import java.awt.*;

public class Car implements Runnable{

		/*
		 * Car est une classe qui implémente une voiture comme
		 * un thread. Ce thread, selon son type cartype (REDCAR/BLUECAR)
		 * va se déplacer de la gauche vers la droite ou inversement
		 * 
		 * @author Matthieu Roy
		 */
    public static final int REDCAR  = 0;
    public static final int BLUECAR = 1;
    
    private final static int bridgeY   = 95;
    private final static int bridgeXLeft = 210;
    private final static int bridgeXLeft2 = 290;
    private final static int bridgeXMid = 410;
    private final static int bridgeXRight2 = 530;
    private final static int bridgeXRight = 610;
    private final static int totalWidth = 900;
    private final static int initX[]  = {-80, totalWidth};
    private final static int initY[]  = {135, 55};
    private final static int outLeft = -200;
    private final static int outRight = totalWidth + 200;

    int cartype;
    int xpos, ypos; 
    Car inFront;
    Image image;
    TrafficController controller;

    public Car(int cartype,Car inFront, Image image, TrafficController controller) {
	this.cartype = cartype;
	this.inFront = inFront;
        this.image = image;
	this.controller  = controller;
        if (cartype == REDCAR) 
	    xpos = inFront==null ? outRight : Math.min(initX[cartype],inFront.getX()-90);
	else 
	    xpos = inFront == null ? outLeft : Math.max(initX[cartype],inFront.getX()+90);
        ypos = initY[cartype];
    }
    

    public void move() throws InterruptedException {
	int xposOld =  xpos;
	if (cartype==REDCAR) {
            if (inFront.getX() - xpos > 100) {
                xpos += 4;
                if (xpos >= bridgeXLeft & xposOld < bridgeXLeft) controller.enterRed();
                else if (xpos > bridgeXLeft && xpos < bridgeXMid) {if (ypos > bridgeY) ypos -= 2;}
                else if (xpos >= bridgeXRight2 && xpos < bridgeXRight) {if (ypos < initY[REDCAR]) ypos += 2;}
		else if (xpos >= bridgeXRight &&  xposOld < bridgeXRight) controller.leaveRed();
            }
	} else {
            if (xpos-inFront.getX() > 100) {
                xpos -= 4;
                if (xpos <= bridgeXRight && xposOld > bridgeXRight) controller.enterBlue();
                else if (xpos < bridgeXRight && xpos > bridgeXMid) {if (ypos < bridgeY) ypos += 2;}
                else if (xpos <= bridgeXLeft2 && xpos > bridgeXLeft) {if(ypos > initY[BLUECAR]) ypos -= 2;}
		else if (xpos <= bridgeXLeft && xposOld > bridgeXLeft) controller.leaveBlue();
            }
	}
    }




    public void run() {
	boolean outOfSight = cartype == REDCAR ? xpos > totalWidth : xpos < -80;
        while (!outOfSight) {
            try {
				move();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    outOfSight = cartype == REDCAR ? xpos > totalWidth : xpos < -80;
	    try {
		Thread.sleep(30);
	    } catch (InterruptedException e) {}
	    }
	xpos = cartype == REDCAR ? outRight: outLeft;
    }

    public int getX() {return xpos;}
    
    public void draw(Graphics g) {
	g.drawImage(image,xpos,ypos,null);
    }
}