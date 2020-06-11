package ast;

import libs.Node;

import java.util.ArrayList;
import java.util.List;


public class PROGRAM extends Node {
    //PROGRAM::= TITLE AUTHOR? (SECTION)*
    TITLE title= null;;
    AUTHOR author= null;;
    List<SECTION> sections = new ArrayList<>();

    // Fields for evaluation
    String start = "\\documentclass[11pt]{beamer}\n" +
            "\\usepackage{hyperref}\n" +
            "\\usepackage{booktabs}\n" +
            "\\usepackage{xspace}\n" +
            "\\usepackage{color,xcolor}\n" +
            "\n" +
            "\\begin{document}";
    String end = "\\end{document}";

    public void parse(){
        // Parse TITLE
        if (tokenizer.checkToken("Title:")) {

            title = new TITLE();
            title.parse();
        }
        System.out.println("title parsed");

        // Parse AUTHOR
        if (tokenizer.checkToken("Author:")) {


            author = new AUTHOR();
            author.parse();
        }
        System.out.println("author parsed");

        // Parse SECTION
        while(tokenizer.moreTokens() && tokenizer.checkToken("Section:")){
            SECTION s = new SECTION();
            s.parse();
            sections.add(s);
        }
    }

    @Override
    public void evaluate() {
        writer.println(start);

        // Evaluate Title
        if (title != null)
            title.evaluate();

        // Evaluate author
        if (author != null)
            author.evaluate();

        // print \maketitle
        String make = "\\maketitle\n";
        writer.println(make);

        // table of contents
        writer.print("\\begin{frame}{Table of contents}\n" +
                "  \\setbeamertemplate{section in toc}[sections numbered]\n" +
                "  \\tableofcontents%[hideallsubsections]\n" +
                "\\end{frame}\n");

        // Evaluate sections
       for (SECTION s : sections){
            s.evaluate();
        }

        writer.println(end);
    }
}
