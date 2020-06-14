public class Field {
    private String modifier;
    private String type;
    private String name;

    public Field(String a, String b, String c){
        this.modifier = a;
        this.type = b;
        this.name = c;

    }

    public Field() {
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        String n = " ";
        return getModifier() + n + getType() + n + getName();
    }
}