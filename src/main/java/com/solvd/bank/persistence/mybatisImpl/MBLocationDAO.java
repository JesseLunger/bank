package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ILocationDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;

import java.util.List;

public class MBLocationDAO implements ILocationDAO {

    private ILocationDAO mapper;

    public MBLocationDAO() {
        mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(ILocationDAO.class);
    }

    @Override
    public void updateCity(Location location, City city) {
        location.setCity(city);
        updateEntity(location);
    }

    @Override
    public List<Location> getAll() {
        return mapper.getAll();
    }

    @Override
    public Location getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(Location location) {
        mapper.saveEntity(location);
    }

    @Override
    public void updateEntity(Location location) {
        mapper.updateEntity(location);
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }


}
