package org.sakila.videostore.rest.dto;

import java.io.Serializable;
import java.util.Date;

public class CountryDTO implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private short countryId;
    public String country;
    public Date last_update;

    public CountryDTO(short countryId, String country, Date last_update) {
        this.countryId = countryId;
        this.country = country;
        this.last_update = last_update;
    }

    public short getCountryId() {
        return countryId;
    }

    public void setCountryId(short countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "CountryDTO [countryId=" + countryId + ", country=" + country + ", last_update=" + last_update + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + countryId;
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((last_update == null) ? 0 : last_update.hashCode());
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
        CountryDTO other = (CountryDTO) obj;
        if (countryId != other.countryId)
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (last_update == null) {
            if (other.last_update != null)
                return false;
        } else if (!last_update.equals(other.last_update))
            return false;
        return true;
    }
}
