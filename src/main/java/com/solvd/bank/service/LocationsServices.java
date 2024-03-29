package com.solvd.bank.service;

import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ILocationDAO;
import com.solvd.bank.persistence.mybatisImpl.LocationDAO;

import java.util.List;

public class LocationsServices {

    private final ILocationDAO locationDAO;

    public LocationsServices() {
        locationDAO = new LocationDAO();
    }

    public void updateCity(Location location) {
        locationDAO.updateCity(location);
    }

    public List<Location> getAllLocations() {
        return locationDAO.getAll();
    }

    public Location getLocationById(int id) {
        return locationDAO.getEntityById(id);
    }

    public void saveLocation(Location location) {
        locationDAO.saveEntity(location);
    }

    public void updateLocation(Location location) {
        locationDAO.updateEntity(location);
    }

    public void removeLocationById(int id) {
        locationDAO.removeEntityById(id);
    }
}
