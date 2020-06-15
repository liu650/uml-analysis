package ast;

public interface tinyVarsVisitor<T> {

    // Recall: one visit method per concrete AST node subclass
    T visit(PROGRAM p);

    T visit(DEC d);

    T visit(UNDEF u);

    T visit(ALIAS a);

    T visit(SET s);

    T visit(PRINT p);

    T visit(NAME n);

    T visit(NUMBER n);
}
