package com.solvd.bank.service;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.domain.BranchHasEmployee;
import com.solvd.bank.domain.Staff;
import com.solvd.bank.persistence.IBranchHasEmployeeDAO;
import com.solvd.bank.persistence.impl.BranchHasEmployeeDAO;

import java.util.ArrayList;
import java.util.List;

public class BranchHasEmployeesService {

    private final IBranchHasEmployeeDAO I_BRANCH_HAS_EMPLOYEE_DAO;

    public BranchHasEmployeesService() {
        this.I_BRANCH_HAS_EMPLOYEE_DAO = new BranchHasEmployeeDAO();
    }

    public ArrayList<Staff> getAllByBranch(Branch branch){
        return I_BRANCH_HAS_EMPLOYEE_DAO.getAllStaffByBranch(branch);
    }

    public List<BranchHasEmployee> getAllBranchHasEmployees() {
        return I_BRANCH_HAS_EMPLOYEE_DAO.getAll();
    }

    public BranchHasEmployee getBranchHasEmployeesById(int id) {
        return I_BRANCH_HAS_EMPLOYEE_DAO.getEntityById(id);
    }

    public void saveBranchHasEmployees(BranchHasEmployee branchHasEmployee) {
        I_BRANCH_HAS_EMPLOYEE_DAO.saveEntity(branchHasEmployee);
    }

    public void updateBranchHasEmployees(BranchHasEmployee branchHasEmployee) {
        I_BRANCH_HAS_EMPLOYEE_DAO.updateEntity(branchHasEmployee);
    }

    public void removeBranchHasEmployeesById(int id) {
        I_BRANCH_HAS_EMPLOYEE_DAO.removeEntityByID(id);
    }
}
