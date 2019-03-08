package com.jjcosare.fhir.controller;

import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import com.jjcosare.fhir.dto.BundleDto;
import com.jjcosare.fhir.dto.ObservationDto;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjcosare on 3/6/19.
 */
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/hapi")
public class HapiController {

    @Autowired
    private IParser fhirParser;

    @Autowired
    private IGenericClient fhirClient;

    @Autowired
    private SimpMessageSendingOperations webSocketTemplate;

    @PutMapping("/Observation/{id}")
    public String putObservation(@PathVariable String id, @RequestBody String json) {
        Observation observation = fhirParser.parseResource(Observation.class, json);
        Patient patient = fhirClient.read().resource(Patient.class).withId(observation.getSubject().getReference()).execute();
        ObservationDto observationDTO = new ObservationDto(observation, patient);
        webSocketTemplate.convertAndSend("/observation", observationDTO);
        return json;
    }

    @GetMapping("/Observation")
    public BundleDto getObservation(@RequestParam(required = false) String url) {
        Bundle bundle = StringUtils.isNotEmpty(url) ? fhirClient.search().byUrl(url).returnBundle(Bundle.class).execute()
                : fhirClient.search().forResource(Observation.class).sort().descending(Observation.DATE)
                    .count(10).returnBundle(Bundle.class).execute();
        List<ObservationDto> list = new ArrayList<>();
        for(Bundle.BundleEntryComponent bundleEntryComponent : bundle.getEntry()){
            Observation observation = (Observation) bundleEntryComponent.getResource();
            Patient patient = fhirClient.read().resource(Patient.class).withId(observation.getSubject().getReference()).execute();
            list.add(new ObservationDto(observation, patient));
        }
        BundleDto bundleDTO = new BundleDto(bundle, list);
        return bundleDTO;
    }

}
