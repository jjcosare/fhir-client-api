package com.jjcosare.fhir.dto;

import org.hl7.fhir.r4.model.Observation;

/**
 * Created by jjcosare on 3/7/19.
 */
public class ComponentDto {

    private String code;

    private String value;

    public ComponentDto() {}

    public ComponentDto(Observation.ObservationComponentComponent component) {
        this.code = component.getCode().getCodingFirstRep().getDisplay();
        this.value = component.getValueQuantity().getValue() +" "+
                component.getValueQuantity().getUnit();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
