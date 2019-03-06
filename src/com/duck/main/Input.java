package com.duck.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter{

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		System.out.println(key);
	}
	
	public void keyReleased(KeyEvent e){
		
	}
	
	
}
