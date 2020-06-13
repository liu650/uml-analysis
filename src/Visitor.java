import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
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
            System.out.println("getFields: " + item.getFields().toString());
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
