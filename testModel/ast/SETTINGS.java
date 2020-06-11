package ast;

import libs.Node;

public class SETTINGS extends Node {
    //SETTINGS:: = "Settings: " SIZE? BI? COLOR?
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
