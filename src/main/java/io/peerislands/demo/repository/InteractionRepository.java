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
import io.peerislands.demo.data.fakers.InteractionFakeDataGenerator;
import io.peerislands.demo.model.interactions.Interaction;
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
public class InteractionRepository {

  static final String ANALYTICS_DB = "analytics";
  static final String INTERACTION_COLLECTION = "interactions";
  static final String MONGO_DRIVER = "org.mongodb.driver";

  static final int BATCH_SIZE = 10000;

  private final MongoClient mongoClient;
  private final CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
    fromProviders(PojoCodecProvider.builder().automatic(true).build()));

  public InteractionRepository() {
    String mongodbURL = "mongodb+srv://admin:admin@cluster0.juw8lnc.mongodb.net/?retryWrites=true&w=majority";
    mongoClient = MongoClients.create(mongodbURL);
  }

  public MongoCollection<Interaction> getMongoCollection() {
    MongoDatabase mongoDatabase = mongoClient.getDatabase(ANALYTICS_DB);
    MongoCollection<Interaction> mongoCollection = mongoDatabase
      .getCollection(INTERACTION_COLLECTION, Interaction.class)
      .withCodecRegistry(pojoCodecRegistry);

    java.util.logging.Logger.getLogger(MONGO_DRIVER).setLevel(Level.SEVERE);

    return mongoCollection;
  }

  public void bulkInsertInteractions(long documentCountPerThread) {
    ArrayList insertArray = new ArrayList<InsertOneModel>();

    for (long counter = 1; counter <= documentCountPerThread; counter++) {
      insertArray.add(new InsertOneModel(InteractionFakeDataGenerator.getInteraction()));

      if (insertArray.size() >= BATCH_SIZE) {
        BulkWriteResult result = getMongoCollection().bulkWrite(insertArray,
          new BulkWriteOptions().ordered(false));
        log.info("Inserted {} Interations Documents", insertArray.size());
        insertArray.clear();
      }
    }
    if (!insertArray.isEmpty()) {
      BulkWriteResult result = getMongoCollection().bulkWrite(insertArray,
        new BulkWriteOptions().ordered(false));
      insertArray.clear();
    }
  }

  public InsertOneResult insertInteraction(Interaction interaction) {
    InsertOneResult result = getMongoCollection().insertOne(interaction);
    return result;
  }

  public DeleteResult deleteInteractions() {
    Bson emptyComparison = empty();
    DeleteResult result = getMongoCollection().deleteMany(emptyComparison);
    return result;
  }
}
