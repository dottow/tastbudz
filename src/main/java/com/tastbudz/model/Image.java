package com.tastbudz.model;

public class Image extends PersistentEntity {
    private Viewable viewable;

	public Viewable getViewable() {
		return viewable;
	}

	public void setViewable(Viewable viewable) {
		this.viewable = viewable;
	}
}
