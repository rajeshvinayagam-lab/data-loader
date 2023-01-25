package io.peerislands.demo.model.events;

import io.peerislands.demo.model.common.Attribute;
import io.peerislands.demo.model.common.AuditTrial;
import io.peerislands.demo.model.common.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Event {
  private String channel;
  private String eventType;
  private Date eventDt;
  private List<Attribute> attributes;
  private Date retainUntilDt;
  private List<Item> items;
  private String eventSrc;
  private String srcEventId;
  private String srcNotificationId;
  private Long srcSeqNum;
  private List<AuditTrial> auditTrails;
}
