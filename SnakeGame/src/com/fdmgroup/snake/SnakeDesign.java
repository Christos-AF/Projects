package com.fdmgroup.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SnakeDesign extends JPanel{
	
	public static Color pink = new Color(3342387);
	public static Color blue = new Color(39321);
	public static Color green = new Color(39219);
		
	@SuppressWarnings("static-access")
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(pink);
		g.fillRect(0, 0, 800, 800);
		
		Snake snake = Snake.snake;
		g.setColor(blue);
		for (Point point : snake.snakeParts ){
			
			g.fillRect(point.x * snake.SCALE, point.y * snake.SCALE , snake.SCALE, snake.SCALE);
			
		}
		
		g.fillRect(snake.head.x * snake.SCALE, snake.head.y * snake.SCALE, snake.SCALE, snake.SCALE);
		
		
		
		g.setColor(green);
		g.fillRect(snake.cherry.x * snake.SCALE, snake.cherry.y * snake.SCALE, snake.SCALE, snake.SCALE);
		String str = "Score: " + snake.score +  ",  Time: "+ snake.ticks /20;
	
		g.setColor(Color.white);
		g.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 15));
		g.drawString(str, (int) getWidth() / 2 - str.length()*2, 10);
	
	}
	

	
}
