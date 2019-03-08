package com.jjcosare.fhir.dto;

import org.hl7.fhir.r4.model.Patient;

import java.util.Date;

/**
 * Created by jjcosare on 3/7/19.
 */
public class PatientDto {

    private String identifier;

    private String name;

    private Date dateOfBirth;

    private String gender;

    private Date updatedAt;

    public PatientDto(){}

    public PatientDto(Patient patient){
        this.identifier = patient.getIdentifierFirstRep().getValue();
        this.name = patient.getNameFirstRep().getNameAsSingleString();
        this.dateOfBirth = patient.getBirthDate();
        this.gender = patient.getGender().getDisplay();
        this.updatedAt = patient.getMeta().getLastUpdated();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
