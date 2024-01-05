package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Position;
import com.solvd.bank.domain.Staff;
import com.solvd.bank.persistence.IStaffDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;

import java.util.List;

public class StaffDAO implements IStaffDAO {

    private IStaffDAO mapper;

    public StaffDAO() {
        mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(IStaffDAO.class);
    }

    @Override
    public void updatePosition(Staff staff, Position position) {
        staff.setPosition(position);
        mapper.updateEntity(staff);
    }

    @Override
    public List<Staff> getAll() {
        return mapper.getAll();
    }

    @Override
    public Staff getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(Staff staff) {
        mapper.saveEntity(staff);
    }

    @Override
    public void updateEntity(Staff staff) {
        mapper.updateEntity(staff);
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }


}
