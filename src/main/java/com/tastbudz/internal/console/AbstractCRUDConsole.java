package com.tastbudz.internal.console;

import com.tastbudz.model.PersistentEntity;


public abstract class AbstractCRUDConsole<T extends PersistentEntity> implements Runnable {
	protected Console console;
	
	public AbstractCRUDConsole(Console console) {
		this.console = console;
	}

	public abstract void create();
	public abstract void read();
	public abstract void update();
	public abstract void delete();

	public void run() {
		String name = getClass().getSimpleName();
		if (name.endsWith("Console")) {
			name = name.substring(0, name.indexOf("Console"));
		}
		console.printf(name+" console\n\n");
		
		String input="";
		while (!(input.equals("quit"))) {
        	console.printf("[C]reate\n");
        	console.printf("[R]ead\n");
        	console.printf("[U]pdate\n");
        	console.printf("[D]elete\n");
        	console.printf("[Q}uit\n");
        	console.printf("\n");
        	console.printf("Choice:  ");
        	input = console.readLine();

			input = input.trim().toLowerCase();
			if (input.startsWith("c")) {
				create();
			} 
			if (input.startsWith("r")) {
				read();
			}
			else if (input.startsWith("u")) {
				update();
			} 
			else if (input.startsWith("d")) {
				delete();
			} 
			else if (input.startsWith("q")) {
				return;
			}
			else {
				console.printf("Huh?!\n\n");
			}
			console.printf("\n\n\n");
		}
	}
}
