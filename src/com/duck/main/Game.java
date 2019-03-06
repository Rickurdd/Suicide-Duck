package com.duck.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -703202359408913338L;
	
	public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9;
	private Random r;
	private Thread thread;
	private boolean running = false;
	private HUD hud;
	

	private Handler handler;
	
	public Game(){
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "Go duck die!", this);
	
		hud = new HUD();

		for(int i = 0; i < 20; i++){
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(WIDTH/2-16, HEIGHT/2-16, ID.BasicEnemy));

		}
		
		
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true; 
	}
	public synchronized void stop(){
		try{
			thread.join();
			running = false; 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus();
		 long lastTime = System.nanoTime();
		 double amountOfTicks = 60.0;
		 double ns = 1000000000 / amountOfTicks;
		 double delta = 0;
		 long timer = System.currentTimeMillis();
		 int frames = 0;
		 while(running){
			 long now = System.nanoTime();
			 delta += (now - lastTime) / ns;
			 lastTime = now;
			 while(delta >= 1){
				 tick();
				 delta--;
			 }
			 if(running)
				 render();
			 frames++;
			 
			 if(System.currentTimeMillis() - timer >1000){
				 timer += 1000;
				 //System.out.println("FPS: " + frames);
				 frames = 0;
			 }
		 }
		 stop();
	}
	
	private void tick(){
		handler.tick();
		hud.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//renders
		handler.render(g);
		hud.render(g);
		
		
		g.dispose();
		bs.show();
	}
	
	//Clamp
	public static int clamp(int var, int min, int max){
		if (var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else return var;
	}
	
	
	
	public static void main(String args[]){
	new Game();
	
	}
}
