package com.solvd.bank.service;

import com.solvd.bank.domain.City;
import com.solvd.bank.persistence.CitiesRepository;
import com.solvd.bank.persistence.impl.CitiesJdbcImpl;

import java.util.List;

public class CitiesService {
    private final CitiesRepository citiesRepository;

    public CitiesService(){
        citiesRepository = new CitiesJdbcImpl();
    }

    public List<City> getAllCities(){
        return citiesRepository.getAll();
    }

    public City getCityById(int id){
        return citiesRepository.getEntityById(id);
    }

    public void SaveCity(City city){
        citiesRepository.saveEntity(city);
    }

    public void updateCity(City city){
        citiesRepository.updateEntity(city);
    }

    public void removeCityById(int id){
        citiesRepository.removeEntityByID(id);
    }
}
