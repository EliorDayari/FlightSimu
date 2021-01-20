package interpreter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CompLexer<V> implements Lexer {
    private Scanner scanner;
    private ArrayList<String[]> lines = new ArrayList<>();
    private String[] tokens =null;

    public CompLexer(String v) {
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(v)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public CompLexer(String[] s)
    {
        tokens =s;
    }
    public CompLexer(V v) {
        scanner = new Scanner((Readable) v);

    }
    public ArrayList<String[]> lexer() {
        if(tokens !=null)
        {
            for (String s: tokens) {
            	//regular expression to represent 1 or more white spaces
                lines.add(s.replaceFirst("=", " = ").replaceFirst("\t","").split("\\s+"));
            }

        }
        else
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine().replaceFirst("=", " = ").replaceFirst("\t","").split("\\s+"));
            }
        return lines;

    }
}
