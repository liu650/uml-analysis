package ast;

import libs.Node;

public class SETTINGS extends Node {

    SIZE size;

    BI bi;

    COLOR color;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("@");
    }

    @Override
    public void evaluate() {
    }
}
