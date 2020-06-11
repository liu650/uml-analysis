package libs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {

    private String reserved = "`";
    private static String rawInput;
    private static List<String> literals;
    private List<String> tokens = new ArrayList<>();
    private int index = 0;
    private static Tokenizer theTokenizer;

    private Tokenizer(String filename, List<String> literalsList){
        literals = literalsList;
        try {
            rawInput = Files.readString(Paths.get(filename));
        } catch (IOException e) {
            System.out.println("!!!ERROR: Input file not found.");
            System.exit(0);
        }
        tokenize();
    }


     private void tokenize (){
        String newInput = rawInput;

        // Replace all constant literals with  reserved + s + reserved;
        for(String s : literals) {
            newInput = newInput.replace(s, reserved + s + reserved);
        }

        // Remove multiple reserved
        newInput = newInput.replace("```",reserved);
        newInput = newInput.replace("``", reserved);

        // Remove leading RESERVEDWORD
        if(newInput.charAt(0) == reserved.charAt(0) && !newInput.isEmpty()) {
            newInput = newInput.substring(1);
        }

        // Split the rest
        String[] tempArray = newInput.split(reserved);

        // Trim whitespace and remove empty tokens
        for (String s:tempArray){
            if(!s.trim().isEmpty()){
               tokens.add(s.trim());
            }
        }

        // System.out.println("Input file: ");
        // System.out.println(Arrays.asList(tempArray));
    }

























    private String checkNext(){
        String result="NO_MORE_TOKENS";
        if (moreTokens()){
            result = tokens.get(index);
        }
        return result;
    }

    public String getNext(){
        String result = "NULLTOKEN";
        if (moreTokens()){
            result = tokens.get(index);
            index++;
        }
        return result;
    }


    public boolean checkToken(String regexp){
        String s = checkNext();
        // System.out.println("comparing: |"+s+"|  to  |"+regexp+"|");
        return (s.matches(regexp));
    }


    public String getAndCheckNext(String regexp){
        String s = getNext();
        if (!s.matches(regexp)) {
            throw new Error("Syntax Error: Expected something matching: " + regexp + " but got: " + s);
        }
        // System.out.println("matched: " + s + "  to  " + regexp);
        return s;
    }

    public boolean moreTokens(){
        return index <tokens.size();
    }

    public static void makeTokenizer(String filename, List<String> literals){
        if (theTokenizer==null){
            theTokenizer = new Tokenizer(filename,literals);
        }
    }

    public static Tokenizer getTokenizer(){
        return theTokenizer;
    }

}
