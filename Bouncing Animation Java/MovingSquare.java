/*
 *  ===============================================================================
 *  MovingSquare.java : Creates a Square shape.
 *  Draws a sqaure depending on user inputs with handles and message.
 *  A square cannot be turned into a rectangle.
 *  ===============================================================================
 */

 /* 
  * Name: Grace Du
  * UPI: mdu119
  *	ID : 650459747
  */

import java.awt.*;
public class MovingSquare extends MovingRectangle{
       
       
    public MovingSquare(){
		        super();
    }
    public MovingSquare(int one){
        int s = one;
        
    }
    public MovingSquare(int x,int y,int s,int mw,int mh,Color fillColor,Color borderColor,String m,int pathType){
		
        super(x ,y ,s ,s,mw ,mh ,fillColor,  borderColor,m,pathType);
        
    }
	public void setWidth(int num){
		
	        super.width = num;
	        super.height = num;
        
	    }
	    public void setHeight(int num){
			
	        super.width = num;
	        super.height = num;
        
	    }
	    

    
  
    }
    
