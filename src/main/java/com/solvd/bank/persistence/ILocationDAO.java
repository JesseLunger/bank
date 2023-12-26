package com.solvd.bank.persistence;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Country;
import com.solvd.bank.domain.Location;

public interface ILocationDAO extends IBaseDAO<Location> {

    public void updateCity(Location location, City city);
}
