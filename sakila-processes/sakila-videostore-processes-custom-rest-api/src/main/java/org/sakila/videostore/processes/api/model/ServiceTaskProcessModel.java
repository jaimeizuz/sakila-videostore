package org.sakila.videostore.processes.api.model;

import org.kie.kogito.MapInput;
import org.kie.kogito.MapInputId;
import org.kie.kogito.MapOutput;
import org.kie.kogito.MappableToModel;
import org.kie.kogito.Model;
import org.sakila.videostore.rest.dto.CountriesDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

public class ServiceTaskProcessModel implements Model, MapInput, MapInputId, MapOutput, MappableToModel<ServiceTaskProcessModelOutput> {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @JsonProperty(value = "validCountries")
    @Valid
    private Boolean validCountries;

    public Boolean getValidCountries() {
        return validCountries;
    }

    public void setValidCountries(Boolean validCountries) {
        this.validCountries = validCountries;
    }

    @JsonProperty(value = "countriesList")
    @Valid
    private CountriesDTO countriesList;

    public CountriesDTO getCountriesList() {
        return countriesList;
    }

    public void setCountriesList(CountriesDTO countriesList) {
        this.countriesList = countriesList;
    }

    @Override()
    public ServiceTaskProcessModelOutput toModel() {
        ServiceTaskProcessModelOutput result = new ServiceTaskProcessModelOutput();
        result.setId(getId());
        result.setValidCountries(getValidCountries());
        result.setCountriesList(getCountriesList());
        return result;
    }
}

