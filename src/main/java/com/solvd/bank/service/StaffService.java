package com.solvd.bank.service;

import com.solvd.bank.domain.Staff;
import com.solvd.bank.persistence.StaffRepository;
import com.solvd.bank.persistence.impl.StaffJdbcImpl;

import java.util.List;

public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService() {
        this.staffRepository = new StaffJdbcImpl();
    }

    public List<Staff> getAllStaff() {
        return staffRepository.getAll();
    }

    public Staff getStaffById(int id) {
        return staffRepository.getEntityById(id);
    }

    public void saveStaff(Staff staff) {
        staffRepository.saveEntity(staff);
    }

    public void updateStaff(Staff staff) {
        staffRepository.updateEntity(staff);
    }

    public void removeStaffById(int id) {
        staffRepository.removeEntityByID(id);
    }
}
