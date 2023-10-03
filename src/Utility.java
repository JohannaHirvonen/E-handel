import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Utility {
    private Utility(){

    }
    public static boolean createTextFile(String nameOfFile){
        File file = new File(nameOfFile);
        try{
            if(file.createNewFile()){
                System.out.println("Filen har skapats: " + file.getName());
            } else {
                System.out.println("Filen " + file.getName() + " finns redan!");
                System.out.println();
                return false;
            }
        } catch (IOException e){
            System.out.println("Ett fel uppstod när vi skulle skapa filen. " + e.getMessage());
            //TODO crash
        }
        System.out.println();
        return true;
    }

    public static boolean addItemToTextFile(String fileString, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName, true);
            PrintStream printStream = new PrintStream(fos);

            printStream.print("\n" + fileString);

            fos.close();
            printStream.close();
            return true;
        } catch (Exception e) {
            System.out.println("Det blev galet! " + e.getMessage());
        }

        return false;
    }
}
