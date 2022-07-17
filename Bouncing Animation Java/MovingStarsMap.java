/*
 *  =====================================================================================
 *  MovingStarsMap.java : Creates a stars map by linking together points
 *  The boundary of the stars map is defined by an invisible rectangles' width and height
 *  The points are randomly generated
 *  =====================================================================================
 */

 /* 
  * Name: Grace Du
  * UPI: mdu119
  *	ID : 650459747
  */

import java.awt.*;
import java.util.Random; 
public class MovingStarsMap extends MovingRectangle{
   
        
    public MovingStarsMap(){
        super();
    }
     public MovingStarsMap(int a){
        super.width = super.height = a;
    }
    public MovingStarsMap(int x,int y,int w,int h,int mw,int mh,Color fillColor,Color borderColor,String m,int pathType){
        super(x ,y ,w ,h ,mw ,mh ,fillColor,  borderColor,m,pathType);
        
    }

	Random rand = new Random();
	int x1 = rand.nextInt(width); 
	int x2 = rand.nextInt(width); 
	int x3 = rand.nextInt(width); 
	
	int y1 = rand.nextInt(height); 
	int y2 = rand.nextInt(height); 
	int y3 = rand.nextInt(height); 
	
	
    public void draw(Graphics g) {
		
		g.setColor(fillColor);
		      int[] xs = {x, x+x1, x+x2,x+x3};
		      int[] ys = {y, y1+y,y+ y2,y+y3};
		      g.drawPolyline(xs, ys, 4); 
     
      drawHandles(g);
	  drawMessage(g);
    }
}