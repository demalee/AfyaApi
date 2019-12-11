package com.spring.afyarekod.patient.model;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class PatientKeyEmail implements Serializable {



    @PrimaryKeyColumn(name = "email", type = PARTITIONED)
    private String gender;

    @PrimaryKeyColumn(name = "patientid", ordinal = 0)
    private String patientid;
    private  String email;


    public String getEmail() {

        return email;
    }
    public void setEmail() {
        this.email = email;
    }
    public String getPatientId() {
        return patientid;
    }

    public void setPatientidId(String patientidid) {
        this.patientid = patientid;
    }

}