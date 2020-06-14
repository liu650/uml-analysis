import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            // Get project directory from user
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the path of your Project (preferably the path to your Model):");
            System.out.println("try this: testModel/ast, testModel/libs... ");
            String projectDirectory = scanner.nextLine();
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

            initialize();
            cleanUpJavaCache();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //sudo position

    // recursively traverse the whole directory to parse every file.
    private static void helper(File f)  throws FileNotFoundException{
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
    private static void parseJavaFile(File f) throws FileNotFoundException {

//        CompilationUnit cu = sourceRoot.parse("", "Blabla.java");
        CompilationUnit cu = StaticJavaParser.parse(f);
    }

    private static void initialize(){
        final int numberOfBox = 9; // should not be modified
        ArrayList<Position> classPositions = DiagramGenerator.findAllPosition(new Position(250, 170), 200, 210);

        //TODO: Box missing  !!!field and !!!method information
        List<Box> boxes = getBoxes(classPositions, numberOfBox);

        List<Arrow> arrows = new ArrayList<>();
        JFrame jFrame = new JFrame(); //initialize
        jFrame.setSize(1000, 770);// set size of window
        jFrame.setBackground(Color.WHITE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set open-close model
        jFrame.setContentPane(new DiagramGenerator(boxes, arrows));// set content
        jFrame.setTitle("Sample Graph");//set title
        jFrame.setVisible(true);// framework is visible
        jFrame.setLocationRelativeTo(null);// the window at middle of the screen
    }

    private static List<Box> getBoxes(ArrayList<Position> classPositions, int numberOfBox) {
        List<Box> boxes = new ArrayList<>();
        for (int i = classPositions.size() - numberOfBox; i < classPositions.size(); i++){
            int boxIndex =  i + numberOfBox - classPositions.size();
            boxes.add(new Box("Class " + Integer.toString(boxIndex), classPositions.get(i)));
        }
        return boxes;
    }

    private static void cleanUpJavaCache() throws IOException {
        String productionPath =  "./out/production";
        if (new File(productionPath).exists()) {
            Runtime.getRuntime().exec("rm -r " + productionPath);
            System.out.println("Java cache is removed successfully.");
        }
    }
}
