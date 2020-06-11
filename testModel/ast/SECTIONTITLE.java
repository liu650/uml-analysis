package ast;

public class SECTIONTITLE extends TITLE{
    CONTENT sectiontitle;
    @Override
    public void parse() {
        sectiontitle=new CONTENT();
        sectiontitle.parse();
    }
    @Override
    public void evaluate() {
        writer.print("\\section{");
        sectiontitle.evaluate();
        writer.print("}\n");
    }
}
/*
\section{\bf{S1: test paragraph and settings}}
 */
