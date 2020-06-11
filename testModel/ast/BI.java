package ast;

import libs.Node;

public class BI extends Node {
    // BI:: = "bi:" ("Bold" or "Italic")
    //String bi;
    boolean bold;
    boolean italic;

    BI(){
        this.bold = false;
        this.italic = false;
    }
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("Bi:");
        // Parse BI
        String s = tokenizer.getNext().toLowerCase();

        switch (s ) {
            case "bold":
                this.bold = true;
                break;
            case "italic":
                this.italic = true; // TODO case sensitive? Yes
                break;
            default:
                throw new RuntimeException("Unexpected token!");
        }
    }

    @Override
    public void evaluate() {
        // do nothing
    }
}
