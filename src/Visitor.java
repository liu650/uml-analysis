import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            // set fields
            SetFieldsHelper(item, arg);

            // Methods


            List<Method> methods = item.getMethods().stream().map((e)->{
                String temMod = handleModifier(e);
                String temReturnType = e.getType().asString();
                String temName = e.getName().asString();
                List<String> temParameters = new ArrayList<>();

                return new Method(temMod,temReturnType,temName,temParameters);
            }).collect(Collectors.toList());
            System.out.println();
            System.out.println("methods  " + methods.toString());
//            // todo
//            arg.setClassName(item.getName().asString());
//            System.out.println("THIS NAME " + arg.getClassName());
        }

        System.out.println();
    }

    private void SetFieldsHelper(ClassOrInterfaceDeclaration item, MyClass arg) {
        String temMod = "";
        String temType = "";
        String temName = "";
        List<Field> fieldsList = new ArrayList<>();
        for(FieldDeclaration e : item.getFields()){
            e.setComment(null);
           System.out.println("getModifiers" + e.getModifiers().toString());
            temMod = handleModifier(e);
            temType = e.getVariable(0).getType().asString();
            temName = e.getVariable(0).getName().asString();
            fieldsList.add(new Field(temMod, temType, temName));
        }
        arg.setFields(fieldsList);
    }

    public String handleModifier(FieldDeclaration e){
        List<Modifier> lom = e.getModifiers();
        if(lom.isEmpty()) {
            return "public";
        }
        switch(lom.get(0).toString().trim()) {
            case "private":
                return "private";
            case "protected":
                return "protected";
            default:
                return "public";
        }
    }
    public String handleModifier(MethodDeclaration e){
        List<Modifier> lom = e.getModifiers();
        if(lom.isEmpty()) {
            return "public";
        }
        switch(lom.get(0).toString().trim()) {
            case "private":
                return "private";
            case "protected":
                return "protected";
            default:
                return "public";
        }
    }
}
