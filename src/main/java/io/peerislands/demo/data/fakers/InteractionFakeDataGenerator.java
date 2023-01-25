package io.peerislands.demo.data.fakers;

import com.github.javafaker.Faker;
import io.peerislands.demo.model.common.Attribute;
import io.peerislands.demo.model.common.AuditTrial;
import io.peerislands.demo.model.common.Item;
import io.peerislands.demo.model.interactions.Insight;
import io.peerislands.demo.model.interactions.Interaction;
import io.peerislands.demo.model.interactions.Media;

import java.util.*;
import java.util.random.RandomGenerator;

public class InteractionFakeDataGenerator {

  static RandomGenerator randomGenerator = RandomGenerator.getDefault();
  static Faker faker = new Faker();

  static List<String> channels = Constants.CHANNELS;
  static List<String> mediaSrc = Constants.MEDIASRC;
  static List<String> mediaStreamer = Constants.MEDIASTREAMER;
  static List<String> actions = Constants.ACTIONS;
  static List<String> sources = Constants.SOURCES;

  static List<String> metadataItems = Arrays.asList("interaction", "specialist", "specialistSkill",
    "voice", "customer", "account", "transfer", "consult", "conference", "participant",
    "disposition", "queue", "dialer", "route", "ivr", "ivrTransaction", "media",
    "email", "screen", "chat", "sms", "video", "social", "web",
//    "storage","audioSession","audioHold","screenSession","transcriptionSession","ingestionSession","tag",
//    "emailSession","chatSession","smsSession","videoSession","socialSession","webSession"
    "annotation", "export"
  );
  static List<String> insightItems = Arrays.asList("measure", "category", "categoryComponent",
    "score", "scoreIndicator");

  static List<String> score = Arrays.asList("scoreId", "scoreName", "scoreWeight");
  static List<String> scoreIndicator = Arrays.asList("indId", "indName", "indScore",
    "indScoreId", "indCpntName", "indMeasName", "indWeight");
  static List<String> categoryComponent = Arrays.asList("cpntId", "cpntName", "cpntCnt", "cpntHits",
    "cpntWeight", "cpntCatId");
  static List<String> category = Arrays.asList("catId", "catName", "catCntr", "catGrp",
    "catWeight");
  static List<String> measure = Arrays.asList("acw", "agitation", "aht", "negSentiment",
    "posSentiment", "sentiment", "holdCnt", "holdLongest", "holdDur", "intDur", "noiseInstCnt",
    "noisePct", "noiseTime", "noiseAvg", "silenceInstCnt", "silenceAvg", "silenceMax",
    "silencePct", "silenceTime", "silenceTotalSec", "stressAvg", "stressMax", "tempo",
    "wordCnt", "confidenceAvg", "overtalkTime", "overtalkInstCnt", "redactTime", "redactInstCnt",
    "intDisc");

  static List<String> interaction = Arrays.asList("intCat", "intDir", "intType", "intLang",
    "lob", "intReason", "intReasonDesc");
  static List<String> specialist = Arrays.asList("intCat", "intDir", "intType", "intLang",
    "lob", "intReason", "intReasonDesc");
  static List<String> specialistSkill = Arrays.asList("intCat", "intDir", "intType", "intLang",
    "lob", "intReason", "intReasonDesc");


  static List<String> account = Arrays.asList("arn", "acctType", "prodType", "prodCode",
    "aoc", "rpc", "loanId", "leaseId");
  static List<String> transfer = Arrays.asList("xferInd", "xferPhoneNr", "xferLob", "xferReason");
  static List<String> consult = Arrays.asList("consultInd", "consultPhoneNr", "consultReason");
  static List<String> voice = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> customer = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> conference = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> participant = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> disposition = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> queue = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> dialer = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> route = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> ivr = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> ivrTransaction = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> media = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> email = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> screen = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> chat = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> sms = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> video = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> social = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> web = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> annotation = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");
  static List<String> export = Arrays.asList("dnDesc", "toPhoneNr", "fromPhoneNr");

  static Map<String, List<String>> itemGroupAttributeMap = Map.ofEntries(
    Map.entry("score", score),
    Map.entry("scoreIndicator", scoreIndicator),
    Map.entry("categoryComponent", categoryComponent),
    Map.entry("category", category),
    Map.entry("measure", measure),

    Map.entry("interaction", interaction),
    Map.entry("specialist", specialist),
    Map.entry("specialistSkill", specialistSkill),
    Map.entry("account", account),
    Map.entry("transfer", transfer),
    Map.entry("consult", consult),
    Map.entry("voice", voice),
    Map.entry("customer", customer),
    Map.entry("web", web),
    Map.entry("social", social),
    Map.entry("video", video),
    Map.entry("sms", sms),
    Map.entry("chat", chat),
    Map.entry("screen", screen),
    Map.entry("conference", conference),
    Map.entry("participant", participant),
    Map.entry("queue", queue),
    Map.entry("dialer", dialer),
    Map.entry("route", route),
    Map.entry("ivr", ivr),
    Map.entry("ivrTransaction", ivrTransaction),
    Map.entry("media", media),
    Map.entry("email", email),
    Map.entry("disposition", disposition),
    Map.entry("annotation", annotation),
    Map.entry("export", export)
  );

