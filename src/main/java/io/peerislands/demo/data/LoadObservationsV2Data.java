package io.peerislands.demo.data;

import com.mongodb.MongoException;
import com.mongodb.client.result.InsertOneResult;
import io.peerislands.demo.data.fakers.ObservationFakeDataGenerator;
import io.peerislands.demo.model.ehr.ObservationV2;
import io.peerislands.demo.repository.ObservationV2Repository;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class LoadObservationsV2Data {

  static final int THREAD_COUNT = 20;
  static final long TOTAL_OBSERVATIONV2_DOCUMENTS = 100000000L;

  public static void main(String[] args) {
    ObservationV2Repository observationRepository = new ObservationV2Repository();

//    test(observationRepository);

    //clean(observationRepository);
    loadObservationDocuments(observationRepository);
  }

  public static void clean(ObservationV2Repository observationRepository) {
    observationRepository.deleteObservationsV2();
  }

  public static void test(ObservationV2Repository observationRepository) {
    try {
      ObservationV2 observation = ObservationFakeDataGenerator.getObservationV2();
      InsertOneResult result = observationRepository.insertObservationV2(observation);
      System.out.println("Success! Inserted document id: " + result.getInsertedId());
    } catch (MongoException me) {
      System.err.println("Unable to insert due to an error: " + me);
    }
  }

  public static void loadObservationDocuments(ObservationV2Repository observationRepository) {
    long startTime = System.currentTimeMillis();
    log.info("Generating ObservationV2 data...");

    try {
      ExecutorService tsExecutorService = Executors.newFixedThreadPool(THREAD_COUNT);
      CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

      long documentCountPerThread = TOTAL_OBSERVATIONV2_DOCUMENTS / THREAD_COUNT;

      log.info("Generating {} documents per thread.", documentCountPerThread);

      for (int i = 0; i < THREAD_COUNT; i++) {
        tsExecutorService.execute(new InsertObservationsV2DataThread(documentCountPerThread, observationRepository, latch));
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
