package com.jjcosare.fhir.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.PerformanceOptionsEnum;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jjcosare on 3/6/19.
 */
@Configuration
public class HapiClientConfig {

    @Bean
    public FhirContext fhirContext() {
        FhirContext fhirContext = FhirContext.forR4();
        fhirContext.setPerformanceOptions(PerformanceOptionsEnum.DEFERRED_MODEL_SCANNING);
        return fhirContext;
    }

    @Bean
    public IGenericClient fhirClient() {
        IGenericClient fhirClient = fhirContext().newRestfulGenericClient("http://localhost:8080/fhir");
        return fhirClient;
    }

    @Bean
    public IParser fhirParser() {
        IParser fhirParser = fhirContext().newJsonParser();
        return fhirParser;
    }
}
