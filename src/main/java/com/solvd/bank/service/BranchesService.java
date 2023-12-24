package com.solvd.bank.service;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.persistence.Branchesrepository;
import com.solvd.bank.persistence.impl.BranchesJdbcImpl;

import java.util.List;

public class BranchesService {
    private final Branchesrepository branchesrepository;

    public BranchesService(){
        branchesrepository = new BranchesJdbcImpl();
    }

    public List<Branch> getAllBranches(){
        return branchesrepository.getAll();
    }

    public Branch getBranchById(int id){
        return branchesrepository.getEntityById(id);
    }

    public void saveBranch(Branch branch){
        branchesrepository.saveEntity(branch);
    }

    public void updateBranch(Branch branch){
        branchesrepository.updateEntity(branch);
    }

    public void removeBranchById(int id){
        branchesrepository.removeEntityByID(id);
    }
}
