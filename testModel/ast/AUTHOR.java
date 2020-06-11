package ast;

import libs.Node;

public class AUTHOR extends Node {
    // AUTHOR::= "Author: " CONTENT
    CONTENT authorContent;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("Author:");
        // Parse Author
        authorContent = new CONTENT();
        authorContent.parse();
    }

    @Override
    public void evaluate() {
        String start = "\\author{";
        String end = "}\n";
        writer.println(start);
        authorContent.evaluate();
        writer.println(end);
    }
}
/*
\author{Me}
 */
