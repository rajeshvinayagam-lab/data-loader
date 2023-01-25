package io.peerislands.demo.data;

import io.peerislands.demo.repository.ObservationV2Repository;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class InsertObservationsV2DataThread implements Runnable {
  CountDownLatch latch;
  long documentCountPerThread;
  ObservationV2Repository repository;

  public InsertObservationsV2DataThread(long documentCountPerThread,
                                        ObservationV2Repository repository,
                                        CountDownLatch latch) {
    this.documentCountPerThread = documentCountPerThread;
    this.repository = repository;
    this.latch = latch;
  }

  @Override
  public void run() {
    long threadId = Thread.currentThread().getId();
    try {
      log.debug("Thread # {} is inserting the observationV2 documents", threadId);
      repository.bulkInsertObservationsV2(documentCountPerThread);
    } catch (Exception e) {
      log.error("Thread # {} : Error inserting events", threadId, e);
    }
    log.info("Thread # {} is completed", threadId);
    latch.countDown();
    log.info("Thread # {} : Latch count {}", threadId, latch.getCount());
  }

}
