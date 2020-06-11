package ast;
import java.util.ArrayList;
import java.util.List;

import libs.Node;

public class SECTION extends Node {
    //SECTION: "Section:" TITLE (PAGE)*
    SECTIONTITLE sectiontitle;

    List<PAGE> pages = new ArrayList<PAGE>();
    @Override
    public void parse() {
        // Parse SECTIONTITLE
        tokenizer.getAndCheckNext("Section:");
        sectiontitle = new SECTIONTITLE();
        sectiontitle.parse();

        // Parse PAGE
        while(tokenizer.moreTokens() && !tokenizer.checkToken("Section:")){
            PAGE page = new PAGE();
            page.parse();
            pages.add(page);
        }
    }

    @Override
    public void evaluate() {
        sectiontitle.evaluate();
        for (PAGE p: pages){
            p.evaluate();
        }
    }
}
