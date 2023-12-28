package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ILocationDAO;

import java.util.List;

public class MBLocationDAO extends MBBaseClassDAO implements ILocationDAO {
    @Override
    public void saveEntity(Location location) {

    }

    @Override
    public Location getEntityById(int id) {
        return null;
    }

    @Override
    public void updateEntity(Location location) {

    }

    @Override
    public void removeEntityById(int id) {

    }

    @Override
    public List<Location> getAll() {
        return null;
    }

    @Override
    public void updateCity(Location location, City city) {

    }
}
