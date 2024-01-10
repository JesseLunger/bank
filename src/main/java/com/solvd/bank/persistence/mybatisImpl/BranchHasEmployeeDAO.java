package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.BranchHasEmployee;
import com.solvd.bank.domain.Staff;
import com.solvd.bank.persistence.IBranchHasEmployeeDAO;
import com.solvd.bank.utils.MySQLFactory;

import java.util.ArrayList;
import java.util.List;

public class BranchHasEmployeeDAO implements IBranchHasEmployeeDAO {

    private final IBranchHasEmployeeDAO mapper;

    public BranchHasEmployeeDAO() {
        mapper = MySQLFactory.getSqlSessionFactory().openSession(true).getMapper(IBranchHasEmployeeDAO.class);
    }

    @Override
    public ArrayList<Staff> getAllStaffByBranchId(int id) {
        return mapper.getAllStaffByBranchId(id);
    }

    @Override
    public List<BranchHasEmployee> getAll() {
        return mapper.getAll();
    }

    @Override
    public BranchHasEmployee getEntityById(int id) {
        return mapper.getEntityById(id);
    }

    @Override
    public void saveEntity(BranchHasEmployee branchHasEmployee) {
        mapper.saveEntity(branchHasEmployee);
    }

    @Override
    public void updateEntity(BranchHasEmployee branchHasEmployee) {
        new StaffDAO().updateEntity(branchHasEmployee.getStaff());
        new BranchDAO().updateEntity(branchHasEmployee.getBranch());
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }
}
