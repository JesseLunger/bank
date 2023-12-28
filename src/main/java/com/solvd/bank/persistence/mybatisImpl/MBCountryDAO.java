package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Country;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ICountryDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class MBCountryDAO extends MBBaseClassDAO implements ICountryDAO {

    private final SqlSessionFactory sqlSessionFactory = MyBatisSQLFactory.getSqlSessionFactory();

    @Override
    public void saveEntity(Country country) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICountryDAO countryDAO = session.getMapper(ICountryDAO.class);
            countryDAO.saveEntity(country);
            session.commit();
        }
    }

    @Override
    public Country getEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICountryDAO countryDAO = session.getMapper(ICountryDAO.class);
            return countryDAO.getEntityById(id);
        }
    }

    @Override
    public void updateEntity(Country country) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICountryDAO countryDAO = session.getMapper(ICountryDAO.class);
            countryDAO.updateEntity(country);
            session.commit();
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICountryDAO countryDAO = session.getMapper(ICountryDAO.class);
            countryDAO.removeEntityById(id);
            session.commit();
        }
    }

    @Override
    public List<Country> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICountryDAO countryDAO = session.getMapper(ICountryDAO.class);
            return countryDAO.getAll();
        }
    }

    @Override
    public ArrayList<Location> getAllLocationsByCountry(Country country) {
        // Implement the logic to get all locations by country
        return null;
    }
}
