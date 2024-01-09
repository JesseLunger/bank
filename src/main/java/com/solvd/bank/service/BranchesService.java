package com.solvd.bank.service;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.persistence.IBranchDAO;
import com.solvd.bank.persistence.mybatisImpl.BranchDAO;

import java.util.ArrayList;
import java.util.List;

public class BranchesService {

    private IBranchDAO branchDAO;

    public BranchesService() {
        branchDAO = new BranchDAO();
    }

    public ArrayList<Branch> getAllByLocationId(int id) {
        return branchDAO.getAllBranchesByLocationId(id);
    }

    public List<Branch> getAllBranches() {
        return branchDAO.getAll();
    }

    public Branch getBranchById(int id) {
        return branchDAO.getEntityById(id);
    }

    public void saveBranch(Branch branch) {
        branchDAO.saveEntity(branch);
    }

    public void updateBranch(Branch branch) {
        branchDAO.updateEntity(branch);
    }

    public void removeBranchById(int id) {
        branchDAO.removeEntityById(id);
    }
}
