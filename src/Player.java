import java.awt.Graphics;
import java.util.ArrayList;

public class Player {
	private double x = 10;
	private double y = 10;
	private double xv;
	private double yv;
	private double xa;
	private double ya;
	
	private double bounce = 1;
	
	private int width = 20;
	private int height = 20;
	
	private ArrayList<Sensor> sensors;
	Sensor feet;
	Sensor hitbox;
	Sensor rightSide;
	Sensor leftSide;
	Sensor head;
	double floor_friction;
	
	private static final double MAX_VELOCITY = 2;
	
	public boolean jump;
	public boolean ground;
	public boolean ceil;
	public boolean flip;
	public boolean left;
	public boolean right;
	
	public Player() {
		sensors = new ArrayList<Sensor>();
		
		//width height x-offset y-offset
		feet = new Sensor(width, 3, 0, height);
		head = new Sensor(width, 3, 0, -3);
		hitbox = new Sensor(width+2,height+2,-1, -1);
		rightSide = new Sensor(3,height-4,width, 2);
		leftSide = new Sensor(3,height-4,-2, 2);
		
		sensors.add(feet);
		sensors.add(head);
		sensors.add(hitbox);
		sensors.add(rightSide);
		sensors.add(leftSide);
		
	}
	
	public void drawPlayer(Graphics g) {
		g.drawRect((int)x,(int)y, width, height);
		feet.draw(g);
		head.draw(g);
		rightSide.draw(g);
		leftSide.draw(g);
	}
	
	public void die() {
		x=20; y=20;
		xv=0; xa=0;
		yv=0; ya=0;
		flip = false;
	}
	
	public void update() {
		
		for (Sensor s:sensors) {s.update((int)x, (int)y);}

		sensorUpdate();
		floorUpdate();
		
		deathTest();
		powerUp();
		grav();
		move();
		
		jump();
		
		x+=xv;
		y+=yv;
		
		if (right||left) { 
			if ((right&&xv>=0)||(left&&xv<=0)) {
				if (Math.abs(xv)<MAX_VELOCITY) xv+=xa;
			}
			else xv+=xa+xa*floor_friction/2;
		}
		else xv+=xa;
		
		
		yv+=ya;
		
	}

	private void deathTest() {
		if (y>600) die();
		if (y<0) die();
		if (hitbox.red) die();
	}
	
	private void grav() {
		if (ceil) {
			if (!flip&&yv<0) yv=0;
			if (flip&&yv>0) yv=0;
		}
		if (ground&&!feet.orange) {ya=0; yv=0;}
		else {
			if (flip) ya=-.05;
			else ya=0.05;
		}
		if (hitbox.solid&&ground) {
			if (flip) y+=.5;
			if (!flip) y-=.5;
		}
	}
	
	private void powerUp() {
		if (hitbox.green);
		if (feet.orange) {
			if (Math.abs(yv)>2) {
				yv*=-.93;
				if (!flip) y-=2;
				if (flip) y+=2;
			}
			else {ya=0; yv=0;}
		}
		if (rightSide.orange) xv*=-1;
		if (leftSide.orange) xv*=-1;
		
		if (hitbox.magenta) flip = true;
		if (hitbox.cyan) flip = false;
	}
	
	private void jump() {
		if (jump&&ground) {
			if (!flip) yv=-3*bounce;
			if (flip) yv=3*bounce;
		}
	}
	
	private void floorUpdate() {
		if (feet.black) {floor_friction = 2; bounce = 1;}
		else if (feet.blue) {floor_friction = 0.1; bounce = .75;}
		else if (feet.orange) {bounce = 1.25;}
		else floor_friction = 0.5;
		
		
		//add bounce maybe??
	}
	
	private void sensorUpdate() {
		
		//check for ground
		if (feet.solid) ground = true;
		else ground = false;
		if (head.solid) ceil = true;
		else ceil = false;
		
		//update sensors for flip
		if (flip) {head.yo = height; feet.yo = -3;}
		else {head.yo = -3; feet.yo = height;}
		
		//right/left sensors
		if (rightSide.solid&&!rightSide.orange) if(xv>0) xv=0;
		if (leftSide.solid&&!leftSide.orange) if(xv<0) xv=0;
	}

	private void move() {

		if (right==true) xa=.1;
		else if (left==true) xa=-.1;
		
		else { //stopping player
			if (xv>0) xa=-.1; //if moving right
			if (xv<0) xa=.1; //if moving left
			if (xv<=.1&&xv>=-.1) {xv=0; xa=0;} //if basically stopped
			xa*=floor_friction; //friction
		}
		
		
	}
	

}
