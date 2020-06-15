package ast;

public class NUMBER extends EXP {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void parse() {
        setValue(Integer.parseInt(tokenizer.getAndCheckNext("[0-9]+")));
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

    @Override
    public <T> T accept(tinyVarsVisitor<T> v) {
        return v.visit(this);
    }
}
