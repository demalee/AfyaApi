package com.spring.afyarekod.patient.model;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class PatientKeyLocation implements Serializable {


    private static final long serialVersionUID = -4392150662090705951L;
    @PrimaryKeyColumn(name = "date_of_birth", type = PARTITIONED)
    private String location;

    @PrimaryKeyColumn(name = "patientid", ordinal = 0)
    private String patientid;



    public String getLocation() {
        return location;
    }
    public void setDate_of_birth(Date date_of_birth) {
        this.location =location;
    }
    public String getPatientId() {
        return patientid;
    }

    public void setPatientidId(String patientidid) {
        this.patientid = patientid;
    }

}