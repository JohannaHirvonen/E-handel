import java.io.File;
import java.io.IOException;

public class Utility {
    private Utility(){

    }
    public static boolean createTextFile(String nameOfFile){
        File file = new File(fileName);
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
        }
        System.out.println();
        return true;
    }
}
