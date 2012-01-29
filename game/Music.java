package game;

import sun.audio.*;
import java.io.*;

public class Music {
	 private String filename;
	 public Music(String filename){
		 this.filename = filename;
	 }
	 
	 public void play() {
		 try{
			 InputStream in = new FileInputStream(filename);
			 AudioStream as = new AudioStream(in);
			 AudioPlayer.player.start(as);
		 } catch (IOException e){
			 e.printStackTrace();
		 }
	 }
}
