package io.peerislands.demo.model.interactions;

import io.peerislands.demo.model.common.AuditTrial;
import io.peerislands.demo.model.common.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Media {
  private String mediaId;
  private String mediaUrl;
  private String transcriptText;
  private Date retainUntilDt;
  private Date hotStorUntilDt;
  private Date coldStorUntilDt;
  private Date archStorUntilDt;
  private List<Item> metadataItems;
  private String mediaSrc;
  private String srcMediaStreamer;
  private String srcMediaId;
  private String srcMediaUrl;
  private List<AuditTrial> auditTrials;

}
