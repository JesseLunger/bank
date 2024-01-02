package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ICityDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;

import java.util.ArrayList;
import java.util.List;

public class MBCityDAO implements ICityDAO {

    private ICityDAO mapper;

    public MBCityDAO() {
        this.mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(ICityDAO.class);
    }

    @Override
    public ArrayList<Location> getLocationsByCity(City city) {
        return mapper.getLocationsByCity(city);
    }

    @Override
    public List<City> getAll() {
        return mapper.getAll();
    }

    @Override
    public City getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(City city) {
        mapper.saveEntity(city);
    }

    @Override
    public void updateEntity(City city) {
        mapper.updateEntity(city);
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }
}
