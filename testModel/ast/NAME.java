package ast;

import ui.Main;

public class NAME extends PROGRAM {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void parse() {
        setName(tokenizer.getAndCheckNext("[a-z]+"));
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public <T> T accept(tinyVarsVisitor<T> v) {
        return v.visit(this);
    }
}
