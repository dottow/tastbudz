package com.tastbudz.model;

import java.io.Serializable;
import java.util.Date;


public interface Entity extends Serializable {
	public ID getId();
	public int getVersion();
    public Date getDateCreated();
    public Date getDateUpdated();
    public Entity makeQueryCriteria();
}
