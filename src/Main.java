import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    // size of Frame
    // TODO: adjust frame size
    static final int WIDTH = 1466;
    static final int HEIGHT =  1466;

    // format of boxes
    // TODO: ADJUST width, height of box
    // TODO: set color?
    final int BoxWidth = 20;
    final int boxHeight = 50;
    final String BoxColor = "Red";

    // TODO: unsure about modifiers
    // List of boxes to draw
    public ArrayList<Box> boxes = new ArrayList<>();
    //public ArrayList<Arrow> arrows = new ArrayList<>();


    public static void main(String[] args) {

        try {
            // Get project directory from user
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the path of your Project (preferably the path to your Model):");
            String projectDirectory = scanner.nextLine();
            System.out.println("Path is : " + projectDirectory);

            // Establish JavaParser AST root
            ArrayList<File> files = new ArrayList<>();
            File root = new File(projectDirectory);

            SourceRoot sourceRoot = new SourceRoot(root.toPath());

            // parse all java files under the package
            sourceRoot.tryToParse("");
            sourceRoot.saveAll();
            sourceRoot.getCompilationUnits();
//            sourceRoot.parse("", new ParserConfiguration(), (SourceRoot.Callback) (path, absPath, result) -> {
//
//                return SourceRoot.Callback.Result.SAVE;
//            });





        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO: get info from results of java parser, fill (list)arrows and (list)boxes
        fill();
        // TODO: draw uml, complete draw()
        draw();
    }

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

    private static void draw() {
        JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new DiagramGenerator();
        canvas.setSize(WIDTH, HEIGHT);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        // TODO: add boxes and arrows to frame
        // TODO: iteratively(for loop) add arrows and boxes to frame
        //TODO Graph DRAWING: recommended approach --Graphics2D (Difficult add on Canvas !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!)
    }

    private static void fill() {
        // TODO: create boxes and arrows object, add them to lists(arrows, boxes defined as public fields of main class)
    }
}