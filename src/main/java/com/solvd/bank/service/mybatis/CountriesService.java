package com.solvd.bank.service.mybatis;

import com.solvd.bank.domain.Country;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ICountryDAO;

import java.util.ArrayList;
import java.util.List;

public class CountriesService {
    private ICountryDAO countryDAO;

    public CountriesService() {
        this.countryDAO = new MBCountryDAO();
    }

    public ArrayList<Location> getAllLocationsByCountry(Country country) {
        return countryDAO.getAllLocationsByCountry(country);
    }

    public List<Country> getAllCountries() {
        return countryDAO.getAll();
    }

    public Country getCountryById(int id) {
        return countryDAO.getEntityById(id);
    }

    public void saveCountry(Country country) {
        countryDAO.saveEntity(country);
    }

    public void updateCountry(Country country) {
        countryDAO.updateEntity(country);
    }

    public void removeCountryById(int id) {
        countryDAO.removeEntityById(id);
    }
}
