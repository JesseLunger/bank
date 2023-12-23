package com.solved.bank.persistence;

import java.util.List;

public interface IBaseRepository<Entity> {

    void saveEntity(Entity entity);
    Entity getEntityById(int id);
    void updateEntity(Entity entity);
    void removeEntityByID(int id);
    List<Entity> getAll();

}
