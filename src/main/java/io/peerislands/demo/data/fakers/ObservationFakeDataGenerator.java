package io.peerislands.demo.data.fakers;

import com.github.javafaker.Faker;
import io.peerislands.demo.model.common.Attribute;
import io.peerislands.demo.model.ehr.Observation;
import io.peerislands.demo.model.ehr.ObservationParameter;
import io.peerislands.demo.model.ehr.ObservationV2;

import java.util.*;
import java.util.random.RandomGenerator;

public class ObservationFakeDataGenerator {
  static RandomGenerator randomGenerator = RandomGenerator.getDefault();
  static Faker faker = new Faker();

  static List<String> bloodGroup = Arrays.asList("A", "B", "O", "AB");
  static List<String> bloodAntigen = Arrays.asList("positive", "negative");
  static List<String> gender = Arrays.asList("Male", "Female");

  static long testDataStartDate = 1648789200000L;
  static long testDataEndDate = 1649307600000L;

  static Map<String, Object> observationAttributes = Map.ofEntries(
    Map.entry("blood_pressure.systolic", randomGenerator.nextInt(50, 200)),
    Map.entry("blood_pressure.diastolic", randomGenerator.nextInt(80, 200)),
    Map.entry("blood_pressure.mean_aterial_pressure", randomGenerator.nextInt(80, 200)),
    Map.entry("blood_pressure.pulse_pressure", randomGenerator.nextInt(80, 200)),

    Map.entry("pulse_oximetry.spo2", randomGenerator.nextInt(0, 100)),
    Map.entry("pulse_oximetry.spoc", roundAvoid(randomGenerator.nextDouble(0.0, 10.0), 2)),
    Map.entry("pulse_oximetry.spco", randomGenerator.nextInt(0, 1000)),
    Map.entry("pulse_oximetry.spmet", randomGenerator.nextInt(0, 1000)),

    Map.entry("blood.group", bloodGroup.get(faker.random().nextInt(0, 3))),
    Map.entry("blood.antigen", bloodAntigen.get(faker.random().nextInt(0, 1))),

    Map.entry("pulse.rate", randomGenerator.nextInt(50, 200)),

    Map.entry("respiration", randomGenerator.nextInt(50, 200)),

    Map.entry("body.temperature", roundAvoid(randomGenerator.nextDouble(30.0, 120.0), 2)),
    Map.entry("body.height", randomGenerator.nextInt(20, 200)),
    Map.entry("body.weight", roundAvoid(randomGenerator.nextDouble(30.0, 600.0), 2)),
    Map.entry("body.hip_circumference", roundAvoid(randomGenerator.nextDouble(10.0, 600.0), 2)),
    Map.entry("body.body_mass_index", roundAvoid(randomGenerator.nextDouble(10.0, 600.0), 2)),
    Map.entry("body.body_surface_area", roundAvoid(randomGenerator.nextDouble(1.00, 10.00), 2)),

    Map.entry("ecg.artrial_heart_rate", randomGenerator.nextInt(30, 100)),
    Map.entry("ecg.ventricular_heart_rate", randomGenerator.nextInt(30, 100)),
    Map.entry("ecg.qt_interval_global", randomGenerator.nextInt(20, 200)),
    Map.entry("ecg.qtc_interval_global", randomGenerator.nextInt(20, 200)),
    Map.entry("ecg.pr_interval_global", randomGenerator.nextInt(20, 200)),
    Map.entry("ecg.qrs_duration_global", randomGenerator.nextInt(20, 200)),
    Map.entry("ecg.rr_interval_global", randomGenerator.nextInt(20, 200)),

    Map.entry("fluid_balance.total_input", roundAvoid(randomGenerator.nextDouble(1.00, 10.00), 2)),
    Map.entry("fluid_balance.total_output", roundAvoid(randomGenerator.nextDouble(1.00, 10.00), 2)),
    Map.entry("fluid_balance.insensible_loss", roundAvoid(randomGenerator.nextDouble(1.00, 10.00), 2)),
    Map.entry("fluid_balance.fluid_balance", roundAvoid(randomGenerator.nextDouble(1.00, 10.00), 2)),

    Map.entry("fluid_input.volume", roundAvoid(randomGenerator.nextDouble(10.00, 20.00), 2)),
    Map.entry("fluid_input.name", faker.medical().medicineName()),

    Map.entry("foetal_biometry.crl", roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2)),
    Map.entry("foetal_biometry.bpd", roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2)),
    Map.entry("foetal_biometry.ofd", roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2)),
    Map.entry("foetal_biometry.hc", roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2)),
    Map.entry("foetal_biometry.tcd", roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2)),
    Map.entry("foetal_biometry.iod", roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2)),
    Map.entry("foetal_biometry.bod", roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2)),
    Map.entry("foetal_biometry.ac", roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2)),
    Map.entry("foetal_biometry.fl", roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2)),
    Map.entry("foetal_biometry.hl", roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2)),

    Map.entry("childrens_global_assessment_scale", randomGenerator.nextInt(20, 100)),

    Map.entry("crb65score.bod", randomGenerator.nextInt(0, 1)),
    Map.entry("crb65score.ac", randomGenerator.nextInt(0, 1)),
    Map.entry("crb65score.fl", randomGenerator.nextInt(0, 1)),
    Map.entry("crb65score.age", randomGenerator.nextInt(0, 1)),
    Map.entry("crb65score.total_score", randomGenerator.nextInt(1, 4)),

    Map.entry("esas_rev.pain", randomGenerator.nextInt(1, 10)),
    Map.entry("esas_rev.tiredness", randomGenerator.nextInt(1, 10)),
    Map.entry("esas_rev.drowsiness", randomGenerator.nextInt(1, 10)),
    Map.entry("esas_rev.nausea", randomGenerator.nextInt(1, 10)),
    Map.entry("esas_rev.lack_of_appetite", randomGenerator.nextInt(1, 10)),
    Map.entry("esas_rev.shortness_of_breath", randomGenerator.nextInt(1, 10)),
    Map.entry("esas_rev.depression", randomGenerator.nextInt(1, 10)),
    Map.entry("esas_rev.anxiety", randomGenerator.nextInt(1, 10)),
    Map.entry("esas_rev.wellbeing", randomGenerator.nextInt(1, 10)),

    Map.entry("bvc.confused", randomGenerator.nextInt(0, 1)),
    Map.entry("bvc.irritable", randomGenerator.nextInt(0, 1)),
    Map.entry("bvc.boisterous", randomGenerator.nextInt(0, 1)),
    Map.entry("bvc.physical_threats", randomGenerator.nextInt(0, 1)),
    Map.entry("bvc.verbal_threats", randomGenerator.nextInt(0, 1)),
    Map.entry("bvc.attacking_objects", randomGenerator.nextInt(0, 1)),
    Map.entry("bvc.total_score", randomGenerator.nextInt(1, 6))
  );

  public static List<Observation> getObservationList(int count) {
    List<Observation> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      list.add(getObservation());
    }
    return list;
  }

  public static Observation getObservation() {
    return createFakeObservation();
  }

  private static Observation createFakeObservation() {
    Observation observation = new Observation();

    observation.setAge(randomGenerator.nextInt(1, 100));
    observation.setGender(gender.get(faker.random().nextInt(0, 1)));
    observation.setEncounterId(faker.internet().uuid());

    long startDate = randomGenerator.nextLong(testDataStartDate, testDataEndDate);
    observation.setOrigin(new Date(startDate));

    observation.setDoctorName(faker.name().fullName());
    observation.setPatientName(faker.name().fullName());

    observation.setAttributes(getFakeAttributeList());

    return observation;
  }

  private static List<Attribute> getFakeAttributeList() {
    List<Attribute> attributes = new ArrayList<>();

    List<String> attributeNames = observationAttributes.keySet().stream().toList();
    if (attributeNames != null) {
      attributeNames.forEach(an -> {
        attributes.add(new Attribute(an, randomValue(an)));
      });
    }

    return attributes;
  }

  private static Object randomValue(String keyName) {

    switch (keyName) {
      case "blood_pressure.systolic":
        return randomGenerator.nextInt(50, 110);

      case "blood_pressure.diastolic":
      case "blood_pressure.mean_aterial_pressure":
      case "blood_pressure.pulse_pressure":
        return randomGenerator.nextInt(80, 160);

      case "foetal_biometry.crl":
      case "foetal_biometry.bpd":
      case "foetal_biometry.ofd":
      case "foetal_biometry.hc":
      case "foetal_biometry.tcd":
      case "foetal_biometry.iod":
      case "foetal_biometry.bod":
      case "foetal_biometry.ac":
      case "foetal_biometry.fl":
      case "foetal_biometry.hl":
        return roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2);

      case "ecg.qt_interval_global":
      case "ecg.qtc_interval_global":
      case "ecg.pr_interval_global":
      case "ecg.qrs_duration_global":
      case "ecg.rr_interval_global":

      case "body.height":
        return randomGenerator.nextInt(20, 200);

      case "fluid_balance.total_input":
      case "fluid_balance.total_output":
      case "fluid_balance.insensible_loss":
      case "fluid_balance.fluid_balance":

      case "body.body_surface_area":
        return roundAvoid(randomGenerator.nextDouble(1.00, 10.00), 2);

      case "esas_rev.pain":
      case "esas_rev.tiredness":
      case "esas_rev.drowsiness":
      case "esas_rev.nausea":
      case "esas_rev.lack_of_appetite":
      case "esas_rev.shortness_of_breath":
      case "esas_rev.depression":
      case "esas_rev.anxiety":
      case "esas_rev.wellbeing":
        return randomGenerator.nextInt(1, 10);

      case "crb65score.bod":
      case "crb65score.ac":
      case "crb65score.fl":
      case "crb65score.age":

      case "bvc.confused":
      case "bvc.irritable":
      case "bvc.boisterous":
      case "bvc.physical_threats":
      case "bvc.verbal_threats":
      case "bvc.attacking_objects":
        return randomGenerator.nextInt(0, 1);

      case "ecg.artrial_heart_rate":
      case "ecg.ventricular_heart_rate":
        return randomGenerator.nextInt(30, 100);

      case "pulse_oximetry.spco":
      case "pulse_oximetry.spmet":
        return randomGenerator.nextInt(0, 1000);

      case "pulse.rate":
      case "respiration":
        return randomGenerator.nextInt(50, 200);

      case "blood.group":
        return bloodGroup.get(faker.random().nextInt(0, 3));

      case "blood.antigen":
        return bloodAntigen.get(faker.random().nextInt(0, 1));

      case "pulse_oximetry.spo2":
        return randomGenerator.nextInt(0, 100);

      case "pulse_oximetry.spoc":
        return roundAvoid(randomGenerator.nextDouble(0.0, 10.0), 2);

      case "body.hip_circumference":
      case "body.body_mass_index":
        return roundAvoid(randomGenerator.nextDouble(10.0, 600.0), 2);

      case "body.temperature":
        return roundAvoid(randomGenerator.nextDouble(30.0, 120.0), 2);

      case "body.weight":
        return roundAvoid(randomGenerator.nextDouble(30.0, 600.0), 2);

      case "fluid_input.volume":
        return roundAvoid(randomGenerator.nextDouble(10.00, 20.00), 2);

      case "fluid_input.name":
        return faker.medical().medicineName();

      case "childrens_global_assessment_scale":
        return randomGenerator.nextInt(20, 100);

      case "crb65score.total_score":
        return randomGenerator.nextInt(1, 4);

      case "bvc.total_score":
        return randomGenerator.nextInt(1, 6);
    }

    return 0;
  }

  public static double roundAvoid(double value, int places) {
    double scale = Math.pow(10, places);
    return Math.round(value * scale) / scale;
  }

  public static List<ObservationV2> getObservationListV2(int count) {
    List<ObservationV2> list = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      list.add(getObservationV2());
    }
    return list;
  }

  public static ObservationV2 getObservationV2() {
    return createFakeObservationV2();
  }

  private static ObservationV2 createFakeObservationV2() {
    ObservationV2 observation = new ObservationV2();

    observation.setAge(randomGenerator.nextInt(1, 100));
    observation.setGender(gender.get(faker.random().nextInt(0, 1)));
    observation.setEncounterId(faker.internet().uuid());

    long startDate = randomGenerator.nextLong(testDataStartDate, testDataEndDate);
    observation.setOrigin(new Date(startDate));

    observation.setDoctorName(faker.name().fullName());
    observation.setPatientName(faker.name().fullName());

    observation.setAttributes(getFakeObserservationParameters());

    return observation;
  }

  private static ObservationParameter getFakeObserservationParameters() {
    ObservationParameter observationParameter = new ObservationParameter();

    observationParameter.setBlood_pressure_systolic(randomGenerator.nextInt(50, 200));
    observationParameter.setBlood_pressure_diastolic(randomGenerator.nextInt(80, 200));
    observationParameter.setBlood_pressure_mean_aterial_pressure(randomGenerator.nextInt(80, 200));
    observationParameter.setBlood_pressure_pulse_pressure(randomGenerator.nextInt(80, 200));

    observationParameter.setPulse_oximetry_spo2(randomGenerator.nextInt(0, 100));
    observationParameter.setPulse_oximetry_spoc(roundAvoid(randomGenerator.nextDouble(0.0, 10.0), 2));
    observationParameter.setPulse_oximetry_spco(randomGenerator.nextInt(0, 1000));
    observationParameter.setPulse_oximetry_spmet(randomGenerator.nextInt(0, 1000));

    observationParameter.setBlood_group(bloodGroup.get(faker.random().nextInt(0, 3)));
    observationParameter.setBlood_antigen(bloodAntigen.get(faker.random().nextInt(0, 1)));

    observationParameter.setPulse_rate(randomGenerator.nextInt(50, 200));
    observationParameter.setRespiration(randomGenerator.nextInt(50, 200));

    observationParameter.setBody_temperature(roundAvoid(randomGenerator.nextDouble(30.0, 120.0), 2));
    observationParameter.setBody_height(randomGenerator.nextInt(20, 200));
    observationParameter.setBody_weight(roundAvoid(randomGenerator.nextDouble(30.0, 600.0), 2));
    observationParameter.setBody_surface_area(roundAvoid(randomGenerator.nextDouble(1.00, 10.00), 2));
    observationParameter.setBody_mass_index(roundAvoid(randomGenerator.nextDouble(10.0, 600.0), 2));
    observationParameter.setBody_hip_circumference(roundAvoid(randomGenerator.nextDouble(10.0, 600.0), 2));

    observationParameter.setEcg_artrial_heart_rate(randomGenerator.nextInt(30, 100));
    observationParameter.setEcg_ventricular_heart_rate(randomGenerator.nextInt(30, 100));
    observationParameter.setEcg_qt_interval_global(randomGenerator.nextInt(20, 200));
    observationParameter.setEcg_qtc_interval_global(randomGenerator.nextInt(20, 200));
    observationParameter.setEcg_pr_interval_global(randomGenerator.nextInt(20, 200));
    observationParameter.setEcg_qrs_duration_global(randomGenerator.nextInt(20, 200));
    observationParameter.setEcg_rr_interval_global(randomGenerator.nextInt(20, 200));

    observationParameter.setFluid_balance_total_input(roundAvoid(randomGenerator.nextDouble(1.00, 10.00), 2));
    observationParameter.setFluid_balance_total_output(roundAvoid(randomGenerator.nextDouble(1.00, 10.00), 2));
    observationParameter.setFluid_balance_insensible_loss(roundAvoid(randomGenerator.nextDouble(1.00, 10.00), 2));
    observationParameter.setFluid_balance_fluid_balance(roundAvoid(randomGenerator.nextDouble(1.00, 10.00), 2));

    observationParameter.setFluid_input_volume(roundAvoid(randomGenerator.nextDouble(10.00, 20.00), 2));
    observationParameter.setFluid_input_name(faker.medical().medicineName());

    observationParameter.setFoetal_biometry_crl(roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2));
    observationParameter.setFoetal_biometry_bpd(roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2));
    observationParameter.setFoetal_biometry_ofd(roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2));
    observationParameter.setFoetal_biometry_hc(roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2));
    observationParameter.setFoetal_biometry_tcd(roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2));
    observationParameter.setFoetal_biometry_iod(roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2));
    observationParameter.setFoetal_biometry_bod(roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2));
    observationParameter.setFoetal_biometry_ac(roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2));
    observationParameter.setFoetal_biometry_fl(roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2));
    observationParameter.setFoetal_biometry_hl(roundAvoid(randomGenerator.nextDouble(5.00, 10.00), 2));

    observationParameter.setChildrens_global_assessment_scale(randomGenerator.nextInt(20, 100));
    observationParameter.setCrb65score_bod(randomGenerator.nextInt(0, 1));
    observationParameter.setCrb65score_ac(randomGenerator.nextInt(0, 1));
    observationParameter.setCrb65score_fl(randomGenerator.nextInt(0, 1));
    observationParameter.setCrb65score_age(randomGenerator.nextInt(0, 1));
    observationParameter.setCrb65score_total_score(randomGenerator.nextInt(1, 4));

    observationParameter.setEsas_rev_pain(randomGenerator.nextInt(1, 10));
    observationParameter.setEsas_rev_tiredness(randomGenerator.nextInt(1, 10));
    observationParameter.setEsas_rev_depression(randomGenerator.nextInt(1, 10));
    observationParameter.setEsas_rev_drowsiness(randomGenerator.nextInt(1, 10));
    observationParameter.setEsas_rev_nausea(randomGenerator.nextInt(1, 10));
    observationParameter.setEsas_rev_lack_of_appetite(randomGenerator.nextInt(1, 10));
    observationParameter.setEsas_rev_shortness_of_breath(randomGenerator.nextInt(1, 10));
    observationParameter.setEsas_rev_anxiety(randomGenerator.nextInt(1, 10));
    observationParameter.setEsas_rev_wellbeing(randomGenerator.nextInt(1, 10));

    observationParameter.setBvc_confused(randomGenerator.nextInt(0, 1));
    observationParameter.setBvc_irritable(randomGenerator.nextInt(0, 1));
    observationParameter.setBvc_boisterous(randomGenerator.nextInt(0, 1));
    observationParameter.setBvc_physical_threats(randomGenerator.nextInt(0, 1));
    observationParameter.setBvc_verbal_threats(randomGenerator.nextInt(0, 1));
    observationParameter.setBvc_attacking_objects(randomGenerator.nextInt(0, 1));
    observationParameter.setBvc_total_score(randomGenerator.nextInt(1, 6));

    return observationParameter;
  }

}
