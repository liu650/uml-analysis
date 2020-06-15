package ast;

import ui.Main;

public class SET extends STATEMENT {

    private String name;

    private EXP exp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EXP getExp() {
        return exp;
    }

    public void setExp(EXP exp) {
        this.exp = exp;
    }

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("set");
        setName(tokenizer.getNext());
        tokenizer.getAndCheckNext(",");
        setExp(EXP.makeExp(tokenizer));
        getExp().parse();
    }

    @Override
    public <T> T accept(tinyVarsVisitor<T> v) {
        return v.visit(this);
    }
}
