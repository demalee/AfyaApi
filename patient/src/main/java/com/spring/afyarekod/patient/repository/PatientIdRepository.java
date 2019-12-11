package com.spring.afyarekod.patient.repository;

import com.spring.afyarekod.patient.model.PatientById;
import org.springframework.data.cassandra.repository.CassandraRepository;
//import com.spring.afyarekod.patient.model.PatientKeyId;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientIdRepository<PatientKeyId> extends CassandraRepository<PatientById, PatientKeyId> {
        public static PatientById findByKeyId(final String id) {
                return null;
        }

        public List<PatientById> findAll();


}
