package com.spring.afyarekod.patient.repository;

import com.spring.afyarekod.patient.model.PatientByLocation;
import com.spring.afyarekod.patient.model.PatientKeyLocation;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface PatientLocationRepository extends CassandraRepository<PatientByLocation, PatientKeyLocation> {
    List<PatientByLocation> findByKeyName(String name);
}
