package com.solvd.bank.service;

import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.LocationsRepository;
import com.solvd.bank.persistence.impl.LocationsJdbcImpl;

import java.util.List;

public class LocationsServices {

    private final LocationsRepository locationsRepository;

    public LocationsServices(){
        locationsRepository = new LocationsJdbcImpl();
    }

    public List<Location> getAllLocations(){
        return locationsRepository.getAll();
    }

    public Location getLocationById(int id){
        return locationsRepository.getEntityById(id);
    }

    public void saveLocation(Location location){
        locationsRepository.saveEntity(location);
    }

    public void updateLocation(Location location){
        locationsRepository.updateEntity(location);
    }

    public void removeLocationById(int id){
        locationsRepository.removeEntityByID(id);
    }
}
