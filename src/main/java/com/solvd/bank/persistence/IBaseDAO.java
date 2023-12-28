package com.solvd.bank.persistence;

import java.util.List;

public interface IBaseDAO<Entity> {

    void saveEntity(Entity entity);

    Entity getEntityById(int id);

    void updateEntity(Entity entity);

    void removeEntityById(int id);

    List<Entity> getAll();

}
