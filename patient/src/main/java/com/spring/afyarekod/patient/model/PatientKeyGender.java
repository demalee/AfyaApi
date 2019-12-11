package com.spring.afyarekod.patient.model;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class PatientKeyGender implements Serializable {


    private static final long serialVersionUID = -3070115729092998867L;
    @PrimaryKeyColumn(name = "gender", type = PARTITIONED)
    private String gender;

    @PrimaryKeyColumn(name = "patientid", ordinal = 0)
    private String patientid;



    public String getGender() {
        return gender;
    }public void setGender() {
        this.gender =gender;
    }
    public String getPatientId() {
        return patientid;
    }

    public void setPatientidId(String patientidid) {
        this.patientid = patientid;
    }

}