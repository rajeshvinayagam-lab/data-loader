package io.peerislands.demo.data.fakers;

import com.github.javafaker.Faker;
import io.peerislands.demo.model.common.Attribute;
import io.peerislands.demo.model.common.AuditTrial;
import io.peerislands.demo.model.common.Item;
import io.peerislands.demo.model.events.Event;
import io.peerislands.demo.model.interactions.Insight;
import io.peerislands.demo.model.interactions.Interaction;
import io.peerislands.demo.model.interactions.Media;

import java.util.*;
import java.util.random.RandomGenerator;

public class EventFakeDataGenerator {

  static RandomGenerator randomGenerator = RandomGenerator.getDefault();
  static Faker faker = new Faker();

  static List<String> channels = Arrays.asList("voice", "email", "chat", "video", "sms", "social");
  static List<String> types = Arrays.asList("Added", "Modified", "Deleted");
  static List<String> actions = Arrays.asList("Added", "Modified", "Deleted");

  static List<String> eventItems = Arrays.asList("specialist", "route");

  static List<String> specialist = Arrays.asList("spcSid", "spcLoginId", "spcExt","spcDialerUserId");
  static List<String> route = Arrays.asList("spcSipSrvr", "spcSipPort");
  static List<String> attributes = Arrays.asList("eventName","eventCat","pubId","pubName","pubDt");

  static Map<String, List<String>> eventItemsMap = Map.ofEntries(
    Map.entry("specialist", specialist),
    Map.entry("route", route));


  static long testDataStartDate = 1648789200000L;
  static long testDataEndDate = 1649307600000L;
  static int transcriptLength = 100;

  public static List<Event> getEventList(int count) {
    List<Event> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      list.add(getEvent());
    }
    return list;
  }

  public static Event getEvent() {
    return createFakeEvent();
  }

  private static Event createFakeEvent() {
    Event event = new Event();

    event.setSrcEventId(faker.internet().uuid());
    event.setChannel(channels.get(faker.random().nextInt(0, 5)));
    event.setEventType(types.get(faker.random().nextInt(0, 2)));
    event.setEventSrc(types.get(faker.random().nextInt(0, 2)));
    event.setSrcNotificationId(faker.internet().uuid());
    event.setSrcSeqNum(faker.random().nextLong());

    event.setItems(getFakeItemList(faker.random().nextInt(2, 5)));
    event.setAuditTrails(getFakeAuditList(faker.random().nextInt(2, 5)));
    event.setAttributes(getFakeAttributes());
    setEventDates(event);

    return event;
  }

  private static void setEventDates(Event event) {
    long startDate = randomGenerator.nextLong(testDataStartDate, testDataEndDate);
    long endDate = randomGenerator.nextLong(startDate, startDate + randomGenerator.nextLong(1, 10) * 144000000);

    event.setEventDt(new Date(startDate));
    event.setRetainUntilDt(new Date(endDate));
  }

  private static List<AuditTrial> getFakeAuditList(int count) {
    List<AuditTrial> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      list.add(createFakeAuditTrial());
    }
    return list;
  }

  private static AuditTrial createFakeAuditTrial() {
    long auditDate = randomGenerator.nextLong(testDataStartDate, testDataStartDate + randomGenerator.nextLong(1, 10) * 144000000);

    AuditTrial auditTrial = new AuditTrial();

    auditTrial.setDt(new Date(auditDate));
    auditTrial.setBy(faker.funnyName().name());
    auditTrial.setAction(actions.get(faker.random().nextInt(0, 2)));

    return auditTrial;
  }

  private static List<Item> getFakeItemList(int count) {
    List<Item> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      list.add(createFakeItem());
    }
    return list;
  }

  private static Item createFakeItem() {
    Item item = new Item();
    String groupName = "";

    groupName = eventItems.get(faker.random().nextInt(0, 1));
    item.setGroup(groupName);
    item.setAttributes(getFakeAttributeList(groupName));
    return item;
  }

  private static List<Attribute> getFakeAttributeList(String groupName) {
    List<Attribute> attributes = new ArrayList<>();

    List<String> attributeNames = eventItemsMap.get(groupName);
    if (attributeNames != null) {
      attributeNames.forEach(an -> {
        attributes.add(new Attribute(an, faker.lorem().fixedString(4)));
      });
    } else {
      System.out.println(groupName);
    }
    return attributes;
  }

  private static List<Attribute> getFakeAttributes() {
    List<Attribute> attributeList = new ArrayList<>();
    attributes.forEach(an -> attributeList.add(new Attribute(an, faker.lorem().fixedString(4))));
    return attributeList;
  }
}
