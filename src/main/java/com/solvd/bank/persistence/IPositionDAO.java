package com.solvd.bank.persistence;

import com.solvd.bank.domain.Branch;
import com.solvd.bank.domain.Position;

import java.util.ArrayList;

public interface IPositionDAO extends IBaseDAO<Position> {

    ArrayList<Branch> getBranchesWithMissingPosition(String positionName);

}
