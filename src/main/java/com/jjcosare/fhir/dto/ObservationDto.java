package com.jjcosare.fhir.dto;

import org.hl7.fhir.r4.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jjcosare on 3/7/19.
 */
public class ObservationDto {

    private String category;

    private String code;

    private Date effectiveAt;

    private Date issuedAt;

    private String value;

    private PatientDto patientDto;

    private List<ComponentDto> componentDtoList;

    private List<String> codeableConceptList;

    public ObservationDto() {}

    public ObservationDto(Observation observation, Patient patient) {
        this.category = observation.getCategoryFirstRep().getCodingFirstRep().getDisplay();
        this.code = observation.getCode().getCodingFirstRep().getDisplay();
        this.effectiveAt = observation.getEffectiveDateTimeType().toCalendar().getTime();
        this.issuedAt = observation.getIssued();
        if(Objects.nonNull(observation.getValue())){
            if(observation.getValue() instanceof Quantity) {
                this.value = observation.getValueQuantity().getValue() + " " +
                        observation.getValueQuantity().getUnit();
            }else if(observation.getValue() instanceof CodeableConcept){
                this.codeableConceptList = new ArrayList<>();
                for(Coding coding : observation.getValueCodeableConcept().getCoding()){
                    this.codeableConceptList.add(coding.getDisplay());
                }
            }else{
                Logger.getGlobal().log(Level.INFO,"Observation/"+observation.getId()+" value not yet supported on ObservationDto");
            }
        }else{
            this.componentDtoList = new ArrayList<>();
            for(Observation.ObservationComponentComponent component : observation.getComponent()){
                this.componentDtoList.add(new ComponentDto(component));
            }
        }
        this.patientDto = new PatientDto(patient);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getEffectiveAt() {
        return effectiveAt;
    }

    public void setEffectiveAt(Date effectiveAt) {
        this.effectiveAt = effectiveAt;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PatientDto getPatientDto() {
        return patientDto;
    }

    public void setPatientDto(PatientDto patientDto) {
        this.patientDto = patientDto;
    }

    public List<ComponentDto> getComponentDtoList() {
        return componentDtoList;
    }

    public void setComponentDtoList(List<ComponentDto> componentDtoList) {
        this.componentDtoList = componentDtoList;
    }

    public List<String> getCodeableConceptList() {
        return codeableConceptList;
    }

    public void setCodeableConceptList(List<String> codeableConceptList) {
        this.codeableConceptList = codeableConceptList;
    }
}
