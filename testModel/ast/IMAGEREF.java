package ast;

import libs.Node;
import libs.Tokenizer;

import java.io.FileNotFoundException;

public class IMAGEREF extends Node{

    String address;

    public static IMAGEREF make(){

        Tokenizer tokenizer = Tokenizer.getTokenizer();
        if (tokenizer.checkToken("Path:")) {
            return new IMAGEPATH();
        } else {
            throw new RuntimeException("Invalid value: " + tokenizer.getNext());
        }
    }
    @Override
    public void parse() throws FileNotFoundException {
        address = tokenizer.getNext();
    }

    @Override
    public void evaluate() {
        writer.print(address);
    }
}





