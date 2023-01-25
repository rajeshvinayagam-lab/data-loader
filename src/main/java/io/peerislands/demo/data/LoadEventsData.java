package io.peerislands.demo.data;

import com.mongodb.MongoException;
import com.mongodb.client.result.InsertOneResult;
import io.peerislands.demo.data.fakers.EventFakeDataGenerator;
import io.peerislands.demo.data.fakers.InteractionFakeDataGenerator;
import io.peerislands.demo.model.events.Event;
import io.peerislands.demo.repository.EventRepository;
import io.peerislands.demo.repository.InteractionRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class LoadEventsData {

  static final int THREAD_COUNT = 5;
  static final long TOTAL_INTERACTION_DOCUMENTS = 40000L;

  public static void main(String[] args) {
    EventRepository eventRepository = new EventRepository();

//    test(eventRepository);

    clean(eventRepository);
    loadEventDocuments(eventRepository);
  }

  public static void clean(EventRepository eventRepository) {
    eventRepository.deleteEvents();
  }

  public static void test(EventRepository eventRepository) {
    try {
      Event event = EventFakeDataGenerator.getEvent();
      InsertOneResult result = eventRepository.insertEvent(event);
      System.out.println("Success! Inserted document id: " + result.getInsertedId());
    } catch (MongoException me) {
      System.err.println("Unable to insert due to an error: " + me);
    }
  }

  public static void loadEventDocuments(EventRepository eventRepository) {
    long startTime = System.currentTimeMillis();
    log.info("Generating Interaction data...");

    try {
      ExecutorService tsExecutorService = Executors.newFixedThreadPool(THREAD_COUNT);
      CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

      long documentCountPerThread = TOTAL_INTERACTION_DOCUMENTS / THREAD_COUNT;

      log.info("Generating {} documents per thread.", documentCountPerThread);

      for (int i = 0; i < THREAD_COUNT; i++) {
        tsExecutorService.execute(new InsertEventsDataThread(documentCountPerThread, eventRepository, latch));
      }

      latch.await();
      tsExecutorService.shutdown();

    } catch (Exception e) {
      log.error("Unable to insert due to an error: " + e);
    }
    long endTime = System.currentTimeMillis();
    log.info("Events data generated in {} minutes", (endTime - startTime) / 1000.0 / 60.0);
  }
}
