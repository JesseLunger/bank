package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.domain.Position;
import com.solvd.bank.persistence.IPositionDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;

import java.util.ArrayList;
import java.util.List;

public class MBPositionDOA implements IPositionDAO {

    private IPositionDAO mapper;

    public MBPositionDOA() {
        mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(IPositionDAO.class);
    }

    @Override
    public ArrayList<Branch> getBranchesWithMissingPosition(String positionName) {
        return mapper.getBranchesWithMissingPosition(positionName);
    }

    @Override
    public List<Position> getAll() {
        return mapper.getAll();
    }

    @Override
    public Position getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(Position position) {
        mapper.saveEntity(position);
    }

    @Override
    public void updateEntity(Position position) {
        mapper.updateEntity(position);
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }

}
