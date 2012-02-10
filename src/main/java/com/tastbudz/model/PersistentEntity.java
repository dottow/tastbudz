package com.tastbudz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.jboss.logging.Logger;

import com.tastbudz.model.id.ID;
import com.tastbudz.model.id.IDFactory;

@MappedSuperclass
public abstract class PersistentEntity implements Entity, Cloneable {
    private static final Logger LOG = Logger.getLogger(PersistentEntity.class);

	@Id
	@Type(type="com.tastbudz.dao.hibernate.usertype.IDUserType")
	@Column(name="id", nullable=false)
	private ID id;
	@Version
	@Column(name="version", nullable=false)
	private int version;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created", nullable=false)
    private Date dateCreated;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_updated", nullable=true)
    private Date dateUpdated;
    
	public PersistentEntity() {
		this.id = IDFactory.createID(this);
		this.version = 0;
		this.dateCreated = new Date();
	}
	
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Date getDateUpdated() {
		return dateUpdated;
	}
	
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||
            !(o instanceof Entity)) {

            return false;
        }

        Entity other = (Entity)o;

        // if the id is missing, return false
        if (id == null) return false;

        // equivalence by id
        return id.equals(other.getId());
    }

    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return super.hashCode();
        }
    }

    public String toString() {
        return this.getClass().getName()
            + "[id=" + id + "]";
    }
    
    /**
     * Utility method to nullify all object fields so they are not used
     * during persistence query.   These are fields that shouldn't be used
     * to discriminate when searching for an entity object based on some
     * business attributes
     */
    public PersistentEntity makeQueryCriteria() {
    	PersistentEntity criteria;
		try {
			criteria = (PersistentEntity)this.clone();
	    	criteria.id = null;
	    	criteria.version = 0;
	    	criteria.dateCreated = null;
	    	criteria.dateUpdated = null;
	    	return criteria;
		} 
		catch (CloneNotSupportedException e) {
			LOG.error("Clone not supported for persistent entity: "+getClass().getName(), e);
			return null;
		}
    }
}
