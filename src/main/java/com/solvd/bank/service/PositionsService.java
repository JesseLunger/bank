package com.solvd.bank.service;

import com.solvd.bank.domain.Position;
import com.solvd.bank.persistence.PositionsRepository;
import com.solvd.bank.persistence.impl.PositionsJdbcImpl;

import java.util.List;

public class PositionsService {

    private final PositionsRepository positionsRepository;

    public PositionsService() {
        this.positionsRepository = new PositionsJdbcImpl();
    }

    public List<Position> getAllPositions() {
        return positionsRepository.getAll();
    }

    public Position getPositionById(int id) {
        return positionsRepository.getEntityById(id);
    }

    public void savePosition(Position position) {
        positionsRepository.saveEntity(position);
    }

    public void updatePosition(Position position) {
        positionsRepository.updateEntity(position);
    }

    public void removePositionById(int id) {
        positionsRepository.removeEntityByID(id);
    }
}
