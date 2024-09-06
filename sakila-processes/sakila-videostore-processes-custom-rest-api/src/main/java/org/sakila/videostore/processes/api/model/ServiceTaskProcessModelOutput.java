package org.sakila.videostore.processes.api.model;

import org.kie.kogito.MapInput;
import org.kie.kogito.MapInputId;
import org.kie.kogito.MapOutput;
import org.kie.kogito.MappableToModel;
import org.kie.kogito.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

public class ServiceTaskProcessModelOutput implements Model, MapInput, MapInputId, MapOutput, MappableToModel<ServiceTaskProcessModel> {

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @JsonProperty(value = "validCountries")
    @Valid
    private java.lang.Boolean validCountries;

    public java.lang.Boolean getValidCountries() {
        return validCountries;
    }

    public void setValidCountries(java.lang.Boolean validCountries) {
        this.validCountries = validCountries;
    }

    @JsonProperty(value = "countriesList")
    @Valid
    private org.sakila.videostore.rest.dto.CountriesDTO countriesList;

    public org.sakila.videostore.rest.dto.CountriesDTO getCountriesList() {
        return countriesList;
    }

    public void setCountriesList(org.sakila.videostore.rest.dto.CountriesDTO countriesList) {
        this.countriesList = countriesList;
    }

    @Override()
    public ServiceTaskProcessModel toModel() {
        ServiceTaskProcessModel result = new ServiceTaskProcessModel();
        result.setId(getId());
        result.setValidCountries(getValidCountries());
        result.setCountriesList(getCountriesList());
        return result;
    }
}
