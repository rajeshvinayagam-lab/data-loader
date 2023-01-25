package io.peerislands.demo.model.common;

import org.bson.Document;

import java.util.Map;

public class Attribute extends Document {

  public Attribute(String key, Object value) {
    super.append("k", key).append("v", value);
  }

  @Override
  public void putAll(Map<? extends String, ? extends Object> m) {
    super.putAll(m);
  }

}
