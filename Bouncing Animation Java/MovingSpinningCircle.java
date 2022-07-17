/*
 *  =====================================================================================
 *  MovingSpinningCircle.java : Creates a spinning circle
 *  The rotation speed is given by 20 and the pattern on the circle is constant
 *  A circle cannot be turned into an ellipse
 *  =====================================================================================
 */

 /* 
  * Name: Grace Du
  * UPI: mdu119
  *	ID : 650459747
  */

import java.awt.*;
public class MovingSpinningCircle extends  MovingEllipse{
    public static int ROTATION_SPEED =20;
    public int startAngle = 0;
 
        
    public MovingSpinningCircle(){
        super();
    }
    public MovingSpinningCircle(int a){
        super.width = a;
        super.height = a;

    }
    public MovingSpinningCircle(int x,int y,int s,int mw,int mh,Color fillColor,Color borderColor,String m,int pathType){
        super(x ,y ,s ,s ,mw ,mh ,fillColor,  borderColor,m,pathType);
        
    }
    public void setWidth(int num){
        super.width = num;
        super.height = num;
        
    }
    public void setHeight(int num){
        super.width = num;
        super.height = num;
        
    }
	
    public void draw(Graphics g) {
	
		if (startAngle != 1){
		
      g.setColor(fillColor);
      g.fillArc(super.x, super.y, super.width, super.height,startAngle,90);
      g.setColor(borderColor);
      g.drawArc(super.x, super.y, super.width, super.height,startAngle,90);
	  
      g.setColor(fillColor);
      g.fillArc(super.x, super.y, super.width, super.height,startAngle+180,90);
      g.setColor(borderColor);
      g.drawArc(super.x, super.y, super.width, super.height,startAngle+180,90);
	  
      g.setColor(Color.white);
      g.fillArc(super.x, super.y, super.width, super.height,startAngle+90,90);
      g.setColor(borderColor);
      g.drawArc(super.x, super.y, super.width, super.height,startAngle+90,90);
	  
      g.setColor(Color.white);
      g.fillArc(super.x, super.y, super.width, super.height,startAngle+270,90);
      g.setColor(borderColor);
      g.drawArc(super.x, super.y, super.width, super.height,startAngle+270,90);
	 
	  startAngle += ROTATION_SPEED;
	  
  }
  
      drawHandles(g);
	  drawMessage(g);
    }
    
}