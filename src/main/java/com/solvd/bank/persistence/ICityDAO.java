package com.solvd.bank.persistence;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Location;

import java.util.ArrayList;

public interface ICityDAO extends IBaseDAO<City> {

    public ArrayList<Location> getLocationsByCity(City city);
}
