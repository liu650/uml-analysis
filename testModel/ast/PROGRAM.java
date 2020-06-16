package ast;

import libs.Node;
import java.util.ArrayList;
import java.util.List;

public class PROGRAM extends SET implements tinyVarsVisitor {

    private final List<STATEMENT> statements = new ArrayList<>();
    private final List<tinyVarsEvaluator> tinyVarsEvaluators = new ArrayList<>();
    public List<STATEMENT> getStatements() {
        return statements;
    }

    @Override
    public void parse() {
        while (tokenizer.moreTokens()) {
            STATEMENT s = null;
            if (tokenizer.checkToken("set")) {
                s = new SET();
            } else if (tokenizer.checkToken("new")) {
                s = new DEC();
            } else if (tokenizer.checkToken("print")) {
                s = new PRINT();
            } else if (tokenizer.checkToken("undef")) {
                s = new UNDEF();
            } else if (tokenizer.checkToken("alias")) {
                s = new ALIAS();
            } else {
                throw new RuntimeException("Unknown statement:" + tokenizer.getNext());
            }
            s.parse();
            getStatements().add(s);
        }
    }

    @Override
    public <T> T accept(tinyVarsVisitor<T> v) {
        return v.visit(this);
    }

    @Override
    public Object visit(PROGRAM p) {
        return null;
    }

    @Override
    public Object visit(DEC d) {
        return null;
    }

    @Override
    public Object visit(UNDEF u) {
        return null;
    }

    @Override
    public Object visit(ALIAS a) {
        return null;
    }

    @Override
    public Object visit(SET s) {
        return null;
    }

    @Override
    public Object visit(PRINT p) {
        return null;
    }

    @Override
    public Object visit(NAME n) {
        return null;
    }

    @Override
    public Object visit(NUMBER n) {
        return null;
    }
}
