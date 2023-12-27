package com.solvd.bank.persistence;

import com.solvd.bank.domain.Branch;

import java.util.ArrayList;

public interface IBranchDAO extends IBaseDAO<Branch> {

    ArrayList<Branch> getAllByLocationId(int id);

}
