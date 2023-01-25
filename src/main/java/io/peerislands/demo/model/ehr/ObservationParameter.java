package io.peerislands.demo.model.ehr;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObservationParameter {

  private int blood_pressure_systolic;
  private int blood_pressure_diastolic;
  private int blood_pressure_mean_aterial_pressure;
  private int blood_pressure_pulse_pressure;

  private int pulse_oximetry_spo2;
  private double pulse_oximetry_spoc;
  private int pulse_oximetry_spco;
  private int pulse_oximetry_spmet;

  private String blood_group;
  private String blood_antigen;
  private int pulse_rate;
  private int respiration;
  private double body_temperature;

  private int body_height;
  private double body_weight;
  private double body_surface_area;
  private double body_mass_index;
  private double body_hip_circumference;

  private int ecg_artrial_heart_rate;
  private int ecg_ventricular_heart_rate;
  private int ecg_qt_interval_global;
  private int ecg_qtc_interval_global;
  private int ecg_pr_interval_global;
  private int ecg_qrs_duration_global;
  private int ecg_rr_interval_global;

  private double fluid_balance_total_input;
  private double fluid_balance_total_output;
  private double fluid_balance_insensible_loss;
  private double fluid_balance_fluid_balance;

  private double fluid_input_volume;
  private String fluid_input_name;

  private double foetal_biometry_crl;
  private double foetal_biometry_bpd;
  private double foetal_biometry_ofd;
  private double foetal_biometry_hc;
  private double foetal_biometry_tcd;
  private double foetal_biometry_iod;
  private double foetal_biometry_bod;
  private double foetal_biometry_ac;
  private double foetal_biometry_fl;
  private double foetal_biometry_hl;

  private int childrens_global_assessment_scale;
  private int crb65score_bod;
  private int crb65score_ac;
  private int crb65score_fl;
  private int crb65score_age;
  private int crb65score_total_score;

  private int esas_rev_pain;
  private int esas_rev_tiredness;
  private int esas_rev_drowsiness;
  private int esas_rev_nausea;
  private int esas_rev_lack_of_appetite;
  private int esas_rev_shortness_of_breath;
  private int esas_rev_depression;
  private int esas_rev_anxiety;
  private int esas_rev_wellbeing;

  private int bvc_confused;
  private int bvc_irritable;
  private int bvc_boisterous;
  private int bvc_physical_threats;
  private int bvc_verbal_threats;
  private int bvc_attacking_objects;
  private int bvc_total_score;

}
