import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sensor {
	int x;
	int y;
	int xo;
	int yo;
	
	BufferedImage level;
	
	boolean black;
	boolean red;
	boolean orange;
	boolean yellow;
	boolean green;
	boolean blue;
	boolean cyan;
	boolean pink;
	boolean magenta;
	
	boolean solid;
	
	
	int width;
	int height;
	
	
	public Sensor(int width, int height,int xo, int yo) {
		this.width = width;
		this.height = height;
		this.xo = xo;
		this.yo = yo;
		level = Game.img;
	
	}
	
	private void update() {
		black = false;
		red = false;
		orange = false;
		yellow = false;
		green = false;
		blue = false;
		cyan = false;
		magenta = false;
		
		solid = false;
		
		int color = 0;
		//System.out.println("ping 2");
		for(int xi = x; xi<x+width; xi++) {
			//System.out.println("ping 3");
			for (int yi = y; yi<y+height; yi++) {
				try {
					color = Game.img.getRGB(xi, yi);
				}
				catch (ArrayIndexOutOfBoundsException e) {
					color = 1;
				}
				finally {
				if (color==Color.BLACK.getRGB()) black = true; //000000
				if (color==Color.RED.getRGB()) red = true; //ff0000
				if (color==Color.ORANGE.getRGB())  orange = true; //ffc800
				if (color==Color.YELLOW.getRGB())  yellow = true; //ffff00
				if (color==Color.GREEN.getRGB())  green = true; //00ff00
				if (color==Color.BLUE.getRGB()) blue = true; //0000ff
				if (color==Color.CYAN.getRGB())  cyan = true; //00ffff
				if (color==Color.PINK.getRGB()) pink = true; //ffafaf
				if (color==Color.MAGENTA.getRGB()) magenta = true; //ff00ff
				
				if (black||blue||orange) solid = true;
				}
			}
		}
	}
	
	public void update(int x, int y) {
		this.x = x+xo;
		this.y = y+yo;
		
		update();
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}

}
