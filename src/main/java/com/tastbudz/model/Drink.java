package com.tastbudz.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tstbdz_drink")
public class Drink extends AbstractConsumable {
	private static final long serialVersionUID = -1250636411261888003L;
}
