package com.tastbudz.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public interface Entity extends PropertyList, Serializable {
	public ID getId();
	public int getVersion();
    public Date getDateCreated();
    public Date getDateUpdated();
    public Entity makeQueryCriteria();
}
