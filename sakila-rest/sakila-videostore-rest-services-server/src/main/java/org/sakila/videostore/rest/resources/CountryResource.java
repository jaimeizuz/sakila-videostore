package org.sakila.videostore.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.sakila.videostore.jpa.entities.City;
import org.sakila.videostore.jpa.entities.Country;
import org.sakila.videostore.rest.dto.CitiesDTO;
import org.sakila.videostore.rest.dto.CityDTO;
import org.sakila.videostore.rest.dto.CountriesDTO;
import org.sakila.videostore.rest.dto.CountryDTO;
import org.sakila.videostore.rest.mappers.SakilaMapper;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/countries")
public class CountryResource {

    @Inject
    SakilaMapper sakilaMapper;

    private static final Logger LOG = Logger.getLogger(CountryResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountryList() {

        LOG.info("GET countries requested");
        
        CountriesDTO countriesList = new CountriesDTO();
        List<Country> jpaServiceResponse = new ArrayList<Country>();

        jpaServiceResponse = Country.findAll().list();

        for (Country country : jpaServiceResponse) {
            countriesList.addCountry(sakilaMapper.countryEntityToDto(country));            
        }

        LOG.info("GET countries result: " + countriesList.toString());

        return Response.ok(
            countriesList, 
            MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{countryId}/cities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCitiesByCountryId(@PathParam("countryId")short countryId) {
        
        LOG.info("GET cities by country " + countryId + " requested");

        CitiesDTO citiesList = new CitiesDTO();
        List<City> jpaServiceResponse = new ArrayList<City>();

        jpaServiceResponse = City.find("country.countryId= ?1" , countryId).list();

        for (City city : jpaServiceResponse) {
            citiesList.addCity(sakilaMapper.cityEntityToDto(city));
        }

        LOG.info("GET cities by country " + countryId + " result: " + citiesList.toString());

        return Response.ok(
            citiesList, 
            MediaType.APPLICATION_JSON).build();
    }
}
