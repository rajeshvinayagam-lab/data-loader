package io.peerislands.demo.model.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Item {
  public String group;
  public List<Attribute> attributes;
}
