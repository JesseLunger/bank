package com.solvd.bank.service;

import com.solvd.bank.domain.Associate;
import com.solvd.bank.persistence.AssociatesRepository;
import com.solvd.bank.persistence.impl.AssociatesJdbcImpl;

import java.util.List;

public class AssociatesService {
    private final AssociatesRepository associatesRepository;

    public AssociatesService(){
        associatesRepository = new AssociatesJdbcImpl();
    }

    public List<Associate> getAllAssociates(){
        return associatesRepository.getAll();
    }

    public Associate getAssociateById(int id){
        return associatesRepository.getEntityById(id);
    }

    public void saveAssociate(Associate associate){
        associatesRepository.saveEntity(associate);
    }

    public void updateAssociates(Associate associate){
        associatesRepository.updateEntity(associate);
    }

    public void removeAssociatesById(int id){
        associatesRepository.removeEntityByID(id);
    }
}
