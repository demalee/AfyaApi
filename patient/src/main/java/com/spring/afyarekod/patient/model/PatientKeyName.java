package com.spring.afyarekod.patient.model;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class PatientKeyName implements Serializable {


    private static final long serialVersionUID = -8434755647058740017L;
    @PrimaryKeyColumn(name = "name", type = PARTITIONED)
    private String gender;

    @PrimaryKeyColumn(name = "patientid", ordinal = 0)
    private String patientid;
    private String name;

    public String getName(String name) {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatientId() {
        return patientid;
    }

    public void setPatientidId(String patientidid) {
        this.patientid = patientid;
    }

}