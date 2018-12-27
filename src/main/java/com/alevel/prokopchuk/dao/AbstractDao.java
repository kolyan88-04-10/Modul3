package com.alevel.prokopchuk.dao;

import com.alevel.prokopchuk.entities.Entity;

public abstract class AbstractDao<K, T extends Entity> {
    public abstract boolean create(T entity);
    public abstract boolean delete(T entity);
}
