package com.solvd.bank.service;

import com.solvd.bank.domain.Associate;
import com.solvd.bank.persistence.IAssociateDAO;
import com.solvd.bank.persistence.impl.AssociateDAO;

import java.util.ArrayList;
import java.util.List;

public class AssociatesService {
    private IAssociateDAO associateDAO;

    public ArrayList<Associate> getAllByLocationId(int id){
        return associateDAO.getAllByLocationId(id);
    }

    public AssociatesService(){
        associateDAO = new AssociateDAO();
    }

    public List<Associate> getAllAssociates(){
        return associateDAO.getAll();
    }

    public Associate getAssociateById(int id){
        return associateDAO.getEntityById(id);
    }

    public void saveAssociate(Associate associate){
        associateDAO.saveEntity(associate);
    }

    public void updateAssociates(Associate associate){
        associateDAO.updateEntity(associate);
    }

    public void removeAssociatesById(int id){
        associateDAO.removeEntityByID(id);
    }
}
