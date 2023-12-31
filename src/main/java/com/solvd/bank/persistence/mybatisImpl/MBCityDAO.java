// CityDAO.java
package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.City;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.ICityDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class MBCityDAO implements ICityDAO {

    private final SqlSession sqlSession;

    public MBCityDAO() {
        this.sqlSession = MyBatisSQLFactory.getSqlSessionFactory().openSession(true);
    }

    @Override
    public ArrayList<Location> getLocationsByCity(City city) {
        List<Location> locations = sqlSession.selectList("com.solvd.bank.persistence.ICityDAO.getLocationsByCity", city.getId());
        return new ArrayList<>(locations);
    }

    @Override
    public List<City> getAll() {
        return sqlSession.selectList("com.solvd.bank.persistence.ICityDAO.getAll");
    }

    @Override
    public City getEntityById(int id) {
        return sqlSession.selectOne("com.solvd.bank.persistence.ICityDAO.getEntityById", id);
    }

    @Override
    public void saveEntity(City city) {
        sqlSession.insert("com.solvd.bank.persistence.ICityDAO.saveEntity", city);
    }

    @Override
    public void updateEntity(City city) {
        sqlSession.update("com.solvd.bank.persistence.ICityDAO.updateEntity", city);
    }

    @Override
    public void removeEntityById(int id) {
        sqlSession.delete("com.solvd.bank.persistence.ICityDAO.removeEntityById", id);
    }
}
