import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        ArrayList<MyClass> allClasses = new ArrayList<>();

        try {
            // Get project directory from user
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the path of your Project (preferably the path to your Model):");
            System.out.println("try this: testModel/ast ... ");
            String projectDirectory = scanner.nextLine();

            //TODO: just for testing
            if (projectDirectory.equals("  ")){
                projectDirectory = "testModel/tinyVars";
            }

            System.out.println("Path is : " + projectDirectory);

            // Establish JavaParser AST root
            ArrayList<File> files = new ArrayList<>();
            File root = new File(projectDirectory);
            SourceRoot sourceRoot = new SourceRoot(root.toPath());

            // parse all java files under the package
            sourceRoot.tryToParse("");

            // Extract Info
            List<CompilationUnit> cus = (ArrayList<CompilationUnit>) sourceRoot.getCompilationUnits();
            System.out.println("Number of java files: " + sourceRoot.getCompilationUnits().size());
            for (CompilationUnit c : cus) {
                MyClass one = new MyClass();
                c.accept(new Visitor(), one);
                one.findDependency();
                one.print();
                allClasses.add(one);
            }

            MyClass.globalDep.forEach((e) -> {
                System.out.println("\n" + e.toString());
            });

            // sourceRoot.saveAll();
//            sourceRoot.parse("", new ParserConfiguration(), (SourceRoot.Callback) (path, absPath, result) -> {
//
//                return SourceRoot.Callback.Result.SAVE;
//            });

            // Ask Target Class from user.
            String target = "!@#!@#!@#";
            System.out.println("Due to some constraints regarding the size limit of the Image, we only support showing a portion of the classes.");
            System.out.println("Here are all Classes Found:  " + MyClass.globalClasses.toString());
            System.out.println("Please enter a class that you want to see in particular: (Enter nothing to view all)");
            target = scanner.nextLine();
            while (!checkExists(target)) {
                    System.out.println("Invalid Input: Try Again");
                    System.out.println("Here are all Classes Found:  " + MyClass.globalClasses.toString());
                    target = scanner.nextLine();
                }


            // Drawasses on Canvas using JFrame
            initialize(allClasses, target);
            cleanUpJavaCache();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean checkExists(String target) {
        System.out.println( " asd asd a tage   " + target);
        if (target.equalsIgnoreCase("")) {
            return true;
        }
        for (String s : MyClass.globalClasses){
            if (s.equalsIgnoreCase(target)){
                return true;
            }
        }
        return false;
    }

    private static void initialize(ArrayList<MyClass> allClasses, String target){
        JFrame jFrame = new JFrame(); //initialize
        jFrame.setSize(1000, 770);// set size of window
        jFrame.setBackground(Color.WHITE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set open-close model
        jFrame.setContentPane(new DiagramGenerator(allClasses,target));// set content
        jFrame.setTitle("UML Graph");//set title
        jFrame.setVisible(true);// framework is visible
        jFrame.setLocationRelativeTo(null);// the window at middle of the screen
    }


    private static void cleanUpJavaCache() throws IOException {
        String productionPath =  "./out/production";
        if (new File(productionPath).exists()) {
            Runtime.getRuntime().exec("rm -r " + productionPath);
            System.out.println("Java cache is removed successfully.");
        }
    }
}
