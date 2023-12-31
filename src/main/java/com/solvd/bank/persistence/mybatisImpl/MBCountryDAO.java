// CountryDAO.java
package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Country;
import com.solvd.bank.persistence.ICountryDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;

import java.util.List;

public class MBCountryDAO {

    private ICountryDAO mapper;

    public MBCountryDAO() {
        this.mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(ICountryDAO.class);
    }

    public List<Country> getAll() {
        return mapper.getAll();
    }

    public Country getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    public void saveEntity(Country country) {
        mapper.saveEntity(country);
    }

    public void updateEntity(Country country) {
        mapper.updateEntity(country);
    }

    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }
}
