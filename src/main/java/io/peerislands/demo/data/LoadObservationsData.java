package io.peerislands.demo.data;

import com.mongodb.MongoException;
import com.mongodb.client.result.InsertOneResult;
import io.peerislands.demo.data.fakers.ObservationFakeDataGenerator;
import io.peerislands.demo.model.ehr.Observation;
import io.peerislands.demo.repository.ObservationRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class LoadObservationsData {

  static final int THREAD_COUNT = 20;
  static final long TOTAL_OBSERVATION_DOCUMENTS = 100000000L;

  public static void main(String[] args) {
    ObservationRepository observationRepository = new ObservationRepository();

//    test(observationRepository);

//    clean(observationRepository);
    loadObservationDocuments(observationRepository);
  }

  public static void clean(ObservationRepository observationRepository) {
    observationRepository.deleteObservations();
  }

  public static void test(ObservationRepository observationRepository) {
    try {
      Observation observation = ObservationFakeDataGenerator.getObservation();
      InsertOneResult result = observationRepository.insertObservation(observation);
      System.out.println("Success! Inserted document id: " + result.getInsertedId());
    } catch (MongoException me) {
      System.err.println("Unable to insert due to an error: " + me);
    }
  }

  public static void loadObservationDocuments(ObservationRepository observationRepository) {
    long startTime = System.currentTimeMillis();
    log.info("Generating Observation data...");

    try {
      ExecutorService tsExecutorService = Executors.newFixedThreadPool(THREAD_COUNT);
      CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

      long documentCountPerThread = TOTAL_OBSERVATION_DOCUMENTS / THREAD_COUNT;

      log.info("Generating {} documents per thread.", documentCountPerThread);

      for (int i = 0; i < THREAD_COUNT; i++) {
        tsExecutorService.execute(new InsertObservationsDataThread(documentCountPerThread, observationRepository, latch));
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
