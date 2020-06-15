package ui;

import ast.PROGRAM;
import ast.EXP;
import ast.tinyVarsEvaluator;
import libs.Tokenizer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<String> literals = Arrays.asList("set", "print", "new", ",", "undef", "alias");
        Tokenizer.makeTokenizer("input.tvar",literals);
        PROGRAM p = new PROGRAM();
        p.parse();
        tinyVarsEvaluator e = new tinyVarsEvaluator();
        p.accept(e);
        System.out.println("completed successfully");
        System.out.println("Final Environment: " + e.getEnvironment());
        System.out.println("Final Memory: " + e.getMemory());
        // you might prefer to debug print-out the value of each declared variable directly (combining information from both maps)
    }

}
