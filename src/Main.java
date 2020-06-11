import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Get project directory
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the path of your Project (preferably the path to your Model):");
            String projectDirectory = scanner.nextLine();
            System.out.println("Path is : " + projectDirectory);

            // Traverse Project
            ArrayList<File> a;
            File root = new File(projectDirectory);

            for (File f: root.listFiles()){
                helper(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // recursively traverse the whole directory to parse every file.
    private static void helper(File f){
        // if this is a java file
        if (f.isFile() && f.getName().matches("(?i).+\\.java$")){
           parseJavaFile(f);
        } else if (f.isDirectory()){
            for (File child: f.listFiles()){
                helper(child);
            }
        }
    }

    // parse java file.
    private static void parseJavaFile(File f) {

    }

}