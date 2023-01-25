package io.peerislands.demo.data;

import com.mongodb.MongoException;
import com.mongodb.client.result.InsertOneResult;
import io.peerislands.demo.data.fakers.InteractionFakeDataGenerator;
import io.peerislands.demo.model.interactions.Interaction;
import io.peerislands.demo.repository.InteractionRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class LoadInteractionData {

  static final int THREAD_COUNT = 5;
  static final long TOTAL_INTERACTION_DOCUMENTS = 40000L;

  public static void main(String[] args) {
    InteractionRepository interactionRepository = new InteractionRepository();

//    test(interactionRepository);

    clean(interactionRepository);
    loadInterationDocuments(interactionRepository);
  }

  public static void clean(InteractionRepository interactionRepository) {
    interactionRepository.deleteInteractions();
  }

  public static void test(InteractionRepository interactionRepository) {
    try {
      Interaction interaction = InteractionFakeDataGenerator.getInteraction();
      InsertOneResult result = interactionRepository.insertInteraction(interaction);
      System.out.println("Success! Inserted document id: " + result.getInsertedId());
    } catch (MongoException me) {
      System.err.println("Unable to insert due to an error: " + me);
    }
  }

  public static void loadInterationDocuments(InteractionRepository interactionRepository) {
    long startTime = System.currentTimeMillis();
    log.info("Generating Interaction data...");

    try {
      ExecutorService tsExecutorService = Executors.newFixedThreadPool(THREAD_COUNT);
      CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

      long documentCountPerThread = TOTAL_INTERACTION_DOCUMENTS / THREAD_COUNT;

      log.info("Generating {} documents per thread.", documentCountPerThread);

      for (int i = 0; i < THREAD_COUNT; i++) {
        tsExecutorService.execute(new InsertInteractionsDataThread(documentCountPerThread, interactionRepository, latch));
      }

      latch.await();
      tsExecutorService.shutdown();

    } catch (Exception e) {
      log.error("Unable to insert due to an error: " + e);
    }
    long endTime = System.currentTimeMillis();
    log.info("Interaction data generated in {} minutes", (endTime - startTime) / 1000.0 / 60.0);
  }
}
