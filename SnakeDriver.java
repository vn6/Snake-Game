
   import javax.swing.JFrame;
    public class SnakeDriver
   {
       public static void main(String[] args)
      { 
         JFrame frame = new JFrame("Snake Game");
         frame.setSize(408, 438);    
         frame.setLocation(0, 0);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         SnakePanel p = new SnakePanel();
         frame.setContentPane(p);
         p.requestFocus();
         frame.setVisible(true);
      }
   }
