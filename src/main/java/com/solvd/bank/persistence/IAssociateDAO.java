package com.solvd.bank.persistence;

import com.solvd.bank.domain.Associate;

import java.util.ArrayList;

public interface IAssociateDAO extends IBaseDAO<Associate> {

    ArrayList<Associate> getAllByLocationId(int id);

}