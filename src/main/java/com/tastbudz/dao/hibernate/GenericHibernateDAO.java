package com.tastbudz.dao.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.tastbudz.dao.GenericDAO;
import com.tastbudz.model.Entity;
import com.tastbudz.model.ID;

public abstract class GenericHibernateDAO<T extends Entity, PK extends ID> implements GenericDAO<T, PK> {
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
    
	@SuppressWarnings("unchecked")
	public List<T> getByExample(T exampleInstance) {
		exampleInstance = (T)exampleInstance.makeQueryCriteria();
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
		Example example =  Example.create(exampleInstance);
		crit.add(example);
		return crit.list();
	}

}
