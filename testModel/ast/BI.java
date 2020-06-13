package ast;

import libs.Node;

public class BI extends Node {

    // BI:: = "bi:" ("Bold" or "Italic")
    boolean bold;

    boolean italic;

    public static boolean hello;
    private boolean bad;
    public final static boolean good = false;
    protected boolean sad;
    BI() {

        this.bold = false;
        this.italic = false;
    }

    @Override

    public void parse() {
        tokenizer.getAndCheckNext("Bi:");
        // Parse BI
        String s = tokenizer.getNext().toLowerCase();
        switch(s) {
            case "bold":
                this.bold = true;
                break;
            case "italic":
                // TODO case sensitive? Yes
                this.italic = true;
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
