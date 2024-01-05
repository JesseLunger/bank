package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Associate;
import com.solvd.bank.persistence.IAssociateDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;

import java.util.ArrayList;
import java.util.List;

public class AssociateDAO implements IAssociateDAO {

    private IAssociateDAO mapper;

    public AssociateDAO() {
        mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(IAssociateDAO.class);
    }

    @Override
    public ArrayList<Associate> getAllAssociatesByLocationId(int id) {
        return mapper.getAllAssociatesByLocationId(id);
    }

    @Override
    public List<Associate> getAll() {
        return mapper.getAll();
    }

    @Override
    public Associate getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(Associate associate) {
        mapper.saveEntity(associate);
    }

    @Override
    public void updateEntity(Associate associate) {
        mapper.updateEntity(associate);
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }
}
