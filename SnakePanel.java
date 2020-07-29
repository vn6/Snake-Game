import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;      //the File class
import java.util.Scanner;    //the Scanner class
import javax.swing.JOptionPane;
import java.util.Comparator;

public class SnakePanel extends JPanel
{
   private JLabel[][] board;
   private JLabel label, scoreLabel;
   private Snake snake;
   private Mouse mouse;
   private Timer t;
   private int size;
   private int speed, fast, normal, slow;
   private int games;
   private boolean alive;
   private Leaderboard leaderboard;
//constructor   
   public SnakePanel()
   {
      snake = new Snake();
      mouse = new Mouse();
      size = 30;
      fast = 10;
      normal = 70;
      slow = 200;
      speed = normal;
      games = 0;
      alive = true;
      leaderboard = new Leaderboard();
      
      setLayout(new BorderLayout());
      
      JPanel north = new JPanel();
      north.setLayout(new FlowLayout());
      add(north);
      label = new JLabel("Welcome the Snake Game -- Use the arrow keys to play");
      add(label, BorderLayout.NORTH);
      
      JPanel center = new JPanel();
      center.setLayout(new GridLayout(size, size));
      add(center, BorderLayout.CENTER);
   
      board = new JLabel[size][size];
      for(int r = 0; r < size; r++)
         for(int c = 0; c < size; c++)
         {
            board[r][c] = new JLabel();
            board[r][c].setOpaque(true);
            board[r][c].setBackground(Color.black);
            center.add(board[r][c]);
         }
      
      JPanel south = new JPanel();
      south.setLayout(new BorderLayout());
      add(south, BorderLayout.SOUTH);
      
      scoreLabel = new JLabel("Length: "+ snake.getLength());
      south.add(scoreLabel, BorderLayout.NORTH);
      
      JPanel south1 = new JPanel();
      south1.setLayout(new FlowLayout());
      south.add(south1, BorderLayout.SOUTH);
      
      JButton Instructions = new JButton("Instructions");
      Instructions.addActionListener(new InstructionsListener());
      south1.add(Instructions);
      
      JButton Settings = new JButton("Settings");
      Settings.addActionListener(new SettingsListener());
      south1.add(Settings);
      
      JButton Reset = new JButton("Reset");
      Reset.addActionListener(new ResetListener());
      south1.add(Reset);
      
      JButton QuitButton = new JButton("Quit");
      QuitButton.addActionListener(new QuitListener());
      south1.add(QuitButton);
      
      t = new Timer(speed,new Listener());
      t.start();
      addKeyListener(new Key());
      setFocusable(true);      
   
      update();
   }
   private void update()
   {
      if(collide()){
         label.setText("You Died!");
         games++;
         alive = false;
         leaderboard.update("output.txt", snake.getLength());         
      }
      else if(eat()){
         snake.grow();
         mouse.jump(25, 25);
         scoreLabel.setText("Length: "+ snake.getLength());
      }
      else if (snake.getDirection() > 0){
         snake.move(snake.getDirection());
      }
      for(int r = 0; r < size; r++)
         for(int c = 0; c < size; c++){
            board[r][c].setBackground(Color.black);
         }
      board[mouse.getY()][mouse.getX()].setBackground(Color.YELLOW);
      
      for(int i=0; i<snake.getLength(); i++){
         if(snake.getY(i)<30&&snake.getY(i)>0)
            if(snake.getX(i)<30&&snake.getX(i)>0)
               board[snake.getY(i)][snake.getX(i)].setBackground(snake.getColor());
      }
   }
   private void reset()
   {
      for(int r = 0; r < size; r++)
         for(int c = 0; c < size; c++){
            board[r][c].setBackground(Color.black);
         }
      label.setText("Welcome the Snake Game");
      snake.reset();
      mouse.reset();
      scoreLabel.setText("Length: "+ snake.getLength());
      requestFocusInWindow();
      alive = true;
      update();  
   }
   private Boolean collide()
   {
      Boolean s = false;
      if(snake.getX(0)<=0||snake.getX(0)>=size-1)
         s=true;
      else if(snake.getY(0)<=0||snake.getY(0)>=size-1)
         s=true;
      for(int i = 1; i<snake.getLength(); i++){
         if(snake.getX(0)==snake.getX(i)&&snake.getY(0)==snake.getY(i))
            s=true;
      }
      return s;
   }
   private Boolean eat()
   {
      Boolean a = false;
      if(snake.getX(0)==mouse.getX()&&snake.getY(0)==mouse.getY())
         a=true;
      return a;
   }
   private class InstructionsListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String message = "";
         message += "Intructions \nPress the arrow keys to play. The object of the game is to grow by eating the mice, the yellow squares. Each mouse you eat increases your length by four. \nMake sure you don't bump into yourself or the edges of the screen, or else you die. You can change the speed and color using the settings button. \nLook in leaderboard.txt for highscores.";
         JOptionPane.showMessageDialog(null, message);
      }
   }
   private class SettingsListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String a;
         do{
         String message = "";
         message = message + "Speed: \n 1. Slow \n 2. Normal \n 3. Fast";
         a = JOptionPane.showInputDialog(message);
         }while(a.equals("1")==false&&a.equals("2")==false&&a.equals("3")==false);
         if(a.equals("1"))
            speed=slow;
         else if(a.equals("2"))
            speed=normal;
         else
            speed=fast;
            
         do{   
         String message = "";
         message = message + "Color: \n 1. Red \n 2. Green \n 3. Blue";
         a = JOptionPane.showInputDialog(message);
         }while(a.equals("1")==false&&a.equals("2")==false&&a.equals("3")==false);
         if(a.equals("1"))
            snake.setColor(Color.RED);
         else if(a.equals("2"))
            snake.setColor(Color.GREEN);
         else
            snake.setColor(Color.BLUE);
         
         reset();
      }
   }
   private class ResetListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         reset();  
      }
   }
   private class QuitListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(1);
      }
   }
         private class Key extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         if(alive){
            if(e.getKeyCode()==KeyEvent.VK_UP){
               snake.setDirection(1);
               snake.move(1);
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN){
               snake.setDirection(2);
               snake.move(2);
            }
            if(e.getKeyCode()==KeyEvent.VK_LEFT){
               snake.setDirection(3);
               snake.move(3);
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT){
               snake.setDirection(4);
               snake.move(4);
            }
         }
      }
   }
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         update();
      }
   } 
}