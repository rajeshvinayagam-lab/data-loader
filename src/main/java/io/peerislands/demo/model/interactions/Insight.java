package io.peerislands.demo.model.interactions;

import io.peerislands.demo.model.common.AuditTrial;
import io.peerislands.demo.model.common.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Insight {
  private String insightId;
  private Date retainUntilDt;
  private List<Item> insightItems;
  private List<AuditTrial> auditTrials;
}
