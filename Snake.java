import java.awt.*;
    public class Snake 
   {
   	//data fields
      private Color myColor;
      private int myLength, myDirection;
      private int[] myX;
      private int[] myY;
   	//constructors
      public Snake()
      {
         myColor= Color.GREEN;
         myLength=1;
         myX= new int[1];
         myY= new int[1];
         myX[0]= 5;
         myY[0]=5;
         myDirection = -1;
      }
   	//getters
      public Color getColor()
      {
         return myColor;
      }
      public int getLength()
      {
         return myLength;
      }
      public int getDirection()
      {
         return myDirection;
      }
      public int getX(int i)
      {
         return myX[i];
      }
      public int getY(int i)
      {
         return myY[i];
      }
      //setters
      public void setColor(Color c)
      {
         myColor=c;
      }
      public void setLength(int l)
      {
         myLength=l;
         int[] x = new int[l];
         int[] y = new int[l];
         for(int i=0; i<l; i++){
            x[i]=myX[i];
            y[i]=myY[i];
         }
         myX=x;
         myY=y;
      }
      public void setDirection(int d)
      {
         myDirection=d;
      }
      public void setX(int i, int value)
      {
         myX[i]=value;
      }
      public void setY(int i, int value)
      {
         myY[i]=value;
      }

      //other
      public void move(int direction) // 1=up, 2=down 3=left 4=right
      {
         int[] x = new int[myLength];
         int[] y = new int[myLength];
         for(int i=0; i<myLength; i++){
            x[i]=myX[i];
            y[i]=myY[i];
         }
         if(direction==1)
            myY[0]--;
         else if(direction==2)
            myY[0]++;
         else if(direction==3)
            myX[0]--;
         else if(direction==4)
            myX[0]++;
         
         for(int i=1; i<myLength; i++){
            myX[i]=x[i-1];
            myY[i]=y[i-1];
         }
      }
      public void grow()
      {
         int[] x = new int[myLength+4];
         int[] y = new int[myLength+4];
         for(int i=0; i<myLength; i++){
            x[i]=myX[i];
            y[i]=myY[i];
         }
         int direction=0;
         if(myLength==1)
            direction = myDirection;
         else if(myY[myLength-2]-myY[myLength-1]==-1)//up
            direction = 1;
         else if(myY[myLength-2]-myY[myLength-1]==1)//down
            direction = 2;
         else if(myX[myLength-2]-myX[myLength-1]==-1)//left
            direction = 3;
         else if(myX[myLength-2]-myX[myLength-1]==1)//right
            direction = 4;
            
         if(direction==1){ //up
            for(int i = 0; i<4; i++){
               x[myLength+i]=myX[myLength-1];
               y[myLength+i]=myY[myLength-1]+i+1;
            }
         }else if(direction==2){//down
            for(int i = 0; i<4; i++){
               x[myLength+i]=myX[myLength-1];
               y[myLength+i]=myY[myLength-1]-i-1;
            }
         }else if(direction==3){//left
            for(int i = 0; i<4; i++){
               x[myLength+i]=myX[myLength-1]+i+1;
               y[myLength+i]=myY[myLength-1];
            }
         }else if(direction==4){//right
            for(int i = 0; i<4; i++){
               x[myLength+i]=myX[myLength-1]-i-1;
               y[myLength+i]=myY[myLength-1];
            }
         }
         myX=x;
         myY=y;
         myLength+=4;
      }
      public void reset()
      {
         setLength(1);
         setX(0, 5);
         setY(0, 5);
         setDirection(-1);
      }
      
   }