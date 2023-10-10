import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Utility {
    private Utility(){

    }
    public static boolean createTextFile(String nameOfFile){
        File file = new File(nameOfFile);
        try{
            if(file.createNewFile()){
                return true;
            }
        } catch (IOException e){
            System.out.println("An error occurred! " + e.getMessage());
            //Unchecked exception: do not catch - supposed to crash the program if this happens
            throw new RuntimeException(e);
        }
        return false;
    }

        public static boolean addItemToTextFile(String fileString, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName, true);
            PrintStream printStream = new PrintStream(fos);
            Scanner scan = new Scanner(new File(fileName));
            if(!scan.hasNextLine()){
                printStream.print(fileString);
            } else {
                printStream.print("\n" + fileString);
            }

            fos.close();
            printStream.close();
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred! " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static String addWhiteSpace(String text, int maxAmount){
        if(text.length() > maxAmount){
            return text.substring(0, maxAmount - 3) + "...";
        }
        return text + " ".repeat(maxAmount - text.length());
    }

}
