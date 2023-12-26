package com.solvd.bank.service;

import com.solvd.bank.domain.Staff;
import com.solvd.bank.persistence.IStaffDAO;
import com.solvd.bank.persistence.impl.StaffDAO;

import java.util.List;

public class StaffService {

    private final IStaffDAO I_STAFF_DAO;

    public StaffService() {
        this.I_STAFF_DAO = new StaffDAO();
    }

    public List<Staff> getAllStaff() {
        return I_STAFF_DAO.getAll();
    }

    public Staff getStaffById(int id) {
        return I_STAFF_DAO.getEntityById(id);
    }

    public void saveStaff(Staff staff) {
        I_STAFF_DAO.saveEntity(staff);
    }

    public void updateStaff(Staff staff) {
        I_STAFF_DAO.updateEntity(staff);
    }

    public void removeStaffById(int id) {
        I_STAFF_DAO.removeEntityByID(id);
    }
}
