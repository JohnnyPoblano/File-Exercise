/*******************************************************************************************************************
 * FileExercise.java
 *******************************************************************************************************************/
import java.io.*;
import java.util.*;

public class FileExercise {
  public static void main(String[] args) throws IOException {
    useBufferedReader();   
    useDataInputStream();
  }
  //-------------------------------------------------------------------------------------------------------------------
  public static void useBufferedReader() throws IOException {
    System.out.println("------- Using PrintWriter and BufferedReader -----------------");

    FileWriter  fw = new FileWriter("file-1.txt");
    PrintWriter pw = new PrintWriter(fw);

    int nbrRecordsToWrite = getRandomNbr (5, 25); 
    for (int i = 0; i < nbrRecordsToWrite; i++)               // 1. Finish the for loop to write a random number of records (5 to 25) to the file. 
    {
       double randomDouble = getRandomNbr(0.00, 100.00);        // 2. Use getRandomNumber to randomly create a double number between 0.00 and 100.00 (not an integer)
       int randomInt = getRandomNbr(0, 100);           // 3. Use getRandomNumber to randomly create an integer number between 0 and 100.
      
       pw.println("Student#" + i);
       pw.println(randomDouble);
       pw.println(randomInt);
       
       int randomBoolean = getRandomNbr (0, 1);
       if (randomBoolean == 0) pw.println(false);
       if (randomBoolean == 1) pw.println(true);
       //pw.println(randomBoolean);
       
       pw.flush();
    }
    pw.close();
    
    //Read the file and print it
    FileReader fr = new FileReader("file-1.txt");
    BufferedReader br = new BufferedReader(fr);
    
    String student = "";
    while ((student = br.readLine()) != null) {
      System.out.println(student + "   " + br.readLine() + "   " + br.readLine() + "   " + br.readLine());
    }
    br.close();                                         // 4. Verify the output looks like:  Student#1  12.34  54  true
  }                                                     //    Verify the file-1.txt file contains all the records being displayed to the console.
   
  //-------------------------------------------------------------------------------------------------------------------
  public static void useDataInputStream() throws IOException {
    System.out.println("\n------- Using DataOutputStream -----------------");

    FileWriter  fw = new FileWriter("file-2.txt");
    PrintWriter pw = new PrintWriter(fw);

    try ( 
         DataOutputStream output =
         new DataOutputStream(new FileOutputStream("file-2.txt"));
    ) {
      int nbrRecordsToWrite = getRandomNbr (5, 25);            
      for (int i = 0; i < nbrRecordsToWrite; i++)                    // 5. Finish the for loop to write a random number of records (5 to 25) to the file.
      {
         double randomDouble = getRandomNbr(0.00, 100.00);                // 6. Use getRandomNumber to randomly create a double number between 0.00 and 100.00 (not an integer)
         int randomInt = getRandomNbr(0, 100);                   // 7. Use getRandomNumber to randomly create an integer number between 0 and 100.
        
         output.writeUTF("Student#" + i);
         output.writeDouble(randomDouble);
         output.writeInt(randomInt);
         
         int randomBoolean = getRandomNbr (0, 1);
         if (randomBoolean == 0) output.writeBoolean(false);
         if (randomBoolean == 1) output.writeBoolean(true);
      }
    }
    try ( 
         DataInputStream input =
         new DataInputStream(new FileInputStream("file-2.txt"));
    ) {
      while (true) { 
        System.out.println(input.readUTF() + "   " + input.readDouble() + "   " +  input.readInt() + "   " +  input.readBoolean());   // 8. Add the read of the boolean value to the end of this println
      }
    }
    catch (EOFException ex) {
      System.out.println("\nAll records have been  read");
    }
  }
  
  //Returns a random number from low to high, inclusive
  public static int getRandomNbr (int low, int high) {
    return (int)(Math.random() * ((high + 1) - low)) + low;
  }

  //Returns a random number from low to high, inclusive
  public static double getRandomNbr (double low, double high) {
    return (double)(Math.random() * ((high + 1.0) - low)) + low;
  }

}