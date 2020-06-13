import java.util.ArrayList;
import java.util.List;

public class MyClass {

    // TODO
    private List<Field> fields;
    // done
    private List<Method> methods;
    // TODO
    private List<Object> importList;
    // done
    private String className;
    // TODO
    private classEnum classType;
    // TODO
    private  List<Object> dependencyList;


    MyClass(){
        fields = new ArrayList<>();
        methods = new ArrayList<>();
        importList = new ArrayList<>();
        dependencyList = new ArrayList<>();
    }



    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public List<Object> getImportList() {
        return importList;
    }

    public void setImportList(List<Object> importList) {
        this.importList = importList;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public classEnum getClassType() {
        return classType;
    }

    public void setClassType(classEnum classType) {
        this.classType = classType;
    }

    public List<Object> getDependencyList() {
        return dependencyList;
    }

    public void setDependencyList(List<Object> dependencyList) {
        this.dependencyList = dependencyList;
    }



}
