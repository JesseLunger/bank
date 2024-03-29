package com.solvd.bank.service;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ICityDAO;
import com.solvd.bank.persistence.mybatisImpl.CityDAO;

import java.util.ArrayList;
import java.util.List;

public class CitiesService {
    private final ICityDAO cityDAO;

    public CitiesService() {
        cityDAO = new CityDAO();
    }

    public ArrayList<Location> getLocationsByCity(City city) {
        return cityDAO.getLocationsByCity(city);
    }

    public List<City> getAllCities() {
        return cityDAO.getAll();
    }

    public City getCityById(int id) {
        return cityDAO.getEntityById(id);
    }

    public void saveCity(City city) {
        cityDAO.saveEntity(city);
    }

    public void updateCity(City city) {
        cityDAO.updateEntity(city);
    }

    public void removeCityById(int id) {
        cityDAO.removeEntityById(id);
    }
}
