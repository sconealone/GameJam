package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import obstacles.Obstacle;
import sun.audio.*;
import java.io.*;

public class Game implements KeyListener,MouseListener{
	
	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 600;
	private static final int GROW_EVERY_NUM_LOOPS = 20;
	
	private int gameTime=0;
	private int score;
	private int loopCounter = 0;
	
	private SnakeBoundary snakeBoundary;
	private SnakeSpriteManager snake;
	private Wall wall;
	
	private boolean isUp, isDown, isLeft, isRight, isShrink = false;
	private boolean isGameOver = false;

	JFrame frame;
	Image gmenu;
	
	//game over scene
	Image gover;
	
	// just a variable to check if the obstacle is
	//inside the snake, change this when you get ur collision methods working
	// true if an obstacle is captured, false otherwise
	private boolean hasCaughtInside = false;
	static boolean hasClickedStart = false;
	
	public void initGame(){
//<<<<<<< HEAD
//		snake = new SnakeModel();
//
//		snakeManager = new SnakeSpriteManager();
//		wall = new Wall(3, snakeBoundary);
//		//frame = new JFrame();
//=======
		snakeBoundary = new SnakeBoundary();
		snake = new SnakeSpriteManager();
		wall = new Wall(4, snakeBoundary);
		//frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setIgnoreRepaint(true);
		frame.setVisible(true);
		frame.createBufferStrategy(2);
		frame.requestFocus();
		frame.addKeyListener(this);
	}
	
	//reads the game menu
	public void readMenu(){
		try {
			gmenu = ImageIO.read(new File("src"+File.separatorChar+"resources"+File.separatorChar+"menu_with_button.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setContentPane(new JLabel(new ImageIcon(gmenu)));
		frame.pack();
		frame.setVisible(true);
		frame.addMouseListener(this);
	}
	
	public void gameOverScene(){
		try {
			gover = ImageIO.read(new File("src"+File.separatorChar+"resources"+File.separatorChar+"game_over.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame.setContentPane(new JLabel(new ImageIcon(gover)));
		frame.pack();
		frame.setVisible(true);
		frame.addMouseListener(this);
	}
	
	public static void main (String [ ] args) throws InterruptedException, GameOverException {
		
		
		Game game = new Game();
		
		game.readMenu();
		
		while (!hasClickedStart)
		{
			if(hasClickedStart == true){
				game.initGame();
			}
		}
		try{
			game.gameLoop();
		}catch(GameOverException e){	
			game.gameOverScene();
			
		}
	
	
	}
	
	public void gameLoop() throws InterruptedException, GameOverException{
		BufferStrategy bf = frame.getBufferStrategy();
		Graphics g = null;
		while(true){
			try{
				if(isDown) snake.moveUp();
				if(isUp) snake.moveDown();
				if(isRight) snake.moveRight();
				if(isLeft) snake.moveLeft();
				
				g = bf.getDrawGraphics();
				g.setColor(new Color(255,255,255));
				g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
				
				for(Obstacle o: wall.getObstacles()) {
					o.update();
				}
				// repaint
				for(Obstacle o: wall.getObstacles()) {
					o.draw(g);
				}
				
				snake.spin();
				snake.draw(g);
				
				if(gameTime % 10 == (int) (Math.random() * 10))
					wall.createObstacle();
				
				// autogrow snake
				if (loopCounter % GROW_EVERY_NUM_LOOPS == 0)
				{
					snakeBoundary.grow();
					snake.grow();
					loopCounter = 1;			
				}
				else
				{
					loopCounter++;
				}
				gameTime++;
				bf.show();
				Thread.sleep(20);
				g.dispose();
			}finally{
				if(g!=null) g.dispose();
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_UP){
			isUp = true;
		}
		if(code == KeyEvent.VK_DOWN){
			isDown = true;
		}
		if(code == KeyEvent.VK_LEFT){
			isLeft = true;
		}
		if(code == KeyEvent.VK_RIGHT){
			isRight = true;
		}
		if(code == KeyEvent.VK_A){
			snake.shrink();
			snakeBoundary.shrink();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_UP){
			isUp = false;
		}
		if(code == KeyEvent.VK_DOWN){
			isDown = false;
		}
		if(code == KeyEvent.VK_LEFT){
			isLeft = false;
		}
		if(code == KeyEvent.VK_RIGHT){
			isRight = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * checks if mouse clicks on the start button
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("mouse clicked");
//		System.out.println("e.getX = " + e.getX());
//		System.out.println("e.getY = " + e.getY());
		// TODO Auto-generated method stub
		if((e.getX() >= 185) && (e.getX() <= 420) && (e.getY() >= 440) && (e.getY() <=510)){
			hasClickedStart = true;
			//System.out.println("you clicked start");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
