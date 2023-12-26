package com.solvd.bank.persistence;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.domain.BranchHasEmployee;
import com.solvd.bank.domain.Staff;

import java.util.ArrayList;

public interface IBranchHasEmployeeDAO extends IBaseDAO<BranchHasEmployee> {

    public ArrayList<Staff> getAllStaffByBranch(Branch branch);
}
