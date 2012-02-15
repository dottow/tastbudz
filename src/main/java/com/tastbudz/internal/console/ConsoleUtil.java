package com.tastbudz.internal.console;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tastbudz.model.Entity;


public final class ConsoleUtil {
	public static Object chooseEntity(Console console, List objects) {
		if (objects.size() == 0) {
			console.printf("No match!");
			return null;
		}
		if (objects.size() == 1) {
			return objects.get(0);
		}
		
		Map<Integer,Object> indexMap = new HashMap<Integer,Object>();
		int index=1;
		for (Object object : objects) {
			String s = object.toString();
			int i = s.indexOf("\n");
			if (i >= 0) s = s.substring(0, i);
			console.printf("[%d] %s\n", index, s);
			indexMap.put(index, object);
			index++;
		}
		console.printf("Which one? ");
		String choice = console.readLine();
		if (StringUtils.isBlank(choice)) {
			return null;
		}

		try {
			index = Integer.parseInt(choice);
		}
		catch (NumberFormatException nfe) {
			console.printf("Invalid choice: %s\n", choice);
			return null;
		}
		
		return indexMap.get(index);
	}

	public static void buildEntity(Console console, Object entity) {
		try {
			for (PropertyDescriptor pd : Introspector.getBeanInfo(entity.getClass()).getPropertyDescriptors()) {
				if (pd.getReadMethod() != null && pd.getWriteMethod() != null && String.class.equals(pd.getPropertyType())) {
					String name = pd.getDisplayName();
					String value = (String)pd.getReadMethod().invoke(entity);
					if (!StringUtils.isBlank(value)) {
						console.printf("%s [%s]? ", name, value);
					}
					else {
						console.printf("%s? ", name);
					}
					value = console.readLine();
					if (!StringUtils.isBlank(value)) {
						pd.getWriteMethod().invoke(entity, value);
					}
				}
			}
		}
		catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
