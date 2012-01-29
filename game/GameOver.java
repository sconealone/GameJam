package game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameOver extends JPanel{
	private Image gover;
	
	//constructs gameover scene
	public GameOver(){
		try {
			gover = ImageIO.read(new File("src"+File.separatorChar+"resources"+File.separatorChar+"game_over.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
