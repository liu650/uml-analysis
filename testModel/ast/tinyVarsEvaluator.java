package ast;

import ui.Main;
import java.util.HashMap;
import java.util.Map;

public class tinyVarsEvaluator implements tinyVarsVisitor<Integer> {

    private final Map<String, Integer> environment = new HashMap<>();

    private final Map<Integer, Integer> memory = new HashMap<>();

    public Map<String, Integer> getEnvironment() {
        return environment;
    }

    public Map<Integer, Integer> getMemory() {
        return memory;
    }

    private Integer getFreshLocation() {
        // or use an int; it will get boxed when needed
        Integer i = Integer.valueOf(1);
        while (environment.containsValue(i)) {
            // Q. what difference might it make if we used memory.containsKey(i) instead?
            // unboxing and boxing is automatic
            i = i + 1;
        }
        return i;
    }

    @Override
    public Integer visit(PROGRAM p) {
        for (STATEMENT s : p.getStatements()) {
            s.accept(this);
        }
        // we only return a value for expressions (EXP); evaluation of programs/statements is via side-effects
        return null;
    }

    @Override
    public Integer visit(DEC d) {
        System.out.println("Putting " + d.getName() + " into symbol table");
        // no value yet
        environment.put(d.getName(), getFreshLocation());
        // we only return a value for expressions (EXP); evaluation of statements is via side-effects
        return null;
    }

    @Override
    public Integer visit(UNDEF u) {
        System.out.println("Removing " + u.getName() + " into symbol table");
        environment.remove(u.getName());
        // we only return a value for expressions (EXP); evaluation of statements is via side-effects
        return null;
    }

    @Override
    public Integer visit(SET s) {
        System.out.println("Evaluating " + s.getExp());
        Integer result = s.getExp().accept(this);
        System.out.println("Setting " + s.getName() + " to " + result);
        memory.put(environment.get(s.getName()), result);
        // we only return a value for expressions (EXP); evaluation of statements is via side-effects
        return null;
    }

    @Override
    public Integer visit(ALIAS a) {
        System.out.println("Creating alias " + a.getNewName() + " for " + a.getOldName());
        // same location
        environment.put(a.getNewName(), environment.get(a.getOldName()));
        // we only return a value for expressions (EXP); evaluation of statements is via side-effects
        return null;
    }

    @Override
    public Integer visit(PRINT p) {
        System.out.println("PRINTING: " + p.getPrinted().accept(this));
        // we only return a value for expressions (EXP); evaluation of statements is via side-effects
        return null;
    }

    @Override
    public Integer visit(NAME n) {
        // lookup: variable -> location -> value
        return memory.get(environment.get(n.getName()));
    }

    @Override
    public Integer visit(NUMBER n) {
        // Java auto-boxes this int as an Integer object
        return n.getValue();
    }
}
