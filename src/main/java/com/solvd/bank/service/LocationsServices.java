package com.solvd.bank.service;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ILocationDAO;
import com.solvd.bank.persistence.impl.LocationDAO;

import java.util.List;

public class LocationsServices {

    private final ILocationDAO I_LOCATION_DAO;

    public LocationsServices(){
        I_LOCATION_DAO = new LocationDAO();
    }

    public void updateCity(Location location, City city){
        I_LOCATION_DAO.updateCity(location, city);
    }

    public List<Location> getAllLocations(){
        return I_LOCATION_DAO.getAll();
    }

    public Location getLocationById(int id){
        return I_LOCATION_DAO.getEntityById(id);
    }

    public void saveLocation(Location location){
        I_LOCATION_DAO.saveEntity(location);
    }

    public void updateLocation(Location location){
        I_LOCATION_DAO.updateEntity(location);
    }

    public void removeLocationById(int id){
        I_LOCATION_DAO.removeEntityByID(id);
    }
}
