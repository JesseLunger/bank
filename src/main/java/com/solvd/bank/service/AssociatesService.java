package com.solvd.bank.service;

import com.solvd.bank.domain.Associate;
import com.solvd.bank.persistence.IAssociateDAO;
import com.solvd.bank.persistence.impl.AssociateDAO;

import java.util.ArrayList;
import java.util.List;

public class AssociatesService {
    private final IAssociateDAO I_ASSOCIATE_DOA;

    public ArrayList<Associate> getAllByLocationId(int id){
        return I_ASSOCIATE_DOA.getAllByLocationId(id);
    }

    public AssociatesService(){
        I_ASSOCIATE_DOA = new AssociateDAO();
    }

    public List<Associate> getAllAssociates(){
        return I_ASSOCIATE_DOA.getAll();
    }

    public Associate getAssociateById(int id){
        return I_ASSOCIATE_DOA.getEntityById(id);
    }

    public void saveAssociate(Associate associate){
        I_ASSOCIATE_DOA.saveEntity(associate);
    }

    public void updateAssociates(Associate associate){
        I_ASSOCIATE_DOA.updateEntity(associate);
    }

    public void removeAssociatesById(int id){
        I_ASSOCIATE_DOA.removeEntityByID(id);
    }
}
