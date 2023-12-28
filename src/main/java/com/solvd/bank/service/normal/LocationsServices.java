package com.solvd.bank.service.normal;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ILocationDAO;
import com.solvd.bank.persistence.impl.LocationDAO;

import java.util.List;

public class LocationsServices {

    private ILocationDAO locationDAO;

    public LocationsServices() {
        locationDAO = new LocationDAO();
    }

    public void updateCity(Location location, City city) {
        locationDAO.updateCity(location, city);
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
