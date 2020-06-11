package ast;

import libs.Node;

import java.util.HashSet;
import java.util.Set;

public class COLOR extends Node {
    // COLOR:: = "Color: " ("red" or "green" or ...)
    String color;
    Set<String> colorSet = new HashSet<>();
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("Color:");
        // Parse Color
        color = tokenizer.getNext().toLowerCase();
        // System.out.println(color + " wait " );
        // System.out.println(color == "blue");
        colorSet.add("black");
        colorSet.add("darkgray");
        colorSet.add("brown");
        colorSet.add("cyan");
        colorSet.add("gray");
        colorSet.add("lime");
        colorSet.add("magenta");
        colorSet.add("pink");
        colorSet.add("purple");
        colorSet.add("red");
        colorSet.add("teal");
        colorSet.add("violet");
        colorSet.add("white");
        colorSet.add("yellow");
        colorSet.add("green");
        colorSet.add("lightgray");
        colorSet.add("olive");
        colorSet.add("orange");
        colorSet.add("blue"); // 19 basic colors of xcolor package
        if (!colorSet.contains(color)) {
            throw new RuntimeException("Color " + color + " not supported.");
        }
    }

    @Override
    public void evaluate() {
        writer.print(toString());
    }

    @Override
    public String toString(){
        return color;
    }
}
