import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
public class MyClass {

    public static ArrayList<Triplet> globalDep = new ArrayList<>();
    public static ArrayList<String> globalClasses = new ArrayList<>();
    private ArrayList<Field> fields;
    private ArrayList<Method> methods;
    private Set<String> preAssoList = new HashSet<>();
    private List<String> extendedList;
    private List<String> implementedList;
    private List<String> importList;

    private String className;

    private String classType;
    // TODO
    private  List<Object> dependencyList;


    MyClass(){
        fields = new ArrayList<>();
        methods = new ArrayList<>();
        importList = new ArrayList<>();
        dependencyList = new ArrayList<>();
    }

    public List<String> getExtendedList() {
        return extendedList;
    }

    public void setExtendedList(List<String> extendedList) {
        this.extendedList = extendedList;
    }

    public List<String> getImplementedList() {
        return implementedList;
    }

    public void setImplementedList(List<String> implementedList) {
        this.implementedList = implementedList;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = new ArrayList<>(fields.size());
        this.fields.addAll(fields);
    }

    public ArrayList<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = new ArrayList<>(methods.size());
        this.methods.addAll(methods);
    }

    public List<String> getImportList() {
        return importList;
    }

    public void setImportList(List<String> importList) {
        this.importList = importList;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public List<Object> getDependencyList() {
        return dependencyList;
    }

    public void setDependencyList(List<Object> dependencyList) {
        this.dependencyList = dependencyList;
    }

    public Set<String> getPreAssoList() {
        return preAssoList;
    }

    public void setPreAssoList(Set<String> preAssoList) {
        this.preAssoList = preAssoList;
    }
    public void findAssoDependency() {
        for(String s : this.preAssoList){
            if (MyClass.globalClasses.contains(s)){
                globalDep.add(new Triplet(this.className,s,DependEnum.ASSOCIATION));
            }
        }
    }
    public void print(){
        StringBuilder s = new StringBuilder();
        s.append("\nimportList: " + this.importList.toString());
        s.append("\nclassType: " + this.classType);
        s.append("\nclassName: " + this.className);
        s.append("\nimplementedList: " + this.implementedList.toString());
        s.append("\nextendedList: " + this.extendedList.toString());
        s.append("\nfields: " + this.fields.stream().map((e)->{return e.toString();}).collect(Collectors.toList()).toString());
        s.append("\nmethods: " + this.methods.stream().map((e)->{return e.toString();}).collect(Collectors.toList()).toString());
        s.append("\n");
        System.out.println(s);;
    }
    public void findDependency(){
        // mark fields as Association [Do in visitor]
        findAssoDependency();
        // mark import as IMPORT
        for(String s: this.importList){
            Triplet t = new Triplet(this.className,s, DependEnum.IMPORT);
            if(!globalDep.contains(t)){
                globalDep.add(t);
            }
        }
        // mark implemented as Realization
        for(String s: this.implementedList){
            Triplet t = new Triplet(this.className,s, DependEnum.REALIZATION);
            if(!globalDep.contains(t)){
                globalDep.add(t);
            }
        }
        // mark extended as inheritance
        for(String s: this.extendedList){
            Triplet t = new Triplet(this.className,s, DependEnum.INHERITANCE);
            if(!globalDep.contains(t)){
                globalDep.add(t);
            }
        }
    }


}
