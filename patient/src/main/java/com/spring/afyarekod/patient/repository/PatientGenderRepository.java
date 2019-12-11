package com.spring.afyarekod.patient.repository;

import com.spring.afyarekod.patient.model.PatientByGender;
import com.spring.afyarekod.patient.model.PatientKeyGender;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface PatientGenderRepository extends CassandraRepository<PatientByGender, PatientKeyGender> {
}
