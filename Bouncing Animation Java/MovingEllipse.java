/*
 *  ===============================================================================
 *  MovingEllipse.java : Creates an Ellipse shape.
 *  Draws an Ellipse depending on user inputs with handles and message.
 *  ===============================================================================
 */

 /* 
  * Name: Grace Du
  * UPI: mdu119
  *	ID : 650459747
  */

import java.awt.*;
public class MovingEllipse extends MovingShape{
        
        
    public MovingEllipse(){
        super();
    }
    public MovingEllipse(int one){
        super.width = super.height = one;
        
    }
    public MovingEllipse(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, String m,int pathType){
        super(x ,y ,w ,h ,mw ,mh ,bc,fc, m, pathType);
        
    }
    public double getArea(){
        return (0.5*super.height)*(0.5*super.width)*Math.PI;
        
    }
    public void draw(Graphics g) {
      g.setColor(fillColor);
      g.fillOval(x, y, width, height);
      g.setColor(borderColor);
      g.drawOval(x, y, width, height);
      drawHandles(g);
	  drawMessage(g);
    }
    

    public boolean contains(Point mousePt) {

		double dx, dy;
		Point EndPt = new Point(x + width, y + height);
		dx = (2 * mousePt.x - x - EndPt.x) / (double) width;
		dy = (2 * mousePt.y - y - EndPt.y) / (double) height;
		return dx * dx + dy * dy < 1.0;
}
}
    
  
    
    
