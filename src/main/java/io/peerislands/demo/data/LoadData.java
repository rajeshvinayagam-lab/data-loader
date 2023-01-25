package io.peerislands.demo.data;

import com.mongodb.MongoException;
import com.mongodb.client.result.InsertOneResult;
import io.peerislands.demo.data.fakers.InteractionFakeDataGenerator;
import io.peerislands.demo.model.interactions.Interaction;
import io.peerislands.demo.repository.EventRepository;
import io.peerislands.demo.repository.InteractionRepository;
import io.peerislands.demo.repository.ObservationRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class LoadData {

  public static void main(String[] args) {
//    InteractionRepository interactionRepository = new InteractionRepository();
//    EventRepository eventRepository = new EventRepository();
//
//    LoadInteractionData.clean(interactionRepository);
//    LoadInteractionData.loadInterationDocuments(interactionRepository);
//
//    LoadEventsData.clean(eventRepository);
//    LoadEventsData.loadEventDocuments(eventRepository);

    ObservationRepository observationRepository = new ObservationRepository();
    LoadObservationsData.clean(observationRepository);
    LoadObservationsData.loadObservationDocuments(observationRepository);
  }
}
