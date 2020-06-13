import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class Visitor extends VoidVisitorAdapter<MyClass> {

    @Override
    public void visit(MethodCallExpr methodCall, MyClass arg){
        super.visit(methodCall, arg);

    }

    @Override
    public void visit(ClassOrInterfaceDeclaration item, MyClass arg){
        super.visit(item, arg);
        System.out.println("getName: " + item.getName());
//        System.out.println("getExtendedTypes: " + item.getExtendedTypes().toString());
//        System.out.println("isInterface: " + item.isInterface());
        if (!item.isInterface()) {
            // fields
            List<Pair<String,String>> fieldsList = new ArrayList<Pair<String,String>>();
            for(FieldDeclaration e : item.getFields()){
                //left type and right name
                e.setComment(null);


                System.out.println("getModifiers" + e.getModifiers().toString());
                String[] tempArray = e.toString().split(" ");
                fieldsList.add(new Pair(tempArray[0],tempArray[1]));

                //System.out.println("getFields " + e.toString());
//                System.out.println("getDeclarationAsString " + e.getName());
            }
            for(Pair p : fieldsList){
                //left type and right name
                System.out.println("left " + p.getL());
                System.out.println("right " + p.getR());
                //System.out.println("getFields " + e.toString());
//                System.out.println("getDeclarationAsString " + e.getName());
            }
//            System.out.println("getFields: " + item.getFields().toString());
            List<String> methodsNames = new ArrayList<>();
//            item.getMethods().stream().map((e)->{
//                return methodsNames.add(e.getDeclarationAsString());
//            });
            for(MethodDeclaration e : item.getMethods()){
                methodsNames.add(e.getName().asString());
//                System.out.println("getDeclarationAsString " + e.getName());
            }
            System.out.println("methodsNames " + methodsNames.toString());
//            // todo
//            arg.setClassName(item.getName().asString());
//            System.out.println("THIS NAME " + arg.getClassName());
        }

        System.out.println();
    }
}
