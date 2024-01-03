// CountryDAO.java
package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Country;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ICountryDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;

import java.util.ArrayList;
import java.util.List;

public class MBCountryDAO implements ICountryDAO {

    private ICountryDAO mapper;

    public MBCountryDAO() {
        this.mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(ICountryDAO.class);
    }

    @Override
    public ArrayList<Location> getAllLocationsByCountry(Country country) {
        return mapper.getAllLocationsByCountry(country);
    }

    @Override
    public List<Country> getAll() {
        return mapper.getAll();
    }

    @Override
    public Country getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(Country country) {
        mapper.saveEntity(country);
    }

    @Override
    public void updateEntity(Country country) {
        mapper.updateEntity(country);
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }


}
