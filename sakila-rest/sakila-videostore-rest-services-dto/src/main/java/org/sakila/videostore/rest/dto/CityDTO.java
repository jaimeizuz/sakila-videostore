package org.sakila.videostore.rest.dto;

import java.io.Serializable;
import java.util.Date;

public class CityDTO  implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private short cityId;
    public String city;
    private short countryId;
    public Date last_update;

    public CityDTO() {
    }

    public CityDTO(short cityId, String city, short countryId, Date last_update) {
        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
        this.last_update = last_update;
    }

    public short getCityId() {
        return cityId;
    }

    public void setCityId(short cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public short getCountryId() {
        return countryId;
    }

    public void setCountryId(short countryId) {
        this.countryId = countryId;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "CityDTO [cityId=" + cityId + ", city=" + city + ", countryId=" + countryId + ", last_update="
                + last_update + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cityId;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + countryId;
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
        CityDTO other = (CityDTO) obj;
        if (cityId != other.cityId)
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (countryId != other.countryId)
            return false;
        if (last_update == null) {
            if (other.last_update != null)
                return false;
        } else if (!last_update.equals(other.last_update))
            return false;
        return true;
    }
    
}
