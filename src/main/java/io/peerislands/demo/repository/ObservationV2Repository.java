package io.peerislands.demo.repository;

import com.mongodb.MongoClientSettings;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import io.peerislands.demo.data.fakers.ObservationFakeDataGenerator;
import io.peerislands.demo.model.ehr.ObservationV2;
import lombok.extern.slf4j.Slf4j;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.logging.Level;

import static com.mongodb.client.model.Filters.empty;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Slf4j
public class ObservationV2Repository {

  static final String EBR_DB = "ehrdata_demo";
  static final String OBSERVATION_COLLECTION = "observationv2";
  static final String MONGO_DRIVER = "org.mongodb.driver";

  static final int BATCH_SIZE = 10000;

  private final MongoClient mongoClient;
  private final CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
    fromProviders(PojoCodecProvider.builder().automatic(true).build()));

  public ObservationV2Repository() {
    String mongodbURL = "mongodb+srv://admin:admin@ehr-demo.4zdhu.mongodb.net/?retryWrites=true&w=majority";
//    String mongodbURL = "mongodb+srv://admin:admin@demo.i7wphlq.mongodb.net/?retryWrites=true&w=majority";
    mongoClient = MongoClients.create(mongodbURL);
  }

  public MongoCollection<ObservationV2> getMongoCollection() {
    MongoDatabase mongoDatabase = mongoClient.getDatabase(EBR_DB);
    MongoCollection<ObservationV2> mongoCollection = mongoDatabase
      .getCollection(OBSERVATION_COLLECTION, ObservationV2.class)
      .withCodecRegistry(pojoCodecRegistry);

    java.util.logging.Logger.getLogger(MONGO_DRIVER).setLevel(Level.SEVERE);

    return mongoCollection;
  }

  public void bulkInsertObservationsV2(long documentCountPerThread) {
    ArrayList insertArray = new ArrayList<InsertOneModel>();

    for (long counter = 1; counter <= documentCountPerThread; counter++) {
      insertArray.add(new InsertOneModel(ObservationFakeDataGenerator.getObservationV2()));

      if (insertArray.size() >= BATCH_SIZE) {
        BulkWriteResult result = getMongoCollection().bulkWrite(insertArray,
          new BulkWriteOptions().ordered(false));
        log.info("Inserted {} ObservationV2", insertArray.size());

        insertArray.clear();
      }
    }
    if (!insertArray.isEmpty()) {
      BulkWriteResult result = getMongoCollection().bulkWrite(insertArray,
        new BulkWriteOptions().ordered(false));
      insertArray.clear();
    }
  }

  public InsertOneResult insertObservationV2(ObservationV2 observation) {
    InsertOneResult result = getMongoCollection().insertOne(observation);
    return result;
  }

  public DeleteResult deleteObservationsV2() {
    Bson emptyComparison = empty();
    DeleteResult result = getMongoCollection().deleteMany(emptyComparison);
    return result;
  }
}
