package com.solvd.bank.service;

import com.solvd.bank.domain.Staff;
import com.solvd.bank.persistence.IStaffDAO;
import com.solvd.bank.persistence.mybatisImpl.StaffDAO;

import java.util.List;

public class StaffService {

    private final IStaffDAO staffDAO;

    public StaffService() {
        this.staffDAO = new StaffDAO();
    }

    public List<Staff> getAllStaff() {
        return staffDAO.getAll();
    }

    public Staff getStaffById(int id) {
        return staffDAO.getEntityById(id);
    }

    public void saveStaff(Staff staff) {
        staffDAO.saveEntity(staff);
    }

    public void updateStaff(Staff staff) {
        staffDAO.updateEntity(staff);
    }

    public void removeStaffById(int id) {
        staffDAO.removeEntityById(id);
    }
}
