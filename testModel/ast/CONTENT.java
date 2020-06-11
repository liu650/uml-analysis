package ast;
import libs.Node;

public class CONTENT extends Node {
    SIZE size = null;
    COLOR color = null;
    BI bi = new BI();
    String sentence = ""; // from list of sentence to single sentence
    @Override
    public void parse() {
        if (tokenizer.checkToken("@\\(")) {
            tokenizer.getNext();
            while (tokenizer.moreTokens() && !tokenizer.checkToken("\\)@")) {
                if (tokenizer.checkToken("Size:")) {
                    size = new SIZE();
                    size.parse();
                }
                if (tokenizer.checkToken("Bi:")) {

                    bi.parse();
                }
                if (tokenizer.checkToken("Color:")) {
                    color = new COLOR();
                    color.parse();
                }
            }
            tokenizer.getAndCheckNext("\\)@");
        }
        sentence = tokenizer.getNext();
        if (sentence == "NULLTOKEN") {
            throw new RuntimeException("Missing document title");
        }
        // System.out.println("SENTENCE IS __:    " + sentence);
    }

    @Override
    public void evaluate() {
            System.out.println("process SENTENCE: " + sentence.toString());

            if (!bi.italic && !bi.bold && size == null && color == null) {
                //0000
                writer.print(sentence);
            }
            if (!bi.italic && !bi.bold && size == null && color != null) {
                // 0001
                writer.print("{\\color{" + color.color + "}" + sentence + "}");
            }
            if (!bi.italic && !bi.bold && size != null && color == null) {
                //0010

                writer.print("{\\" + size.size + " " + sentence + "}");
            }
            if (!bi.italic && !bi.bold && size != null && color != null) {
                // 0011
                writer.print("{\\" + size.size + "{\\color{" + color.color + "}" + sentence + "}}" );

            }
            if (!bi.italic && bi.bold && size == null && color == null) {
                //0100
                writer.print("{\\bf "  + sentence + "}");
            }
            if (!bi.italic && bi.bold && size == null && color != null) {
                //0101
                // TODO {\it {\bf{\Large {\color{blue} Large bold italic blue } } }}\newline
                writer.print("{\\bf{\\color{" +color.color +"}" + sentence + "}}" );
            }
            if (!bi.italic && bi.bold && size != null && color == null) {
                //0110
                writer.print("{\\bf{\\" + size.size + " " + sentence + "}}");
            }
            if (!bi.italic && bi.bold && size != null && color != null) {
                // 0111
                writer.print("{\\bf{\\" + size.size + "{\\color{" + color.color + "}" + sentence + "}}}");
            }
            if (bi.italic && !bi.bold && size == null && color == null) {
                // 1000
                writer.print("{\\it " + sentence + "}");
            }
            if (bi.italic && !bi.bold && size == null && color != null) {
                // 1001
                writer.print("{\\it{\\color{" + color.color + "}" +sentence + "}}" );
            }
            if (bi.italic && !bi.bold && size != null && color == null) {
                // 1010
                writer.print("{\\it{\\" + size.size + " " + sentence + "}}");
            }
            if (bi.italic && !bi.bold && size != null && color != null) {
                // 1011
                writer.print("{\\it{\\" + size.size + "{\\color{" + color.color + "}" + sentence + "}}}");
            }
            if (bi.italic && bi.bold && size == null && color == null) {
                // 1100
                writer.print("{\\it{\\bf " + sentence + "}}");
            }
            if (bi.italic && bi.bold && size == null && color != null) {
                // 1101
                writer.print("{\\it{\\bf{\\color{" + color.color + "}" + sentence + "}}}");
            }
            if (bi.italic && bi.bold && size != null && color == null) {
                // 1110

                writer.print("{\\it{\\bf{\\" + size.size + " " + sentence + "}}}");
            }
            if (bi.italic && bi.bold && size != null && color != null) {
                // 1111
                writer.print("{\\it{\\bf{\\" + size.size + "{\\color{" + color.color +"}" + sentence + "}}}}");
            }
    }
}
