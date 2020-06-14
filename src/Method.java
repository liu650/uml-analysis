public class Method {
    private String modifier;
    private String returnType;
    private String name;
    private String parameters;

    public Method(String modifier, String returnType, String name, String parameters) {
        this.modifier = modifier;
        this.returnType = returnType;
        this.name = name;
        this.parameters = parameters;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString(){
        String n = " ";
        return getModifier() + n + getReturnType() + n + getName()+"("+getParameters().toString()+ ");";
    }
}