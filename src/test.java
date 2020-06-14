import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class test {

    @Test
    public void testOr1() {
        try {
            // Get project directory from user
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Enter the path of your Project (preferably the path to your Model):");
//            String projectDirectory = scanner.nextLine();
            Visitor visitor = new Visitor();
            String tinyVars = "testModel/tinyVars";
//            System.setIn(new ByteArrayInputStream(projectDirectory.getBytes()));
//
//            System.out.println("Path is : " + projectDirectory);


            // Establish JavaParser AST root
            ArrayList<File> files = new ArrayList<>();
            File root = new File(tinyVars);
            System.out.println("file children number: " + root.listFiles().length);

            SourceRoot sourceRoot = new SourceRoot(Path.of(root.getAbsolutePath()));
            System.out.println(sourceRoot.getRoot());

            // parse all java files under the package
            sourceRoot.tryToParse("");
            List<CompilationUnit> cus = (ArrayList<CompilationUnit>) sourceRoot.getCompilationUnits();
            for (CompilationUnit c : cus){
                MyClass one = new MyClass();
                c.accept(new Visitor(),one);
            }
//            cus.get(0).accept(new ModifierVisitor<Void>() {
//                /**
//                 * For every if-statement, see if it has a comparison using "!=".
//                 * Change it to "==" and switch the "then" and "else" statements around.
//                 */
//                @Override
//                public Visitable visit(IfStmt n, Void arg) {
//                    // Figure out what to get and what to cast simply by looking at the AST in a debugger!
//                    n.getCondition().ifBinaryExpr(binaryExpr -> {
//                        if (binaryExpr.getOperator() == BinaryExpr.Operator.NOT_EQUALS && n.getElseStmt().isPresent()) {
//                        /* It's a good idea to clone nodes that you move around.
//                            JavaParser (or you) might get confused about who their parent is!
//                        */
//                            Statement thenStmt = n.getThenStmt().clone();
//                            Statement elseStmt = n.getElseStmt().get().clone();
//                            n.setThenStmt(elseStmt);
//                            n.setElseStmt(thenStmt);
//                            binaryExpr.setOperator(BinaryExpr.Operator.EQUALS);
//                        }
//                    });
//                    return super.visit(n, arg);
//                }
//            }, null);

            // save to a directory
             sourceRoot.saveAll(sourceRoot.getRoot().resolve(Paths.get("../../output")));
//            sourceRoot.parse("", new ParserConfiguration(), (SourceRoot.Callback) (path, absPath, result) -> {
//
//                return SourceRoot.Callback.Result.SAVE;
//            });
            System.out.println(sourceRoot.getCompilationUnits().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRegex() {
        // test regex
        System.out.println("PASS~~~~~~~~~~~~");
        System.out.println("ADAD.java".matches("(?i).+\\.java$"));
        System.out.println("helo.java.java".matches("(?i).+\\.java$"));
        System.out.println("helo.JAVA".matches("(?i).+\\.java$"));
        System.out.println("helo.JAVA.JAVA".matches("(?i).+\\.java$"));
        System.out.println("..java".matches("(?i).+\\.java$"));
        System.out.println("C:/hello/ad.java".matches("(?i).+\\.java$"));

        System.out.println("FALSE~~~~~~~~~~~~");
        System.out.println("hellojava".matches("(?i).+\\.java$"));
        System.out.println("Hello.java.".matches("(?i).+\\.java$"));
        System.out.println(".java".matches("(?i).+\\.java$"));
        System.out.println("java".matches("(?i).+\\.java$"));
        System.out.println("java".matches("(?i).+\\.java$"));
    }

}
