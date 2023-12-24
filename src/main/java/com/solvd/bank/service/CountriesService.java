package com.solvd.bank.service;

import com.solvd.bank.domain.Country;
import com.solvd.bank.persistence.CountriesRepository;
import com.solvd.bank.persistence.impl.CountriesJdbcImpl;

import java.util.List;

public class CountriesService {
    private final CountriesRepository countriesRepository;

    public CountriesService(){
        this.countriesRepository = new CountriesJdbcImpl();
    }

    public List<Country> getAllCountries(){
        return countriesRepository.getAll();
    }

    public Country getCountryById(int id){
        return countriesRepository.getEntityById(id);
    }

    public void saveCountry(Country country){
        countriesRepository.saveEntity(country);
    }

    public void updateCountry(Country country){
        countriesRepository.updateEntity(country);
    }

    public void removeCountryById(int id){
        countriesRepository.removeEntityByID(id);
    }
}
