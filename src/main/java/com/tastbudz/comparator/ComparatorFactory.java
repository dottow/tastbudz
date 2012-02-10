package com.tastbudz.comparator;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.tastbudz.model.Cuisine;

public class ComparatorFactory {
	private static Map<Class,Comparator> comparatorMap = new HashMap<Class,Comparator>();
	
	public static Comparator getComparator(Class clazz) {
		Comparator instance = comparatorMap.get(clazz);
		if (instance == null) {
			instance = createInstance(clazz);
			comparatorMap.put(clazz, instance);
		}
		return instance;
	}
	
	private static Comparator createInstance(Class clazz) {
		String name = clazz.getSimpleName();
		String packageName = ComparatorFactory.class.getPackage().getName();
		String comparatorName = packageName + "." + name + "Comparator";
		
		
		try {
			Class comparatorClass = Class.forName(comparatorName);
			return (Comparator)comparatorClass.newInstance();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public static void main(String[] args) {
		ComparatorFactory.getComparator(Cuisine.class);
	}
}
