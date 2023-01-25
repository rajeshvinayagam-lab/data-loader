package io.peerislands.demo.data;

import io.peerislands.demo.repository.InteractionRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class InsertInteractionsDataThread implements Runnable {
  CountDownLatch latch;
  long documentCountPerThread;
  InteractionRepository repository;

  public InsertInteractionsDataThread(long documentCountPerThread,
                                      InteractionRepository repository,
                                      CountDownLatch latch) {
    this.documentCountPerThread = documentCountPerThread;
    this.repository = repository;
    this.latch = latch;
  }

  @Override
  public void run() {
    long threadId = Thread.currentThread().getId();
    try {
      log.debug("Thread # {} is inserting the documents",threadId);
      repository.bulkInsertInteractions(documentCountPerThread);
    } catch (Exception e) {
      log.error("Thread # {} : Error inserting interactions", threadId, e);
    }
    log.info("Thread # {} is completed", threadId);
    latch.countDown();
    log.info("Thread # {} : Latch count {}",threadId, latch.getCount());
  }

}
