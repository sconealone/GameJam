package game;

import sun.audio.*;
import java.io.*;

public class Music {
	 private String filename;
	 private InputStream in;
	 AudioStream as;
	 
	 AudioData musicData = null;
	 ContinuousAudioDataStream loop = null;
	 
	 public Music(){
		 try{
			 in = new FileInputStream("src" + File.separatorChar + "resources" + File.separatorChar + "MainTheme.wav"); 
			 as = new AudioStream(in);
			 musicData = as.getData();
			 loop = new ContinuousAudioDataStream(musicData);
		 } catch (IOException e){
			 e.printStackTrace();
		 }
	 }
	 
	 public void play() {
		 AudioPlayer.player.start(loop);
	 }
	 
	 public void stopBGM() {
		 AudioPlayer.player.stop(loop);
	 }
}
