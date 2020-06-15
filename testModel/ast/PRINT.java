package ast;

import java.util.ArrayList;
import java.util.List;

public class PRINT extends PROGRAM {
    private final List<PROGRAM> programs = new ArrayList<>();
    private EXP printed;

    public EXP getPrinted() {
        return printed;
    }

    public void setPrinted(EXP printed) {
        this.printed = printed;
    }

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("print");
        setPrinted(EXP.makeExp(tokenizer));
        getPrinted().parse();
    }

    @Override
    public <T> T accept(tinyVarsVisitor<T> v) {
        return v.visit(this);
    }
}
