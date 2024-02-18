package com.solvd.bank.persistence;

import com.solvd.bank.domain.Location;

public interface ILocationDAO extends IBaseDAO<Location> {

    void updateCity(Location location);

}
