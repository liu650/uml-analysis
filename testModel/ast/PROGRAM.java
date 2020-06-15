package ast;

import libs.Node;
import java.util.ArrayList;
import java.util.List;

public class PROGRAM extends Node {

    private final List<STATEMENT> statements = new ArrayList<>();

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
}
