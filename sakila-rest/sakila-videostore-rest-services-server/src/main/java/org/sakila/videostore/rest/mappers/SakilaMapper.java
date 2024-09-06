package org.sakila.videostore.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.sakila.videostore.jpa.entities.City;
import org.sakila.videostore.jpa.entities.Country;
import org.sakila.videostore.rest.dto.CityDTO;
import org.sakila.videostore.rest.dto.CountryDTO;

@Mapper(componentModel =  "cdi")
public interface SakilaMapper {

    SakilaMapper INSTANCE = Mappers.getMapper(SakilaMapper.class);

    @Mapping(target = "last_update", source = "lastUpdate")
    CityDTO cityEntityToDto(City city);  

    @Mapping(target = "last_update", source = "lastUpdate")
    CountryDTO countryEntityToDto(Country country);
    
}
