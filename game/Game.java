package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import javax.swing.*;

import obstacles.Obstacle;


public class Game implements KeyListener, MouseListener {

	private enum Scene {START, INSTRUCTION, GAME, GAME_OVER};
	
	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 600;
	private static final int GROW_EVERY_NUM_LOOPS = 40;
	private static final int MAX_LEVEL = 25;
    private static final int LEVEL_CHANGE_MULTIPLIER = 1000 * 5;
	
	private int level;
	private int gameTime = 0;
	private int score = 0;
	private int captured = 0;
	private int loopCounter = 1;
	private long systemStart = System.currentTimeMillis();
	
	private Scene scene;

	private Snake snake;
	private Wall wall;
	private ArrayList<Obstacle> obstacles;
	private boolean isUp, isDown, isLeft, isRight, isShrink = false;
	private Image background;

	Music bgm;
	JFrame frame;
	Image gmenu;

	// game over scene
	Image gover;
    private int nextLevelAt;
	static boolean hasClickedStart = false;
	static boolean hasClickedRetry = false;
	private static boolean hasClickedInstruction = false;

	public Game()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		scene = Scene.START;
        frame.addMouseListener(this);
        frame.addKeyListener(this);
	}
	
	public void initGame() {
		scene = Scene.GAME;

		level = 1;
		nextLevelAt = 0;
		gameTime = 0;
		score = 0;
		captured = 0;
		loopCounter = 0;
		hasClickedRetry = false;
		
		bgm = new Music();

		bgm.play();
		
		snake = new SnakeManager();
		wall = new Wall(MAX_LEVEL, ((SnakeManager)snake).getBoundary());
		frame.setIgnoreRepaint(true);
		frame.setVisible(true);
		frame.createBufferStrategy(2);
		frame.requestFocus();
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

	//reads the game menu
	public void readMenu(){
		scene = Scene.START;
		try {
			gmenu = ImageIO
					.read(new File("src" + File.separatorChar + "resources"
							+ File.separatorChar + "menu_with_button.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame.setContentPane(new JLabel(new ImageIcon(gmenu)));
		frame.setVisible(true);
	}

	public void gameOverScene() {
		scene = Scene.GAME_OVER;
		bgm.stopBGM();

		try {
			gover = ImageIO.read(new File("src" + File.separatorChar
					+ "resources" + File.separatorChar
					+ "game_over_retryButton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame.setContentPane(new JLabel(new ImageIcon(gover)));
		frame.setVisible(true);

	}
	
	/**
	 * Displays instructions.
	 */
	private void instructionScene()
	{
		scene = Scene.INSTRUCTION;
		Image instructions = null;
		try
		{
			instructions = ImageIO.read(new File("src" + File.separatorChar + "resources" + File.separatorChar + "ggjinstructionmenu.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		frame.setContentPane(new JLabel(new ImageIcon(instructions)));
		frame.setVisible(true);
		
	}
	
	public static void main (String [ ] args) throws InterruptedException, GameOverException {
		
		
		Game game = new Game();
		game.readMenu();
		while (!hasClickedStart)
		{
			if (hasClickedInstruction)
			{
				game.instructionScene();
			}
			Thread.sleep(1);
		}

		while (true)
		{
			game.initGame();
			try {
				game.gameLoop();
	
			}
			catch(GameOverException e){	
			    
				game.gameOverScene();
				while (!hasClickedRetry)
				{
					// do nothing
					Thread.sleep(1);
				}
				hasClickedRetry = true;
			}
		}
	}

	public void gameLoop() throws InterruptedException, GameOverException{
		BufferStrategy bf = frame.getBufferStrategy();
		Graphics g = null;
		boolean[] arrived = new boolean[MAX_LEVEL];
		Obstacle[] arrivedObstacle = new Obstacle[MAX_LEVEL];

		while (true) {
			try {
			    boolean gameOver = false;

		        getInput();
				g = bf.getDrawGraphics();
				g.setColor(new Color(255, 255, 255));
				g.drawImage(background, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
				for (int i = 0; i < arrived.length - 1; i++) {
					arrived[i] = true;
					arrivedObstacle[i] = null;
				}

                int numCaptures = 0;
				Obstacle myO = null;
				Obstacle gameOverObstacle = null;
                // arrived seems to be the opposite of arrived
                // use !arrived[i] if the obstacle has actually arrived
				for (int i = 0; i < obstacles.size(); i++)
				{
					myO = obstacles.get(i);
					if (null != myO)
					{
    					arrived[i] = myO.update();
    					arrivedObstacle[i] = myO;
                        if (!arrived[i])
                        {
                            if (myO.wasCaptured()){
                                numCaptures++;
                            }
                            if (myO.haveCollided()){
                                gameOver = true;
                                gameOverObstacle = myO;
                                ((obstacles.CircleObstacle)gameOverObstacle).explode(g);
                            }
                        }
                    }
				}
				
				// score update
				
				
                g.drawString(
                        ("Score: " + Integer.toString(getScore(numCaptures))), 
                        frame.getWidth() - 140, frame.getHeight() - (frame.getHeight() - 50));
				    

                // repaint
                for (Obstacle o : wall.getObstacles()) {
                    if (null != o)
                        o.draw(g);
                }
				snake.draw(g);
				g.setColor(Color.WHITE);
				if ((gameTime % 50 == (int) (Math.random() * 50)) && wall.getSize() < level)
					wall.createObstacle();

				// autogrow snake
				if (loopCounter % GROW_EVERY_NUM_LOOPS == 0) {
					snake.grow();
					loopCounter = 1;
				} else {
					loopCounter++;
				}
				

                level++;
				if (level >= nextLevelAt)
				{
				    //System.out.println(level);
				    nextLevelAt = level * level * LEVEL_CHANGE_MULTIPLIER;
				}
				gameTime++;
				bf.show();
				Thread.sleep(20);
                if (gameOver)
                {
                    
                    // TODO This is not drawing. Don't know how to fix it.
                    /*
                    g.setColor(new Color(255, 0, 0));
                    g.drawString(
                            "You've been hit!", 
                            (int)gameOverObstacle.getTLCoord().x, (int)gameOverObstacle.getTLCoord().y);
                            */
                    throw new GameOverException();
                }
				g.dispose();
			} catch (GameOverException goe) {
                Thread.sleep(3000); // let the player see which moon hit him  
                throw new GameOverException();
			} finally {
	            if (g != null)
	                g.dispose();
	            
                for (int i = 0; i < arrived.length - 1; i++) {
                    if (!arrived[i]) {
                        wall.deleteObstacle(arrivedObstacle[i]);
                        arrived[i] = true;

                    }

                }
			}
		} // end while
	}

    /**
     * Modifies the position of the snake according to input
     */
    private void getInput()
    {
        final int MOVE_BY = 10;
        if (isDown) {
        	snake.moveBy(new Point2D.Double(0, MOVE_BY));
        }
        if (isUp) {
            snake.moveBy(new Point2D.Double(0, -MOVE_BY));
        }
        if (isRight) {
            snake.moveBy(new Point2D.Double(MOVE_BY, 0));
        }
        if (isLeft) {
            snake.moveBy(new Point2D.Double(-MOVE_BY, 0));
        }
        if (isShrink) {
            snake.shrink();
        	isShrink = false;
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
	    int x = e.getX(), y = e.getY();
	    //System.out.println("("+x+","+y+")");
		int startButtonLeftBoundary = 185, startButtonRightBoundary = 420, startButtonTopBoundary = 468, startButtonBottomBoundary = 502;
		boolean isInStartButtonBoundary = (x >= startButtonLeftBoundary) 
		        && (x<= startButtonRightBoundary) 
		        && (y >= startButtonTopBoundary) 
		        && (y <=startButtonBottomBoundary);
		if((scene == Scene.START || scene == Scene.INSTRUCTION) && isInStartButtonBoundary){
			hasClickedStart = true;
			return;
		}
		int instrButtonLeft = startButtonLeftBoundary;
		int instrButtonRight = startButtonRightBoundary;
		int instrButtonOffsetWrtStart = 55; // pixels according to gimp
		int isntrButtonUp = startButtonTopBoundary + instrButtonOffsetWrtStart;
		int instrButtonDown = startButtonBottomBoundary + instrButtonOffsetWrtStart;
		boolean isInInstrButtonBounds = (x >= instrButtonLeft) 
                && (x<= instrButtonRight) 
                && (y >= isntrButtonUp) 
                && (y <=instrButtonDown);
		if ((scene == Scene.START) && isInInstrButtonBounds)
		{
		    hasClickedInstruction = true;
		    return;
		}
		if ((scene == Scene.GAME_OVER) 
		        && (x >= 194) 
		        && (x <= 419) 
		        && (y>= 480)
				&& (y <= 510)) {
			hasClickedRetry = true;
			return;
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
	private int getScore(int captures) {
		final int CAPTURE_MULTIPLIER = 100;		
		int stage = snake.getStage();
        if (0 == stage)
        {
            score += captures;
        } 
        else
        {
            score = score + captures * CAPTURE_MULTIPLIER * stage;
        }
		return score;
	}

}
