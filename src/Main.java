import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
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
            System.out.println("try this: testModel/tinyVars ... ");
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
            System.out.println("All Classes Found:  " + MyClass.globalClasses.toString());
            MyClass.globalDep.forEach((e) -> {
                System.out.println("\n" + e.toString());
            });

            sourceRoot.saveAll();
//            sourceRoot.parse("", new ParserConfiguration(), (SourceRoot.Callback) (path, absPath, result) -> {
//
//                return SourceRoot.Callback.Result.SAVE;
//            });


            // draw classes on Canvas using JFrame
            initialize(allClasses);
            cleanUpJavaCache();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initialize(ArrayList<MyClass> allClasses){
        JFrame jFrame = new JFrame(); //initialize
        jFrame.setSize(1000, 770);// set size of window
        jFrame.setBackground(Color.WHITE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set open-close model
        jFrame.setContentPane(new DiagramGenerator(allClasses));// set content
        jFrame.setTitle("UML Graph");//set title
        jFrame.setVisible(true);// framework is visible
        jFrame.setLocationRelativeTo(null);// the window at middle of the screen
    }

    // recursively traverse the whole directory to parse every file.
    private static void helper(File f)  throws FileNotFoundException {
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
    private static void parseJavaFile(File f)  throws FileNotFoundException {

//        CompilationUnit cu = sourceRoot.parse("", "Blabla.java");
        CompilationUnit cu = StaticJavaParser.parse(f);
    }

    private static void cleanUpJavaCache() throws IOException {
        String productionPath =  "./out/production";
        if (new File(productionPath).exists()) {
            Runtime.getRuntime().exec("rm -r " + productionPath);
            System.out.println("Java cache is removed successfully.");
        }
    }
}
