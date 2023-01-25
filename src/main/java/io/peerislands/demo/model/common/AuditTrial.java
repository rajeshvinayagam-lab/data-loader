package io.peerislands.demo.model.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuditTrial {
  private String by;
  private Date dt;
  private String action;
}
