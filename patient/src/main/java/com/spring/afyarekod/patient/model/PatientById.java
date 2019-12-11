package com.spring.afyarekod.patient.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Table("patient_by_id")
public class PatientById implements Serializable {

    private static final long serialVersionUID = 1982449322818435136L;
    @PrimaryKey
    private String patientid;

    private String firstname;

    private String lastname;

    private String gender;

    private String email;

    private String identification_number;

    private String place_of_residence;

    private String address;

    private String occupation;

    private String status;

    private int score;

    @Column("date")
    private Date date_of_birth;

    private List<String> phone_number;

    private Map<String, String> next_of_kin;

    private Map<String, String> insurance;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentification_number() {
        return identification_number;
    }

    public void setIdentification_number(String identification_number) {
        this.identification_number = identification_number;
    }

    public String getPlace_of_residence() {
        return place_of_residence;
    }

    public void setPlace_of_residence(String place_of_residence) {
        this.place_of_residence = place_of_residence;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public List<String> getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(List<String> phone_number) {
        this.phone_number = phone_number;
    }

    public Map<String, String> getNext_of_kin() {
        return next_of_kin;
    }

    public void setNext_of_kin(Map<String, String> next_of_kin) {
        this.next_of_kin = next_of_kin;
    }

    public Map<String, String> getInsurance() {
        return insurance;
    }

    public void setInsurance(Map<String, String> insurance) {
        this.insurance = insurance;
    }
}
