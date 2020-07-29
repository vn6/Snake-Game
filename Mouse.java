import java.awt.*;
    public class Mouse 
   {
   	//data fields
      private Color myColor;
      private int myX;
      private int myY;
   	//constructors
      public Mouse()
      {
         myColor= Color.YELLOW;
         myX= 9;
         myY= 9;
      }
   	//getters
      public Color getColor()
      {
         return myColor;
      }
      public int getX()
      {
         return myX;
      }
      public int getY()
      {
         return myY;
      }
      //setters
      public void setColor(Color c)
      {
         myColor=c;
      }
      public void setX(int x)
      {
         myX=x;
      }
      public void setY(int y)
      {
         myY=y;
      }
      //other methods
      public void jump(int rightEdge, int bottomEdge)
      {
         myX = (int)(Math.random()* (rightEdge-1) + 5);
         myY = (int)(Math.random()* (bottomEdge-1) + 5);
      }  
      public void reset()
      {
         setX(9);
         setY(9);
      } 
   }