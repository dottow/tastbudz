package com.tastbudz.util;


public final class Strings {
	public static boolean equals(String s1, String s2) {
		if (isEmpty(s1) && isEmpty(s2)) {
			return true;
		}
		return s1.trim().equalsIgnoreCase(s2.trim());
	}
	
	public static int compare(String s1, String s2) {
		if (isEmpty(s1) && isEmpty(s2)) {
			return 0;
		}
		return s1.trim().toLowerCase().compareTo(s2.trim().toLowerCase());
	}
	
	public static int compareWithNullMatch(String s1, String s2) {
		if (isEmpty(s2)) {
			return 0;
		}
		return compare(s1, s2);
	}
	
	public static boolean isEmpty(String s) {
		if (s == null) return true;
		return s.trim().length() == 0;
	}
}
