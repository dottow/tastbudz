package com.tastbudz.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="tstbdz_dish")
public class Dish extends AbstractConsumable {
	private static final long serialVersionUID = -1571152603252394186L;
}
