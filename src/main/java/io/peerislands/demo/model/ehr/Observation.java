package io.peerislands.demo.model.ehr;

import io.peerislands.demo.model.common.Attribute;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Observation {
  private String doctorName;
  private String patientName;
  private String gender;
  private int age;
  private String encounterId;
  private Date origin;
  private List<Attribute> attributes;
}
