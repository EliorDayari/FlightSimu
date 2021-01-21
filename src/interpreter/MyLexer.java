
package interpreter;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MyLexer<V> implements Lexer
{
    private Scanner scanner;
    private ArrayList<String[]> lines;
    private String[] tokens;

    public MyLexer(final String v) {
        this.lines = new ArrayList<String[]>();
        this.tokens = null;
        try {
            this.scanner = new Scanner(new BufferedReader(new FileReader(v)));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyLexer(final String[] s) {
        this.lines = new ArrayList<String[]>();
        this.tokens = null;
        this.tokens = s;
    }

    public MyLexer(final V v) {
        this.lines = new ArrayList<String[]>();
        this.tokens = null;
        this.scanner = new Scanner((Readable)v);
    }

    @Override
    public ArrayList<String[]> lexer() {
        if (this.tokens != null) {
            for (final String s : this.tokens) {
                this.lines.add(s.replaceFirst("=", " = ").replaceFirst("\t", "").split("\\s+"));
            }
        }
        else {
            while (this.scanner.hasNextLine()) {
                this.lines.add(this.scanner.nextLine().replaceFirst("=", " = ").replaceFirst("\t", "").split("\\s+"));
            }
        }
        return this.lines;
    }
}
