package com.tastbudz.dao;

import com.tastbudz.model.PersistentEntity;
import com.tastbudz.model.id.ID;

public interface GenericDAO<T extends PersistentEntity, PK extends ID> {
	T save(T entity);
		
	void delete (T entity);
	
    T read(PK id);
}
