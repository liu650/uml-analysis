import java.util.ArrayList;
import java.util.List;

public class MyClass {
    private List<Object> fields;
    private List<Object> methods;
    private List<Object> importList;
    private String className;
    private classEnum classType;
    private  List<Object> dependencyList;


    MyClass(){
        fields = new ArrayList<>();
        methods = new ArrayList<>();
        importList = new ArrayList<>();
        dependencyList = new ArrayList<>();
    }



    public List<Object> getFields() {
        return fields;
    }

    public void setFields(List<Object> fields) {
        this.fields = fields;
    }

    public List<Object> getMethods() {
        return methods;
    }

    public void setMethods(List<Object> methods) {
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
