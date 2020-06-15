package ast;

import ui.Main;

public class DEC extends STATEMENT {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("new");
        setName(tokenizer.getNext());
    }

    @Override
    public <T> T accept(tinyVarsVisitor<T> v) {
        return v.visit(this);
    }
}
