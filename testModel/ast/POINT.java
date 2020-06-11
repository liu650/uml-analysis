package ast;
import java.util.ArrayList;
import java.util.List;

public class POINT extends PAGESTUFF {
    //POINT::= "BulletPoint: " (CONTENT)*
    List<CONTENT> loc = new ArrayList<>();
    @Override
    public void parse() {
        // Parse content

        while(tokenizer.moreTokens() && tokenizer.checkToken("BulletPoint:")){
            tokenizer.getNext();
            CONTENT c = new CONTENT();
            c.parse();
            loc.add(c);
        }
    }

    @Override
    public void evaluate() {
        writer.print("\\begin{itemize}\n");
        for (CONTENT c: loc) {
            writer.print("\\item ");
            c.evaluate();
        }
        writer.println("\\end{itemize}\n");
    }
}

/*
* sample:
* \begin{itemize}
        \item {\bf{bold font item}}
        \item {\Large{Large item}}
        \item {\color{blue} blue item}
        \item {plain item}
    \end{itemize}
* */
