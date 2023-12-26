package com.solvd.bank.service;

import com.solvd.bank.domain.Country;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ICountryDAO;
import com.solvd.bank.persistence.impl.CountryDAO;

import java.util.ArrayList;
import java.util.List;

public class CountriesService {
    private final ICountryDAO I_COUNTRY_DAO;

    public CountriesService(){
        this.I_COUNTRY_DAO = new CountryDAO();
    }

    public ArrayList<Location> getAllLocationsByCountry(Country country){
        return I_COUNTRY_DAO.getAllLocationsByCountry(country);
    }

    public List<Country> getAllCountries(){
        return I_COUNTRY_DAO.getAll();
    }

    public Country getCountryById(int id){
        return I_COUNTRY_DAO.getEntityById(id);
    }

    public void saveCountry(Country country){
        I_COUNTRY_DAO.saveEntity(country);
    }

    public void updateCountry(Country country){
        I_COUNTRY_DAO.updateEntity(country);
    }

    public void removeCountryById(int id){
        I_COUNTRY_DAO.removeEntityByID(id);
    }
}
