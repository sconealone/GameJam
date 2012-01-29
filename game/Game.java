package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;

import obstacles.Obstacle;
import sun.audio.*;
import java.io.*;

public class Game implements KeyListener, MouseListener {

	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 600;
	private static final int GROW_EVERY_NUM_LOOPS = 20;

	private int gameTime = 0;
	private int score = 0;
	private int captured = 0;
	private int loopCounter = 0;
	private long systemStart = System.currentTimeMillis();

	private SnakeBoundary snakeBoundary;
	private SnakeSpriteManager snake;
	private Wall wall;
	private ArrayList<Obstacle> obstacles;
	private boolean isUp, isDown, isLeft, isRight, isShrink = false;
	private Image background;
	private JLabel scoreLabel = new JLabel("Score: " + Integer.toString(score));

	JFrame frame;
	Image gmenu;

	// game over scene
	Image gover;

	// just a variable to check if the obstacle is
	// inside the snake, change this when you get ur collision methods working
	// true if an obstacle is captured, false otherwise
	private boolean hasCaughtInside = false;
	static boolean hasClickedStart = false;
	static boolean hasClickedRetry = false;

	public void initGame() {
		snakeBoundary = new SnakeBoundary();
		snake = new SnakeSpriteManager();
		wall = new Wall(4, snakeBoundary);
		// frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setIgnoreRepaint(true);
		frame.setVisible(true);
		frame.createBufferStrategy(2);
		frame.requestFocus();
		frame.addKeyListener(this);
		obstacles = wall.getObstacles();

		try {
			background = ImageIO.read(new File("src" + File.separatorChar
					+ "resources" + File.separatorChar + "bkground.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final Date origDate = new Date();
		systemStart = origDate.getTime();
	}

	// reads the game menu
	public void readMenu() {
		try {
			gmenu = ImageIO
					.read(new File("src" + File.separatorChar + "resources"
							+ File.separatorChar + "menu_with_button.png"));
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

	public void gameOverScene() {
		try {
			gover = ImageIO.read(new File("src" + File.separatorChar
					+ "resources" + File.separatorChar
					+ "game_over_retryButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame.setContentPane(new JLabel(new ImageIcon(gover)));
		frame.pack();
		frame.setVisible(true);
		frame.addMouseListener(this);

	}

	public static void main(String[] args) throws InterruptedException,
			GameOverException {

		Game game = new Game();

		game.readMenu();
		while (!hasClickedStart) {

		}
		game.initGame();
		try {
			game.gameLoop();

		} catch (GameOverException e) {
			game.gameOverScene();
			if (hasClickedRetry == true) {
				game = null;
				game = new Game();

				hasClickedRetry = false;
			}
		}
	}

	public void gameLoop() throws InterruptedException, GameOverException {

		BufferStrategy bf = frame.getBufferStrategy();
		Graphics g = null;
		boolean[] arrived = new boolean[10];
		Obstacle[] arrivedObstacle = new Obstacle[10];

		while (true) {
			try {
				if (isDown) {
					snake.moveUp();
					snakeBoundary.moveUp();
				}
				if (isUp) {
					snake.moveDown();
					snakeBoundary.moveDown();
				}
				if (isRight) {
					snake.moveRight();
					snakeBoundary.moveRight();
				}
				if (isLeft) {
					snake.moveLeft();
					snakeBoundary.moveLeft();
				}
				if (isShrink) {
					snake.shrink();
					snakeBoundary.shrink();
					isShrink = false;
				}
				g = bf.getDrawGraphics();
				g.setColor(new Color(255, 255, 255));
				g.drawImage(background, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);

				Date date = new Date();
				long time = date.getTime() - systemStart;
				g.drawString(
						("Score: " + Integer.toString(getScore((int) time))),
						frame.getWidth() - 70, frame.getHeight() - (frame.getHeight() - 50));

				for (int i = 0; i < arrived.length - 1; i++) {
					arrived[i] = true;
					arrivedObstacle[i] = null;
				}

				Obstacle myO;
				for (int i = 0; i < obstacles.size(); i++)// Obstacle o:
															// obstacles) {
				{
					myO = obstacles.get(i);
					arrived[i] = myO.update();
					arrivedObstacle[i] = myO;

				}
				for (int i = 0; i < arrived.length - 1; i++) {
					if (!arrived[i]) {
						wall.deleteObstacle(arrivedObstacle[i]);
						arrived[i] = true;

					}

				}
				// repaint
				for (Obstacle o : wall.getObstacles()) {
					o.draw(g);
				}

				snake.spin();
				snake.draw(g);

				// /// Update the score (based on system time)
				//
				// frame.getContentPane().add(scoreLabel);
				// Date date = new Date();
				// long time = date.getTime();
				// scoreLabel.setText("Score: " +
				// Integer.toString(getScore((int) time)));
				// scoreLabel.setBounds(200, 200, 100, 100);
				// scoreLabel.repaint();
				// scoreLabel.setVisible(true);

				if (gameTime % 10 == (int) (Math.random() * 10))
					wall.createObstacle();

				// autogrow snake
				if (loopCounter % GROW_EVERY_NUM_LOOPS == 0) {
					snakeBoundary.grow();
					snake.grow();
					loopCounter = 1;
				} else {
					loopCounter++;
				}
				gameTime++;
				bf.show();
				Thread.sleep(20);
				g.dispose();
			} finally {
				if (g != null)
					g.dispose();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) {
			isUp = true;
		}
		if (code == KeyEvent.VK_DOWN) {
			isDown = true;
		}
		if (code == KeyEvent.VK_LEFT) {
			isLeft = true;
		}
		if (code == KeyEvent.VK_RIGHT) {
			isRight = true;
		}
		if (code == KeyEvent.VK_A) {
			isShrink = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) {
			isUp = false;
		}
		if (code == KeyEvent.VK_DOWN) {
			isDown = false;
		}
		if (code == KeyEvent.VK_LEFT) {
			isLeft = false;
		}
		if (code == KeyEvent.VK_RIGHT) {
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
		if ((e.getX() >= 185) && (e.getX() <= 420) && (e.getY() >= 440)
				&& (e.getY() <= 510)) {
			hasClickedStart = true;
		}
		if ((e.getX() >= 194) && (e.getX() <= 419) && (e.getY() >= 490)
				&& (e.getY() <= 515)) {
			hasClickedRetry = true;
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

	/**
	 * Computes a score based on the time that the game has been running and the
	 * number of objects you captured
	 * 
	 * @param time
	 * @param captures
	 * @return
	 */
	private int getScore(int time) {
		final int FRAMES_PER_SECOND = 50;
		final int CAPTURE_MULTIPLIER = 100;
		score = ((int) time / FRAMES_PER_SECOND + CAPTURE_MULTIPLIER * captured);
		return score;
	}

}
