package com.tastbudz.model;

public interface Rating extends Taggable {
	  public Consumable getConsumable();
	  public User getUser();
	  public double getOverall();
	  public double getTaste();
	  public double getPresentation();
	  public double getComposition();
	  public double getValue();
	  public String getReview();
}
