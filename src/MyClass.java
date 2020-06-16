import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
public class MyClass {

    //for drawing fields and methods
    private int lineHeight = 11;
    private int maxStringLength = 24;

    public static ArrayList<Triplet> globalDep = new ArrayList<>();
    public static ArrayList<String> globalClasses = new ArrayList<>();
    private ArrayList<Field> fields;
    private ArrayList<Method> methods;
    private Set<String> preAssoList = new HashSet<>();
    private ArrayList<String> extendedList;
    private ArrayList<String> implementedList;
    private ArrayList<String> importList;
    private ArrayList<String> associationList;

    private String className;

    private String classType;
    // TODO
    private  ArrayList<Object> dependencyList;

    MyClass(String className){
        this.className = className;
    }

    MyClass(){
        fields = new ArrayList<>();
        methods = new ArrayList<>();
        importList = new ArrayList<>();
        dependencyList = new ArrayList<>();
        implementedList=new ArrayList<>();
        extendedList=new ArrayList<>();
        className = "";
        associationList = new ArrayList<>();
    }

    public ArrayList<String> getExtendedList() {
        return extendedList;
    }

    public void setExtendedList(List<String> extendedList) {
        this.extendedList = new ArrayList<>();
        this.extendedList.addAll(extendedList);
    }

    public ArrayList<String> getImplementedList() {
        return implementedList;
    }

    public void setImplementedList(List<String> implementedList) {
        this.implementedList = new ArrayList<>();
        this.implementedList.addAll(implementedList);
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

    public ArrayList<String> getImportList() {
        return importList;
    }

    public void setImportList(List<String> importList) {
        this.importList = new ArrayList<>();
        this.importList.addAll(importList);
    }

    public ArrayList<String> getAssociationList() {
        associationList = new ArrayList<>();
        for (Triplet triplet: globalDep){
            if (triplet.getSrc().equalsIgnoreCase(getClassName()) && triplet.getType().equals(DependEnum.ASSOCIATION)) {
                associationList.add(triplet.getDes());
            }
        }
        return associationList;
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

    public ArrayList<Object> getDependencyList() {
        return dependencyList;
    }

    public void setDependencyList(List<Object> dependencyList) {
        this.dependencyList = new ArrayList<>();
        this.dependencyList.addAll(dependencyList);
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
        System.out.println(s);
    }
    public void findDependency(){
        // mark fields as Association [Do in visitor]
        findAssoDependency();
        // mark import as IMPORT
        for(String s: this.importList){
            String importString = s.contains(".") ? s.substring(s.lastIndexOf(".") + 1, s.length()) : s;
            Triplet t = new Triplet(this.className,importString, DependEnum.IMPORT);

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

    @Override
    public boolean equals(Object obj){
        try {
            MyClass myClass = (MyClass) obj;
            return getClassName().equalsIgnoreCase(myClass.getClassName());
        }catch(Exception e){
            return false;
        }
    }

    public static String getModifierSymbol(String s) {
        switch (s.toLowerCase()){
            case "private": return "-";
            case "protected": return "#";
            case "public":
            default: return "+";

        }
    }

    public static ArrayList<Triplet> getGlobalDep() {
        return globalDep;
    }

    public boolean hasSrcRelation(String className) {
        return (getImplementedList().contains(className) || getImportList().contains(className)
                || getExtendedList().contains(className) || getAssociationList().contains(className));
    }

    public boolean hasDstRelation(String className) {
        for (Triplet t: globalDep){
            if (t.getDes().equalsIgnoreCase(getClassName()) && t.getDes().equalsIgnoreCase(className)){
                return true;
            }
        }
        return false;
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public int getMaxStringLength(){
        return maxStringLength;
    }
}
