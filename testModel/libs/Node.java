package libs;

import ast.tinyVarsVisitor;

import java.io.PrintWriter;

public abstract class Node {
    protected Tokenizer tokenizer = Tokenizer.getTokenizer();

    abstract public void parse();

    abstract public <T> T accept(tinyVarsVisitor<T> v); // so that we remember to define this in all subclasses
}
