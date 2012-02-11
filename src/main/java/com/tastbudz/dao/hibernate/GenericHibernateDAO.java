package com.tastbudz.dao.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tastbudz.dao.GenericDAO;
import com.tastbudz.model.PersistentEntity;
import com.tastbudz.model.id.ID;

@Repository
public abstract class GenericHibernateDAO<T extends PersistentEntity, PK extends ID> implements GenericDAO<T, PK> {
	private Class<T> persistentClass;
	@Autowired
	protected SessionFactory sessionFactory;
 
    public GenericHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                                .getGenericSuperclass()).getActualTypeArguments()[0];
     }
 
    @Autowired
    @Required
    public void setSessionFactory(SessionFactory factory) {
    	this.sessionFactory = factory;
    }
 
    public Class<T> getPersistentClass() {
        return persistentClass;
    }
 
    @SuppressWarnings("unchecked")
    public T read(PK id) {
    	try {
    		return (T) sessionFactory.getCurrentSession().load(getPersistentClass(), id, LockOptions.READ);
    	}
    	catch (ObjectNotFoundException onfe) {
    		return null;
    	}
    }    
    
    @SuppressWarnings("unchecked")
    public T save(T entity) {
    	sessionFactory.getCurrentSession().merge(entity);
         return entity;
    }
    
    @SuppressWarnings("unchecked")
    public void delete(T entity) {
    	sessionFactory.getCurrentSession().delete(entity);
    }
}
