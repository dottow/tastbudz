package com.tastbudz.model;

public abstract class Image extends PersistentEntity {
    private Viewable viewable;

	public Viewable getViewable() {
		return viewable;
	}

	public void setViewable(Viewable viewable) {
		this.viewable = viewable;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
