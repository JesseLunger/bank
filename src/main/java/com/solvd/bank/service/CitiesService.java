package com.solvd.bank.service;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ICityDAO;
import com.solvd.bank.persistence.impl.CityDAO;

import java.util.ArrayList;
import java.util.List;

public class CitiesService {
    private final ICityDAO I_CITY_DAO;

    public CitiesService(){
        I_CITY_DAO = new CityDAO();
    }

    public ArrayList<Location> getLocationsByCity(City city){
        return I_CITY_DAO.getLocationsByCity(city);
    }

    public List<City> getAllCities(){
        return I_CITY_DAO.getAll();
    }

    public City getCityById(int id){
        return I_CITY_DAO.getEntityById(id);
    }

    public void SaveCity(City city){
        I_CITY_DAO.saveEntity(city);
    }

    public void updateCity(City city){
        I_CITY_DAO.updateEntity(city);
    }

    public void removeCityById(int id){
        I_CITY_DAO.removeEntityByID(id);
    }
}
