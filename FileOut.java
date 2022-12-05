package p1cs232;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.w3c.dom.Node;
/**
 * A class that prints to an output file using one @param
 * but has to be concatenated the same
 * 
 * @author Hunter
 *
 */
//output class we were given last semester that can print into a file with all types
public class FileOut {   
     PrintStream fout; 
   
   FileOut(String filename){
     try{
       fout= new PrintStream ( new FileOutputStream(filename));
     }catch(IOException fo){
         System.out.println(fo); 
      }
    }

   public void writer(String string)
   {
     
         fout.println(string);
      
   }
   public void writer(int out)
   {
     
         fout.println(out);
      
   }
   public void writer(char out)
   {
     
         fout.println(out);
      
   }
   public void writer(double out)
   {
     
         fout.println(out);
      
   }
   public void writer(float out)
   {
     
         fout.println(out);
      
   }
   public void writer(Node first)
   {
     
         fout.println(first);
      
   }
   public void writer(long out) {
	   fout.println(out);
   }

/*following all write on one line*/

   public void write(long out) {
	   fout.println(out);
   }
   public void write(String out)
   {
     
         fout.print(out);
      
   }

   public void write(int out)
   {
     
         fout.print(out);
      
   }

   public void write(char out)
   {
     
         fout.print(out);
      
   }

   public void write(float out)
   {
     
         fout.print(out);
      
   }

   public void write(double out)
   {
     
         fout.print(out);
      
   }
   public void write(Node out)
   {
     
         fout.println(out);
      
   }
 
}
