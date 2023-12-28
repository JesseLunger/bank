package com.solvd.bank.service.mybatis;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ICityDAO;
import com.solvd.bank.persistence.impl.CityDAO;

import java.util.ArrayList;
import java.util.List;

public class MBCitiesService {
    private ICityDAO cityDAO;

    public MBCitiesService() {
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

    public void SaveCity(City city) {
        cityDAO.saveEntity(city);
    }

    public void updateCity(City city) {
        cityDAO.updateEntity(city);
    }

    public void removeCityById(int id) {
        cityDAO.removeEntityById(id);
    }
}