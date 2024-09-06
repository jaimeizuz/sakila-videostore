package org.sakila.videostore.rest.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class CountriesDTO implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private ArrayList<CountryDTO> countriesList;

    public CountriesDTO() {
        this.countriesList = new ArrayList<CountryDTO>();
    }

    public CountriesDTO(ArrayList<CountryDTO> countriesList) {
        this.countriesList = countriesList;
    }

    public ArrayList<CountryDTO> getCountriesList() {
        return countriesList;
    }

    public void setCountriesList(ArrayList<CountryDTO> countriesList) {
        this.countriesList = countriesList;
    }

    public void addCountry(CountryDTO country) {
        if(this.countriesList == null) {
            this.countriesList = new ArrayList<CountryDTO>();
        }

        countriesList.add(country);
    }

    @Override
    public String toString() {
        return "CountriesDTO [countriesList=" + countriesList + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((countriesList == null) ? 0 : countriesList.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CountriesDTO other = (CountriesDTO) obj;
        if (countriesList == null) {
            if (other.countriesList != null)
                return false;
        } else if (!countriesList.equals(other.countriesList))
            return false;
        return true;
    }
}
