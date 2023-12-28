package com.solvd.bank.service.mybatis;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.domain.Position;
import com.solvd.bank.persistence.IPositionDAO;
import com.solvd.bank.persistence.impl.PositionDAO;

import java.util.ArrayList;
import java.util.List;

public class MBPositionsService {

    private IPositionDAO positionDAO;

    public MBPositionsService() {
        this.positionDAO = new PositionDAO();
    }

    public ArrayList<Branch> getBranchWithMissingPosition(Position position) {
        return positionDAO.getBranchWithMissingPosition(position);
    }

    public List<Position> getAllPositions() {
        return positionDAO.getAll();
    }

    public Position getPositionById(int id) {
        return positionDAO.getEntityById(id);
    }

    public void savePosition(Position position) {
        positionDAO.saveEntity(position);
    }

    public void updatePosition(Position position) {
        positionDAO.updateEntity(position);
    }

    public void removePositionById(int id) {
        positionDAO.removeEntityById(id);
    }
}
