package com.solvd.bank.service;

import com.solvd.bank.domain.Associate;
import com.solvd.bank.persistence.IAssociateDAO;
import com.solvd.bank.persistence.mybatisImpl.AssociateDAO;

import java.util.ArrayList;
import java.util.List;

public class AssociatesService {
    private final IAssociateDAO associateDAO = new AssociateDAO();

    public ArrayList<Associate> getAllByLocationId(int id) {
        return associateDAO.getAllAssociatesByLocationId(id);
    }

    public List<Associate> getAllAssociates() {
        return associateDAO.getAll();
    }

    public Associate getAssociateById(int id) {
        return associateDAO.getEntityById(id);
    }

    public void saveAssociate(Associate associate) {
        associateDAO.saveEntity(associate);
    }

    public void updateAssociates(Associate associate) {
        associateDAO.updateEntity(associate);
    }

    public void removeAssociatesById(int id) {
        associateDAO.removeEntityById(id);
    }
}
