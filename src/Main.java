import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;

import java.io.File;
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
            for (CompilationUnit c : cus){
                MyClass one = new MyClass();
                c.accept(new Visitor(),one);
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





        } catch (Exception e) {
            e.printStackTrace();
        }

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

}