package io.peerislands.demo.data;

import io.peerislands.demo.repository.ObservationRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class InsertObservationsDataThread implements Runnable {
  CountDownLatch latch;
  long documentCountPerThread;
  ObservationRepository repository;

  public InsertObservationsDataThread(long documentCountPerThread,
                                      ObservationRepository repository,
                                      CountDownLatch latch) {
    this.documentCountPerThread = documentCountPerThread;
    this.repository = repository;
    this.latch = latch;
  }

  @Override
  public void run() {
    long threadId = Thread.currentThread().getId();
    try {
      log.debug("Thread # {} is inserting the observation documents", threadId);
      repository.bulkInsertObservations(documentCountPerThread);
    } catch (Exception e) {
      log.error("Thread # {} : Error inserting events", threadId, e);
    }
    log.info("Thread # {} is completed", threadId);
    latch.countDown();
    log.info("Thread # {} : Latch count {}", threadId, latch.getCount());
  }

}
