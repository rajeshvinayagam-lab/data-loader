package io.peerislands.demo.model.rules;

import io.peerislands.demo.model.common.Attribute;
import io.peerislands.demo.model.common.AuditTrial;
import io.peerislands.demo.model.common.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Rule {
  private List<Item> ruleItems;
  private List<Attribute> attributes;
  private List<AuditTrial> auditTrails;
}
