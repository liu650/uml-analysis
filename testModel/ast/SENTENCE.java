package ast;

import libs.Node;
public class SENTENCE extends Node {
    String s;

    @Override
    public void parse() {
        s = tokenizer.getNext();
    }

    @Override
    public void evaluate() {
        writer.print(s.toString());
    }

    @Override
    public String toString(){
        return s;
    }
}



