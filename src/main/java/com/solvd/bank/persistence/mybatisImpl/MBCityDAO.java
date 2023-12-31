package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ICityDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class MBCityDAO implements ICityDAO {

    private ICityDAO mapper;

    public MBCityDAO() {
        this.mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(ICityDAO.class);
    }

    @Override
    public ArrayList<Location> getLocationsByCity(City city) {
        // Implement this method
        return null;
    }

    @Override
    public List<City> getAll() {
        // Implement this method
        return null;
    }

    @Override
    public City getEntityById(int id) {
        // Implement this method
        return null;
    }

    @Override
    public void saveEntity(City city) {
        // Implement this method
    }

    @Override
    public void updateEntity(City city) {
        // Implement this method
    }

    @Override
    public void removeEntityById(int id) {
        // Implement this method
    }
}
