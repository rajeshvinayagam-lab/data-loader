package io.peerislands.demo.model.ehr;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ObservationV2 {
  private String doctorName;
  private String patientName;
  private String gender;
  private int age;
  private String encounterId;
  private Date origin;
  private ObservationParameter attributes;
}
