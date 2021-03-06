package com.fdmgroup.snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {

	public static Snake snake;
	
	public JFrame jframe;
	public SnakeDesign sd;
	public Timer timer = new Timer(8, this);
	
	public ArrayList<Point> snakeParts = new ArrayList<Point>();

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
	
	public int ticks = 0, direction = DOWN, score, taillength = 10, delay = 5, delay2 = 4 ;
	
	public Point head, cherry;
	
	public Random random;
	
	public boolean over = false, paused = false;
	
	public Dimension dim;
	
	@SuppressWarnings("static-access")
	public Snake() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake");
		
		jframe.setVisible(true);
		jframe.setSize(400, 400);
		jframe.setResizable(false);
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
		jframe.add(sd = new SnakeDesign());
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		startGame();
	}
	
	public void startGame(){
		over = false;
		paused = false;
		score = 0;
		ticks = 0;
		taillength = 1;
		direction = DOWN;
		head = new Point(0, 0);
		snakeParts.clear();
		random = new Random();
		cherry = new Point(random.nextInt(38), random.nextInt(37));
		
		for (int i = 0; i < taillength; i++){
			snakeParts.add(new Point(head.x, head.y));
		}
		timer.start();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		sd.repaint();
		if(!over ){
		ticks++;
		}
		if(ticks % 10 == 0 && head != null && !over && !paused){
			
			snakeParts.add(new Point(head.x, head.y));
			if(direction == UP){
				if(head.y -1 >= 0 && noTailAt(head.x, head.y -1))
				head = new Point(head.x, head.y -1);
				else
					over = true;
					
			}
				
			if(direction == DOWN){
				if(head.y +1 < 38 && noTailAt(head.x, head.y +1))
				head = new Point(head.x, head.y +1);
				else
					over = true;
			}	
			if(direction == LEFT){
				if(head.x -1 >= 0 && noTailAt(head.x -1, head.y))
				head =new Point(head.x -1, head.y);
				else
					over = true;
			}	
			if(direction == RIGHT){
				if(head.x +1 < 39 && noTailAt(head.x +1, head.y))
				head = new Point(head.x +1, head.y);
				else
					over = true;
			}
			
		if (snakeParts.size() > taillength){
			snakeParts.remove(0);
		}
		if(score >= 50){
			timer.setDelay(delay);
		}
		if(score >=100){
			timer.setDelay(delay2);
		}
			
		if(cherry != null){
			
			if(head.equals(cherry)){
				score += 10;
				taillength++;
				
				cherry.setLocation( random.nextInt(38), random.nextInt(37));
			}
		}
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if(i == KeyEvent.VK_A && direction != RIGHT)
			direction = LEFT;
		if(i == KeyEvent.VK_D && direction != LEFT)
			direction = RIGHT;
		if(i == KeyEvent.VK_W && direction != DOWN)
			direction = UP;
		if(i == KeyEvent.VK_S && direction != UP)
			direction = DOWN;
		if(i == KeyEvent.VK_SPACE){
			if(over)
				startGame();
			else 
				paused = !paused;
		}
			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	
	public boolean noTailAt(int x, int y){
		for (Point point : snakeParts){
			if(point.equals(new Point(x, y))){
				return false;
		
			}
		}
		return true;
	}
	
	
	public static void main(String [] args){
		
		 snake = new Snake();
		
	}

	


}
