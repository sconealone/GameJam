package game;

import sun.audio.*;
import java.io.*;

public class Music {
	 private String filename;
	 private InputStream in;
	 AudioStream as;
	 
	 public Music(){
		 try{
			 in = new FileInputStream("MainTheme.wav");
			 as = new AudioStream(in);
		 } catch (IOException e){
			 e.printStackTrace();
		 }
	 }
	 
	 public void play() {
		 AudioPlayer.player.start(as);
	 }
	 
	 public void stopBGM() {
		 AudioPlayer.player.stop(as);
	 }
}