  static long testDataStartDate = 1648789200000L;
  static long testDataEndDate = 1649307600000L;
  static int transcriptLength = 100;


  public static List<Interaction> getInteractionList(int count) {
    List<Interaction> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      list.add(getInteraction());
    }
    return list;
  }

  public static Interaction getInteraction() {
    return createFakeInteraction();
  }

  private static Interaction createFakeInteraction() {
    Interaction interaction = new Interaction();

    interaction.setIntSessId(faker.internet().uuid());
    interaction.setIntId(faker.internet().uuid());
    interaction.setIntLegId(faker.internet().uuid());
    interaction.setChannel(channels.get(faker.random().nextInt(0, 5)));
    interaction.setPlatform(faker.lorem().characters(10));
    interaction.setLockInd(faker.random().nextBoolean());

    setInteractionDates(interaction);

    interaction.setInsight(createFakeInsight());
    interaction.setMedias(getFakeMediaList(randomGenerator.nextInt(1, 5)));
    interaction.setAuditTrials(getFakeAuditList(randomGenerator.nextInt(1, 2)));
    interaction.setMetadataItems(getFakeItemList(true, faker.random().nextInt(2, 5)));
    interaction.setIntSrc(sources.get(faker.random().nextInt(0, 1)));
    interaction.setSrcIntId(faker.internet().uuid());
    interaction.setSrcIntLegId(faker.internet().uuid());

    return interaction;
  }

  private static List<Media> getFakeMediaList(int count) {
    List<Media> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      list.add(createFakeMedia());
    }
    return list;
  }

  private static Media createFakeMedia() {
    Media media = new Media();

    media.setMediaId(faker.internet().uuid());
    media.setMediaUrl(faker.internet().url());
    media.setTranscriptText(faker.lorem().characters(transcriptLength));

    setMediaDates(media);

    media.setMediaSrc(mediaSrc.get(faker.random().nextInt(0, 5)));
    media.setSrcMediaStreamer(mediaStreamer.get(faker.random().nextInt(0, 1)));
    media.setSrcMediaUrl(faker.internet().url());
    media.setSrcMediaId(faker.internet().uuid());
    media.setAuditTrials(getFakeAuditList(faker.random().nextInt(2, 5)));
    media.setMetadataItems(getFakeItemList(false, faker.random().nextInt(2, 5)));

    return media;
  }

  private static Insight createFakeInsight() {

    long retainUntilDate = randomGenerator.nextLong(testDataEndDate, testDataEndDate + randomGenerator.nextLong(1, 40) * 144000000);

    Insight insight = new Insight();
    insight.setInsightId(faker.internet().uuid());
    insight.setAuditTrials(getFakeAuditList(faker.random().nextInt(2, 5)));
    insight.setRetainUntilDt(new Date(retainUntilDate));
    insight.setInsightItems(getFakeItemList(true, faker.random().nextInt(2, 5)));

    return insight;
  }

  private static void setInteractionDates(Interaction interaction) {
    long startDate = randomGenerator.nextLong(testDataStartDate, testDataEndDate);
    long endDate = randomGenerator.nextLong(startDate, startDate + randomGenerator.nextLong(1, 10) * 144000000);

    interaction.setEndDt(new Date(endDate));
    interaction.setStartDt(new Date(startDate));
  }

  private static void setMediaDates(Media media) {
    long retainUntilDate = randomGenerator.nextLong(testDataEndDate, testDataEndDate + randomGenerator.nextLong(1, 40) * 144000000);
    long hotUntilDate = randomGenerator.nextLong(testDataEndDate, testDataEndDate + randomGenerator.nextLong(1, 10) * 144000000);
    long coldUntilDate = randomGenerator.nextLong(hotUntilDate, hotUntilDate + randomGenerator.nextLong(1, 10) * 144000000);
    long archUntilDate = randomGenerator.nextLong(coldUntilDate, coldUntilDate + randomGenerator.nextLong(1, 10) * 144000000);

    media.setRetainUntilDt(new Date(retainUntilDate));
    media.setHotStorUntilDt(new Date(hotUntilDate));
    media.setColdStorUntilDt(new Date(coldUntilDate));
    media.setArchStorUntilDt(new Date(archUntilDate));
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

  private static List<Item> getFakeItemList(boolean isInsightItem, int count) {
    List<Item> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      list.add(createFakeItem(isInsightItem));
    }
    return list;
  }

  private static Item createFakeItem(boolean isInsightItem) {
    Item item = new Item();
    String groupName = "";

    if (isInsightItem) {
      groupName = insightItems.get(faker.random().nextInt(0, 4));
    } else {
      groupName = metadataItems.get(faker.random().nextInt(0, metadataItems.size() - 1));
    }
    item.setGroup(groupName);
    item.setAttributes(getFakeAttributeList(groupName));
    return item;
  }

  private static List<Attribute> getFakeAttributeList(String groupName) {
    List<Attribute> attributes = new ArrayList<>();

    List<String> attributeNames = itemGroupAttributeMap.get(groupName);
    if (attributeNames != null) {
      attributeNames.forEach(an -> {
        attributes.add(new Attribute(an, faker.lorem().fixedString(4)));
      });
    } else {
      System.out.println(groupName);
    }
    return attributes;
  }
}
