package game;

public class SnakeSpriteManager {
	private String[] snakes;
	private int stageCounter = 0;
	private final int NUM_GROWTH_STAGES = 3;
	
	public SnakeSpriteManager()
	{
		
		// added from largest to smallest
		snakes = new String[NUM_GROWTH_STAGES];
		snakes[0] = "snake2.png";
		snakes[1] = "s-testER-01.png";
		snakes[2] = "snake.gif";
	}
	
	public void shrink()
	{
		if (stageCounter < NUM_GROWTH_STAGES - 1)
		{
			stageCounter++;
		}
	}
	
	public void grow()
	{
		if (stageCounter > 0)
		{
			stageCounter--;
		}
	}
	
	public String getSpriteName()
	{
		return snakes[stageCounter];
	}
}
