package ast;

import libs.Node;

public class TITLE extends Node{
    // TITLE::="Title= " CONTENT “;”
    CONTENT titleContent = null;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("Title:");
        titleContent = new CONTENT();
        titleContent.parse();
    }

    @Override
    public void evaluate() {
        writer.print("\\title{");
        titleContent.evaluate();
        writer.print("}\n");
    }
}
/*
\title{Test}
* */