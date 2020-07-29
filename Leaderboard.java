import java.awt.*;
import java.io.*;      //the File class
import java.util.Scanner;    //the Scanner class
import javax.swing.JOptionPane;
public class Leaderboard
{
   private PrintStream outfile;
	//constructors
   public Leaderboard()
   {
   }
   //other methods
   public void update(String filename, int score)
   {     
      Scanner infile = null;
      try{
         infile = new Scanner(new File(filename));
      }
      catch(FileNotFoundException e)
      {
         JOptionPane.showMessageDialog(null,"The file could not be found.");
         System.exit(0);
      }
      int numitems = infile.nextInt();
      int[] array = new int[numitems+1];
      for(int k = 0; k < numitems; k++)
      {
         array[k]= infile.nextInt();
      }
      infile.close();
      array[array.length-1] = score;
      sort(array);
      save(array);
      try{
         outfile = new PrintStream(new FileOutputStream("leaderboard.txt"));
      }
      catch(FileNotFoundException e)
      {
         JOptionPane.showMessageDialog(null,"The file could not be created.");
      }
      outfile.println("Scores");
      for(int i=0; i<array.length; i++)
         outfile.println(i+1 +". "+array[i]);
   }
   public void save(int[] array)
   {
      PrintStream outfile = null;
      try{
         outfile = new PrintStream(new FileOutputStream("output.txt"));
      }
      catch(FileNotFoundException e)
      {
         JOptionPane.showMessageDialog(null,"The file could not be created.");
      }
      outfile.println(""+array.length);
      for(int i=0; i<array.length; i++)
         outfile.println(""+array[i]);
   }
   public static void sort(int[] array)
   {
      int maxPos;
      for(int k = 0; k < array.length; k++)
      {
         maxPos = findMax(array, array.length - k);
         swap(array, maxPos, array.length - k - 1);
      }
   }
    public static void swap(int[] array, int a, int b)
   {
      int temp;
      temp=array[a];
      array[a]=array[b];
      array[b]=temp;
   }
   public static int findMax(int[] array, int upper)
   {
      int maxdex=0;
      for(int i = 1; i<upper; i++)
         if(array[maxdex]-array[i]>0)
              maxdex=i;
      return maxdex;
   }
}