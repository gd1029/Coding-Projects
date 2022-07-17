/*
 *  ===============================================================================
 *  MovingRectangle.java : Creates a rectangle shape.
 *  Draws a rectangle depending on user inputs with handles and message.
 *  ===============================================================================
 */

 /* 
  * Name: Grace Du
  * UPI: mdu119
  *	ID : 650459747
  */
import java.awt.*;

public class MovingRectangle extends MovingShape{

        
    public MovingRectangle(){
        super();
    }
    public MovingRectangle(int one){
        super.width = super.height = one;
        
    }
    public MovingRectangle(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, String m,int pathType){
        super(x ,y ,w ,h ,mw ,mh ,bc,fc, m, pathType);
        
    }
    public double getArea(){
        return super.height*super.width;
        
    }
    public void draw(Graphics g) {
	    g.setColor(fillColor);
	    g.fillRect(this.x, this.y, super.width, super.height);
	    g.setColor(borderColor);
	    g.drawRect(this.x, this.y, super.width, super.height);
	    drawHandles(g);
		drawMessage(g);
    }
    

public boolean contains(Point mousePt) {
    return(this.x <= mousePt.x && mousePt.x <= (this.x + super.width + 1)  &&  this.y <= mousePt.y && mousePt.y <= (this.y + super.height + 1)); 
}
   
  
    }
    

    
