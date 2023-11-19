package game;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Sound extends Thread {
	private Player player;
	
	private boolean isLoop;
	
	private File file;
	private FileInputStream fileIStream;
	private BufferedInputStream bufferedIStream;
	
	public Sound(String name, boolean isLoop) {
		
		try {
			this.isLoop = isLoop;
			
			file = new File(Main.class.getResource("../sounds/" + name).toURI());
			fileIStream = new FileInputStream(file);
			bufferedIStream = new BufferedInputStream(fileIStream);
			player = new Player(bufferedIStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {
		
		if (player == null) {
			return 0;
		}
		
		return player.getPosition();
	}
	
	public void close() {
		
		isLoop = false;
		
		player.close();
		
		this.interrupt();
	}
	
	@Override
	public void run() {
		
		try {
			do {
				player.play();
				
				fileIStream = new FileInputStream(file);
				bufferedIStream = new BufferedInputStream(fileIStream);
				player = new Player(bufferedIStream);
			} while (isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
