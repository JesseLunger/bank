package com.solvd.bank.service;

import com.solvd.bank.domain.BranchHasEmployee;
import com.solvd.bank.persistence.BranchHasEmployeesRepository;
import com.solvd.bank.persistence.impl.BranchHasEmployeesJdbcImpl;

import java.util.List;

public class BranchHasEmployeesService {

    private final BranchHasEmployeesRepository branchHasEmployeesRepository;

    public BranchHasEmployeesService() {
        this.branchHasEmployeesRepository = new BranchHasEmployeesJdbcImpl();
    }

    public List<BranchHasEmployee> getAllBranchHasEmployees() {
        return branchHasEmployeesRepository.getAll();
    }

    public BranchHasEmployee getBranchHasEmployeesById(int id) {
        return branchHasEmployeesRepository.getEntityById(id);
    }

    public void saveBranchHasEmployees(BranchHasEmployee branchHasEmployee) {
        branchHasEmployeesRepository.saveEntity(branchHasEmployee);
    }

    public void updateBranchHasEmployees(BranchHasEmployee branchHasEmployee) {
        branchHasEmployeesRepository.updateEntity(branchHasEmployee);
    }

    public void removeBranchHasEmployeesById(int id) {
        branchHasEmployeesRepository.removeEntityByID(id);
    }
}
