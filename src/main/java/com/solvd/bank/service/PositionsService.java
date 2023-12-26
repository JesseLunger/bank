package com.solvd.bank.service;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.domain.Position;
import com.solvd.bank.persistence.IPositionDAO;
import com.solvd.bank.persistence.impl.PositionDAO;

import java.util.ArrayList;
import java.util.List;

public class PositionsService {

    private final IPositionDAO I_POSITION_DAO;

    public PositionsService() {
        this.I_POSITION_DAO = new PositionDAO();
    }

    public ArrayList<Branch> getBranchWithMissingPosition(Position position){
        return I_POSITION_DAO.getBranchWithMissingPosition(position);
    }

    public List<Position> getAllPositions() {
        return I_POSITION_DAO.getAll();
    }

    public Position getPositionById(int id) {
        return I_POSITION_DAO.getEntityById(id);
    }

    public void savePosition(Position position) {
        I_POSITION_DAO.saveEntity(position);
    }

    public void updatePosition(Position position) {
        I_POSITION_DAO.updateEntity(position);
    }

    public void removePositionById(int id) {
        I_POSITION_DAO.removeEntityByID(id);
    }
}
