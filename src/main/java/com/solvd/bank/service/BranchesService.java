package com.solvd.bank.service;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.domain.Location;
import com.solvd.bank.persistence.IBranchDAO;
import com.solvd.bank.persistence.impl.BranchDAO;

import java.util.ArrayList;
import java.util.List;

public class BranchesService {

    private final IBranchDAO I_BRANCH_DOA;

    public BranchesService(){
        I_BRANCH_DOA = new BranchDAO();
    }

    public ArrayList<Branch> getAllByLocationId(int id){
        return I_BRANCH_DOA.getAllByLocationId(id);
    }

    public List<Branch> getAllBranches(){
        return I_BRANCH_DOA.getAll();
    }

    public Branch getBranchById(int id){
        return I_BRANCH_DOA.getEntityById(id);
    }

    public void saveBranch(Branch branch){
        I_BRANCH_DOA.saveEntity(branch);
    }

    public void updateBranch(Branch branch){
        I_BRANCH_DOA.updateEntity(branch);
    }

    public void removeBranchById(int id){
        I_BRANCH_DOA.removeEntityByID(id);
    }
}
