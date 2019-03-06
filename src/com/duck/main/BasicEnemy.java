package com.duck.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{

	public BasicEnemy(int x, int y, ID id) {
		super(x, y, id);
	
		volX = 5;
		volY = 5;
		
	}
	public Rectangle getBounds(){
		return new Rectangle(x, y,16 ,16);
	}

	
	public void tick() {
	
		x += volX;
		y += volY;
		
		if(y <=0 || y >= Game.HEIGHT -32) volY *= -1;
		else if(x <=0 || x >= Game.WIDTH -16) volX *= -1;
		
	}


	public void render(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 16, 16);
		
	}

}
