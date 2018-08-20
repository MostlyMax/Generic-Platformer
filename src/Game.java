import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Game extends JComponent implements KeyListener {
	
	JFrame f;
	Player player;
	static BufferedImage img;
	static int level;
	
	public Game(JFrame f) {
		this.f = f;
		level = 1;
	}
	
	public void initGame() {
		player = new Player();
		try {this.img = ImageIO.read(new File("res/level"+level+".png"));} 
		catch (IOException e) {e.printStackTrace();}
		
		while(true) {
			gameLoop();
			try {Thread.sleep(5);} 
			catch (InterruptedException e) {e.printStackTrace();}
		}
		
		
	}
	
	private void gameLoop() {
		
		player.update();
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(this.img, 0,0, 800, 600, null);
		player.drawPlayer(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			player.jump = true;
			break;
		case KeyEvent.VK_DOWN:
			
			break;
		case KeyEvent.VK_RIGHT:
			//System.out.println("right");
			player.right = true;
			break;
		case KeyEvent.VK_LEFT:
			player.left = true;
			break;
		case KeyEvent.VK_SPACE:
			
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			//player.drop();
			player.jump = false;
			
			break;
		case KeyEvent.VK_R:
			player.die();
			break;
		case KeyEvent.VK_RIGHT:
			player.right = false;
			break;
		case KeyEvent.VK_LEFT:
			player.left = false;
			break;
		}
	}

}
