import java.util.List;

public class Method {
    private String modifier;
    private String returnType;
    private String name;
    private List<String> parameters;

    public Method(String modifier, String returnType, String name, List<String> parameters) {
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

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
}