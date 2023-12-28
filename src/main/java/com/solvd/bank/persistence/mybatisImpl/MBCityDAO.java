package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ICityDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class MBCityDAO extends MBBaseClassDAO implements ICityDAO {

    private final SqlSessionFactory sqlSessionFactory = MyBatisSQLFactory.getSqlSessionFactory();


    @Override
    public void saveEntity(City city) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("com.solvd.bank.persistence.ICityDAO.saveEntity", city);
            session.commit();
        }
    }

    @Override
    public City getEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("com.solvd.bank.persistence.ICityDAO.getEntityById", id);
        }
    }

    @Override
    public void updateEntity(City city) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.update("com.solvd.bank.persistence.ICityDAO.updateEntity", city);
            session.commit();
        }
    }

    @Override
    public void removeEntityById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("com.solvd.bank.persistence.ICityDAO.removeEntityById", id);
            session.commit();
        }
    }

    @Override
    public List<City> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("com.solvd.bank.persistence.ICityDAO.getAll");
        }
    }

    @Override
    public ArrayList<Location> getLocationsByCity(City city) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return new ArrayList<>(session.selectList("com.solvd.bank.persistence.ICityDAO.getLocationsByCity", city.getId()));
        }
    }
}
