package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Position;
import com.solvd.bank.domain.Staff;
import com.solvd.bank.persistence.IStaffDAO;

import java.util.List;

public class MBStaffDAO extends MBBaseClassDAO implements IStaffDAO {

    @Override
    public void updatePosition(Staff staff, Position position) {

    }

    @Override
    public void saveEntity(Staff staff) {

    }

    @Override
    public Staff getEntityById(int id) {
        return null;
    }

    @Override
    public void updateEntity(Staff staff) {

    }

    @Override
    public void removeEntityById(int id) {

    }

    @Override
    public List<Staff> getAll() {
        return null;
    }
}
