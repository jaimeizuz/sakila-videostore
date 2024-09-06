package org.sakila.videostore.rest.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class CitiesDTO  implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private ArrayList<CityDTO> citiesList;

    public CitiesDTO() {
    }

    public CitiesDTO(ArrayList<CityDTO> citiesList) {
        this.citiesList = citiesList;
    }

    public ArrayList<CityDTO> getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(ArrayList<CityDTO> citiesList) {
        this.citiesList = citiesList;
    }

    public void addCity(CityDTO city) {
        if(this.citiesList == null) {
            this.citiesList = new ArrayList<CityDTO>();
        }

        citiesList.add(city);
    }

    @Override
    public String toString() {
        return "CitiesDTO [citiesList=" + citiesList + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((citiesList == null) ? 0 : citiesList.hashCode());
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
        CitiesDTO other = (CitiesDTO) obj;
        if (citiesList == null) {
            if (other.citiesList != null)
                return false;
        } else if (!citiesList.equals(other.citiesList))
            return false;
        return true;
    }
    
}
