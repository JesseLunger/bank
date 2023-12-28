package com.solvd.bank.service.mybatis;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.persistence.IBranchDAO;
import com.solvd.bank.persistence.impl.BranchDAO;

import java.util.ArrayList;
import java.util.List;

public class MBBranchesService {

    private IBranchDAO branchDAO;

    public MBBranchesService() {
        branchDAO = new BranchDAO();
    }

    public ArrayList<Branch> getAllByLocationId(int id) {
        return branchDAO.getAllByLocationId(id);
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