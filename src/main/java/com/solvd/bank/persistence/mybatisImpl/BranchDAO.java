package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.persistence.IBranchDAO;
import com.solvd.bank.utils.MySQLFactory;

import java.util.ArrayList;
import java.util.List;

public class BranchDAO implements IBranchDAO {

    private IBranchDAO mapper;

    public BranchDAO() {
        mapper = MySQLFactory.getSqlSessionFactory().openSession(true).getMapper(IBranchDAO.class);
    }

    @Override
    public ArrayList<Branch> getAllBranchesByLocationId(int id) {
        BranchHasEmployeeDAO branchHasEmployeeDAO = new BranchHasEmployeeDAO();
        ArrayList<Branch> branches = new ArrayList<>(getAll());
        for (Branch branch : branches) {
            branch.setBranchStaff(branchHasEmployeeDAO.getAllStaffByBranchId(branch.getId()));
        }
        return branches;
    }

    @Override
    public List<Branch> getAll() {
        return mapper.getAll();
    }

    @Override
    public Branch getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(Branch branch) {
        mapper.saveEntity(branch);
    }

    @Override
    public void updateEntity(Branch branch) {
        mapper.updateEntity(branch);
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }

}
