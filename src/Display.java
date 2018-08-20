

import java.awt.Color;
import javax.swing.JFrame;

public class Display {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Test Alpha 0.01";

	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		Game game = new Game(frame);
		
		frame.setTitle(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setBackground(Color.WHITE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(game);
		frame.addKeyListener(game);
		frame.setVisible(true);
	
		System.out.println("Running...");	
		game.initGame();
	}
}
