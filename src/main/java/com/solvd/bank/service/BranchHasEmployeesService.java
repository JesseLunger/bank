package com.solvd.bank.service;

import com.solvd.bank.domain.BranchHasEmployee;
import com.solvd.bank.domain.Staff;
import com.solvd.bank.persistence.IBranchHasEmployeeDAO;
import com.solvd.bank.persistence.mybatisImpl.BranchHasEmployeeDAO;

import java.util.ArrayList;
import java.util.List;

public class BranchHasEmployeesService {

    private IBranchHasEmployeeDAO branchHasEmployeeDAO;

    public BranchHasEmployeesService() {
        this.branchHasEmployeeDAO = new BranchHasEmployeeDAO();
    }

    public ArrayList<Staff> getAllByBranchId(int branchId) {
        return branchHasEmployeeDAO.getAllStaffByBranchId(branchId);
    }

    public List<BranchHasEmployee> getAllBranchHasEmployees() {
        return branchHasEmployeeDAO.getAll();
    }

    public BranchHasEmployee getBranchHasEmployeesById(int id) {
        return branchHasEmployeeDAO.getEntityById(id);
    }

    public void saveBranchHasEmployees(BranchHasEmployee branchHasEmployee) {
        branchHasEmployeeDAO.saveEntity(branchHasEmployee);
    }

    public void updateBranchHasEmployees(BranchHasEmployee branchHasEmployee) {
        branchHasEmployeeDAO.updateEntity(branchHasEmployee);
    }

    public void removeBranchHasEmployeesById(int id) {
        branchHasEmployeeDAO.removeEntityById(id);
    }
}
