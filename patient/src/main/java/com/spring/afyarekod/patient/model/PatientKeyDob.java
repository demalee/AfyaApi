package com.spring.afyarekod.patient.model;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class PatientKeyDob implements Serializable {


    private static final long serialVersionUID = 8571033128885041603L;
    @PrimaryKeyColumn(name = "date_of_birth", type = PARTITIONED)
    private String gender;

    @PrimaryKeyColumn(name = "patientid", ordinal = 0)
    private String patientid;
    private Date date_of_birth;


    public Date getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    public String getPatientId() {
        return patientid;
    }

    public void setPatientidId(String patientidid) {
        this.patientid = patientid;
    }

}