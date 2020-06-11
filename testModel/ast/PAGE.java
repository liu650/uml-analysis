package ast;

import libs.Node;
import java.util.ArrayList;
import java.util.List;

public class PAGE extends Node {
    //PAGE::= "NewPage: " PAGETITLE PAGESTUFF*
    PAGETITLE pagetitle;
    List<PAGESTUFF> pstf = new ArrayList<>();

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("NewPage:");
        // Parse PAGETITLE
        pagetitle = new PAGETITLE();
        pagetitle.parse();

        // Parse PAGESTUFF
        while(tokenizer.moreTokens() && !tokenizer.checkToken("NewPage:") && !tokenizer.checkToken("Section:")){
            PAGESTUFF p = PAGESTUFF.make();
            p.parse();
            pstf.add(p);

        }
    }

    @Override
    public void evaluate() {
        pagetitle.evaluate();
        for (PAGESTUFF ps: pstf) {
            ps.evaluate();
        }
        String end  = "\\end{frame}";
        writer.println(end);
    }
}
/*
* sample page:
* \begin{frame}{title(as string)}
   ......
\end{frame}
* */