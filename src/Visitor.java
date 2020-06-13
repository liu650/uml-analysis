import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
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

        // className
        System.out.println("getName: " + item.getName());
        arg.setClassName(item.getNameAsString());

        // classType
        System.out.println("Modifier: " + handleModifier(item.getModifiers()));
        arg.setClassType(handleModifier(item.getModifiers()));

//        System.out.println("getExtendedTypes: " + item.getExtendedTypes().toString());
//        System.out.println("isInterface: " + item.isInterface());
        if (!item.isInterface()) {
            System.out.println("getName    "+ item.getName());
            // Fields
            SetFieldsHelper(item, arg);

            // Methods
            List<Method> methods = item.getMethods().stream().map((e)->{
                String temMod = handleModifier(e.getModifiers());
                String temReturnType = e.getType().asString();
                String temName = e.getName().asString();
                String temParameters = e.getParameters().toString();
                System.out.println("getType   " + e.getType().asString());
//                System.out.println("getTypeParameters   " + e.getNameAsString()+" "+ e.getTypeParameters().toString());
                System.out.println("getParameters   " + e.getNameAsString()+" "+ e.getParameters().toString());
                return new Method(temMod,temReturnType,temName,temParameters);
            }).collect(Collectors.toList());
            System.out.println();
            System.out.println("methods  " + methods.toString());
            arg.setMethods(methods);

            // Import
//            for(Parameter p : e.getParameters()){
//                System.out.println(p.getName());
//                System.out.println(p.getType());
//            }



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
        for(FieldDeclaration f : item.getFields()){
            f.setComment(null);
            temMod = handleModifier(f.getModifiers());
            temType = f.getVariable(0).getType().asString();
            temName = f.getVariable(0).getName().asString();
            fieldsList.add(new Field(temMod, temType, temName));
        }
        arg.setFields(fieldsList);
    }

    public String handleModifier(List<Modifier> lom){

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
