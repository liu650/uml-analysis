package ast;

import java.util.ArrayList;
import java.util.List;

public class UNDEF extends STATEMENT {
    private final List<PROGRAM> programs = new ArrayList<>();
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("undef");
        setName(tokenizer.getNext());
    }

    @Override
    public <T> T accept(tinyVarsVisitor<T> v) {
        return v.visit(this);
    }
}
