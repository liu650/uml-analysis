package ast;

import libs.Node;
import libs.Tokenizer;

public class PAGESTUFF extends Node{
    @Override
    public void parse() {

    }

    @Override
    public void evaluate() {

    }
    // PAGESTUFF::== POINT | PARAGRAPH | IMAGES

    public static PAGESTUFF make(){

        Tokenizer tokenizer = Tokenizer.getTokenizer();
        if (tokenizer.checkToken("Paragraph:")) {
            return new PARAGRAPH();

        } else if (tokenizer.checkToken("BulletPoint:")) {
            return new POINT();
        } else if (tokenizer.checkToken("Image:")) {
            return new IMAGES();
        } else {
            throw new RuntimeException("Invalid value: " + tokenizer.getNext());
        }
    }

}



