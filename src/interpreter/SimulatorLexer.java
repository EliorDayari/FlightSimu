package interpreter;

import java.util.ArrayList;
import java.util.Scanner;

public class SimulatorLexer<V> implements Lexer{

	private Scanner scanner;
	private ArrayList<String[]> lines = new ArrayList<>();

	public SimulatorLexer(String v) {
		scanner = new Scanner(v);
	}
	public SimulatorLexer(V v) {
		scanner = new Scanner((Readable) v);
		
	}
	public ArrayList<String[]> lexer() {
		while (scanner.hasNextLine()) {
			lines.add(scanner.nextLine().split(" "));
		}
		return lines;

	}

}
