import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Visitor extends VoidVisitorAdapter<MyClass> {

    @Override
    // ImportList
    public void visit(CompilationUnit cu, MyClass obj){
        super.visit(cu, obj);
        // System.out.println(cu.getImports().toString());
        obj.setImportList(cu.getImports().stream().map((e)-> {return e.getNameAsString();}).collect(Collectors.toList()));
//        System.out.println(obj.getImportList());

    }

    @Override
    public void visit(ClassOrInterfaceDeclaration item, MyClass arg){
        super.visit(item, arg);
        // Implemented, Extended
        arg.setExtendedList(item.getExtendedTypes().stream().map((e)->{return e.asString();})
                .collect(Collectors.toList()));
        arg.setImplementedList(item.getImplementedTypes().stream().map((e)->{return e.asString();})
                .collect(Collectors.toList()));
        // className
//        System.out.println("getName: " + item.getName());
        String classname = item.getNameAsString();
        arg.setClassName(classname);
        if(!MyClass.globalClasses.contains(classname)){
            MyClass.globalClasses.add(classname);
        }

        // classType
//        System.out.println("Modifier: " + handleModifier(item.getModifiers()));
        arg.setClassType(handleModifier(item.getModifiers()));

//        System.out.println("getExtendedTypes: " + item.getExtendedTypes().toString());
//        System.out.println("isInterface: " + item.isInterface());
        if (!item.isInterface()) {
//            System.out.println("getName    "+ item.getName());
            // Fields
            SetFieldsHelper(item, arg);

            // Methods
            List<Method> methods = item.getMethods().stream().map((e)->{
                String temMod = handleModifier(e.getModifiers());
                String temReturnType = e.getType().asString();
                String temName = e.getName().asString();
                String temParameters = e.getParameters().isEmpty()? "" : e.getParameters().toString();
//                System.out.println("getType   " + e.getType().asString());
//                System.out.println("getTypeParameters   " + e.getNameAsString()+" "+ e.getTypeParameters().toString());
//                System.out.println("getParameters   " + e.getNameAsString()+" "+ e.getParameters().toString());
                return new Method(temMod,temReturnType,temName,temParameters);
            }).collect(Collectors.toList());

            arg.setMethods(methods);

        }

        System.out.println();
    }

    private void SetFieldsHelper(ClassOrInterfaceDeclaration item, MyClass arg) {
        String temMod = "";
        String temType = "";
        String temName = "";
        List<Field> fieldsList = new ArrayList<>();
        Set<String> depTypeList = new HashSet<>();
        for(FieldDeclaration f : item.getFields()){
            f.setComment(null);
            temMod = handleModifier(f.getModifiers());
            temType = f.getVariable(0).getType().asString();
//
//                System.out.println(f.getVariable(0).getType().asString() + "  LIST OF WHAT??:    " + f.getVariable(0).getType().getElementType());
//            System.out.println("isReferenceType    "+ f.getVariable(0).getType().isReferenceType());
            temName = f.getVariable(0).getName().asString();
            fieldsList.add(new Field(temMod, temType, temName));
            depTypeList.add(temType);

            f.getVariable(0).getType().ifClassOrInterfaceType(e ->{e.asClassOrInterfaceType()
                    .getTypeArguments().ifPresent(
                    (tas) -> {
                        tas.forEach(i->depTypeList.add(i.toString()));
                        });});

        }
        arg.setFields(fieldsList);

        arg.setPreAssoList(depTypeList);
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
