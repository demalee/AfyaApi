package com.spring.afyarekod.patient.service;

import com.spring.afyarekod.patient.model.*;

import java.util.List;

public interface PatientService {
 public  void createPatient(PatientById patientById);
    public  void createPatient(PatientByEmail patientByEmail);
    public  void createPatient(PatientByGender patientByGender);
    public void  createPatient(PatientByLocation patientByLocation);
    public  void  createPatient(PatientByPhone patientByPhone);
    public  void  createPatient(PatientByName patientByName);
    public  void  createPatient(PatientByDob patientByDob);
   public  PatientById findByKeyId(final String patientid);
   public List<PatientByLocation> findByPatientByLocation(String name);


}
