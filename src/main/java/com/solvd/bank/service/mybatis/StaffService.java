package com.solvd.bank.service.mybatis;

import com.solvd.bank.domain.Staff;
import com.solvd.bank.persistence.IStaffDAO;

import java.util.List;

public class StaffService {

    private IStaffDAO staffDAO;

    public StaffService() {
        this.staffDAO = new MBStaffDAO();
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
