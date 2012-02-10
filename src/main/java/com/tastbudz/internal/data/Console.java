package com.tastbudz.internal.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public final class Console   {
	private java.io.Console console=null;
	BufferedReader reader=null;
	
	public Console() {
		console = System.console();
		if (console == null) {
			InputStreamReader converter = new InputStreamReader(System.in);
			reader = new BufferedReader(converter);
		}
	}
	
    public Console format(String fmt, Object ...args) {
    	//TODO
        return this;
    }

    public Console printf(String format, Object ... args) {
    	//TODO
    	return format(format, args);
    }

    public String readLine(String fmt, Object ... args) {
    	if (console != null)
    		return console.readLine(fmt, args);
    	else {
    		return readLine();
    	}
    }

    public String readLine() {
    	if (console != null)
    		return console.readLine();
    	else {
    		try {
    			return reader.readLine();
    		}
    		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return null;
    }

    public char[] readPassword(String fmt, Object ... args) {
    	//TODO
    	return null;
    }

    public char[] readPassword() {
    	//TODO
    	return null;
    }
}

