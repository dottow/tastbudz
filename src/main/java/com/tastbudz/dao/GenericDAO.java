package com.tastbudz.dao;

import java.util.List;

import com.tastbudz.model.Entity;

public interface GenericDAO<T extends Entity, ID> {
	T save(T entity);
		
	void delete (T entity);
	
    T read(ID id);
    
    List<T> getByExample(T example);
}
