package ast;

import java.io.File;
import java.io.FileNotFoundException;

public class IMAGEPATH extends IMAGEREF{
    //IMAGEPATH::= "Image PATH: " STRING
    String address;

    @Override
    public void parse() throws FileNotFoundException{
        tokenizer.getAndCheckNext("Path:");
        address = tokenizer.getNext();
        File f = new File(address);
        if (!f.exists()){
            throw new FileNotFoundException("The image path " + address + " is invalid." );
        }
    }

    @Override
    public void evaluate() {
        writer.print(address);
    }
}



