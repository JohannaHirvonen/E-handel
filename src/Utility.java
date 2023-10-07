import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Utility {
    private Utility(){

    }
    public static boolean createTextFile(String nameOfFile){
        File file = new File(nameOfFile);
        try{
            if(file.createNewFile()){
                System.out.println("The file has been created: " + file.getName());
            } else {
                System.out.println("The file " + file.getName() + " finns redan!");
                System.out.println();
                return false;
            }
        } catch (IOException e){
            System.out.println("An error occurred! " + e.getMessage());
            //TODO crash
        }
        System.out.println();
        return true;
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
        }

        return false;
    }
}
