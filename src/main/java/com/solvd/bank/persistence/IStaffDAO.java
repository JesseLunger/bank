package com.solvd.bank.persistence;

import com.solvd.bank.domain.Position;
import com.solvd.bank.domain.Staff;

public interface IStaffDAO extends IBaseDAO<Staff> {

    void updatePosition(Staff staff, Position position);
}
