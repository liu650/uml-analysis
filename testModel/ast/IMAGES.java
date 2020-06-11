package ast;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class IMAGES extends PAGESTUFF {
    //IMAGES ::= “Image:” (IMAGEREF)*
    List<IMAGEREF> images = new ArrayList<>();

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("Image:");

        while(tokenizer.moreTokens() && !tokenizer.checkToken("NewPage:") && !tokenizer.checkToken("BulletPoint:")&&
                !tokenizer.checkToken("Paragraph:") && !tokenizer.checkToken("Section:")){
            //tokenizer.getNext();
            IMAGEREF image = IMAGEREF.make();
            try {
                image.parse();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e.getMessage());
            }
            images.add(image);
        }
        if (images.size()>4) {
            throw new RuntimeException("Too Many Pictures");
        }
    }

    @Override
    public void evaluate() {
        int len = images.size();
            switch (len) {
                case 0:
                    break;
                case 1:
                    writer.print("\\includegraphics[width=\\textwidth]{");
                    images.get(0).evaluate();
                    writer.print("}\n");
                    break;
                case 2:
                    for (IMAGEREF i : images) {
                        writer.print("\\includegraphics[width=.5\\textwidth]{");
                        i.evaluate();
                        writer.print("}\n");
                    }
                    break;
                case 3:
                    for (IMAGEREF i : images) {
                        writer.print("\\includegraphics[width=.3\\textwidth]{");
                        i.evaluate();
                        writer.print("}\n");
                    }
                    break;
                default:
                    for (int i = 0; i < 2; i++) {
                        writer.print("\\includegraphics[width=.5\\textwidth]{");
                        images.get(i).evaluate();
                        writer.print("}\n");
                    }
                    writer.print("\\newline");
                    for (int i = 2; i < 4; i++) {
                        writer.print("\\includegraphics[width=.5\\textwidth]{");
                        images.get(i).evaluate();
                        writer.print("}\n");
                    }
                    writer.print("\\newline");
                    break;
        }
    }
}

/*
        sample IMAGES:
        \includegraphics[width=.5\textwidth]{p1}
 */