package org.sakila.videostore.processes.api.model;

import org.kie.kogito.MapInput;
import org.kie.kogito.MapInputId;
import org.kie.kogito.MapOutput;
import org.kie.kogito.MappableToModel;
import org.kie.kogito.Model;
import org.kie.kogito.ProcessInput;
import org.sakila.videostore.rest.dto.CountriesDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

@ProcessInput(processName = "ServiceTaskProcess")
public class ServiceTaskProcessModelInput implements Model, MapInput, MapInputId, MapOutput, MappableToModel<ServiceTaskProcessModel> {

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
    public ServiceTaskProcessModel toModel() {
        ServiceTaskProcessModel result = new ServiceTaskProcessModel();
        result.setValidCountries(getValidCountries());
        result.setCountriesList(getCountriesList());
        return result;
    }
}
