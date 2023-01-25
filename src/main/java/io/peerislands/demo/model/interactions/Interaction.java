package io.peerislands.demo.model.interactions;

import io.peerislands.demo.model.common.AuditTrial;
import io.peerislands.demo.model.common.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Interaction {
  private String intSessId;
  private String intId;
  private String intLegId;
  private String channel;
  private String platform;
  private Date startDt;
  private Date endDt;
  private boolean lockInd;
  private Date retainUntilDt;
  private List<Item> metadataItems;
  private String intSrc;
  private String srcIntId;
  private String srcIntLegId;
  private List<AuditTrial> auditTrials;
  private List<Media> medias;
  private Insight insight;
}
