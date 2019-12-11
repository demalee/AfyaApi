package com.spring.afyarekod.patient.repository;

import com.spring.afyarekod.patient.model.PatientByEmail;
import com.spring.afyarekod.patient.model.PatientKeyEmail;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface PatientEmailRepository extends CassandraRepository<PatientByEmail, PatientKeyEmail> {
}
