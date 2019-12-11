package com.spring.afyarekod.patient.service;


import java.util.List;

import com.spring.afyarekod.patient.model.*;
import com.spring.afyarekod.patient.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.afyarekod.patient.model.PatientByGender;
import com.spring.afyarekod.patient.model.PatientByName;
import com.spring.afyarekod.patient.model.PatientByEmail;
import com.spring.afyarekod.patient.model.PatientByLocation;
import com.spring.afyarekod.patient.model.PatientByGender;
import com.spring.afyarekod.patient.model.PatientById;
import com.spring.afyarekod.patient.model.PatientByDob;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {



    @Autowired
    PatientIdRepository patientIdRepository;

    @Autowired
    PatientGenderRepository patientGenderRepository;

    @Autowired
    PatientEmailRepository patientEmailRepository;

    @Autowired
    PatientLocationRepository patientLocationRepository;
    @Autowired
    PatientDobRepository patientDobRepository;

    @Override
    public void createPatient(PatientById patientById) {

    }

    @Override
    public void createPatient(PatientByEmail patientByEmail) {

    }

    @Override
    public void createPatient(PatientByGender patientByGender) {

    }

    @Override
    public void createPatient(PatientByLocation patientByLocation) {

    }

    @Override
    public void createPatient(PatientByPhone patientByPhone) {

    }

    @Override
    public void createPatient(PatientByName patientByName) {

    }

    @Override
    public void createPatient(PatientByDob patientByDob) {

    }

    @Override
    public PatientById findByKeyId(String patientid) {
        return PatientIdRepository.findByKeyId(patientid);
    }
    @Override
    public List<PatientByLocation> findByPatientByLocation(String name) {
        return patientLocationRepository.findByKeyName(name);
    }



//    @Override
//    public void createPatient(PatientById PatientById) {
//        PatientIdRepository.save(PatientById);
//    }


}
