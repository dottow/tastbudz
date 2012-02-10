package com.tastbudz.model.id;



public class IDFactory {
	private static IDGenerator instance=null;
	
	public static ID createID(Object persistentObject) {
		IDGenerator generator = getIDGenerator(persistentObject);
		return generator.createID(persistentObject);
	}
	
	private static IDGenerator getIDGenerator(Object persistentObject) {
		if (instance == null) {
			instance = new IDGenerator();
		}
		return instance;
	}
}
