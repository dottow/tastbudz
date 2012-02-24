package com.tastbudz.model.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import com.tastbudz.model.ID;
import com.tastbudz.model.PropertyList;
import com.tastbudz.util.Strings;

public abstract class ModelUtil {
	public static List<NameValuePair> getNameValuePairs(PropertyList entity) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(entity.getClass());
		Map<String,PropertyDescriptor> nameDescriptorMap = new HashMap<String,PropertyDescriptor>();
		for (PropertyDescriptor descriptor : descriptors) {
			nameDescriptorMap.put(descriptor.getName(), descriptor);
		}

		List<String> propertyNames = entity.getPropertyNames();
		for (String propertyName : propertyNames) {
			PropertyDescriptor descriptor = nameDescriptorMap.get(propertyName);
			Assert.notNull(descriptor, "Entity "+entity+" has invalid property model");
			try {
				Method readMethod = descriptor.getReadMethod();
				if (readMethod != null) {
					Object value = readMethod.invoke(entity);
					if (value != null) {
						Class propertyType = descriptor.getPropertyType();
						if (String.class.equals(propertyType)) {
							String s = value.toString();
							if (!Strings.isEmpty(s)) {
								list.add(new NameValuePair(propertyName, s));
							}
						}
						else if (ID.class.equals(propertyType)) {
							list.add(new NameValuePair(propertyName, value.toString()));
						}
						else if (value instanceof Collection) {
							list.add(new NameValuePair(propertyName, value));
						}
					}
				} 
			}
			catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}
}
