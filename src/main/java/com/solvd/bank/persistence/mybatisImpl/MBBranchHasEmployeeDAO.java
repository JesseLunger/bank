package com.solvd.bank.persistence.mybatisImpl;

import com.solvd.bank.domain.BranchHasEmployee;
import com.solvd.bank.domain.Staff;
import com.solvd.bank.persistence.IBranchHasEmployeeDAO;
import com.solvd.bank.utils.MyBatisSQLFactory;

import java.util.ArrayList;
import java.util.List;

public class MBBranchHasEmployeeDAO implements IBranchHasEmployeeDAO {

    private IBranchHasEmployeeDAO mapper;

    public MBBranchHasEmployeeDAO() {
        mapper = MyBatisSQLFactory.getSqlSessionFactory().openSession(true).getMapper(IBranchHasEmployeeDAO.class);
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
        new MBStaffDAO().updateEntity(branchHasEmployee.getStaff());
        new MBBranchDAO().updateEntity(branchHasEmployee.getBranch());
    }

    @Override
    public void removeEntityById(int id) {
        mapper.removeEntityById(id);
    }
}
