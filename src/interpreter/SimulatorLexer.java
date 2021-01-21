
package interpreter;

import java.util.ArrayList;
import java.util.Scanner;

public class SimulatorLexer<V> implements Lexer
{
	private Scanner scanner;
	private ArrayList<String[]> lines;

	public SimulatorLexer(final String v) {
		this.lines = new ArrayList<String[]>();
		this.scanner = new Scanner(v);
	}

	public SimulatorLexer(final V v) {
		this.lines = new ArrayList<String[]>();
		this.scanner = new Scanner((Readable)v);
	}

	@Override
	public ArrayList<String[]> lexer() {
		while (this.scanner.hasNextLine()) {
			this.lines.add(this.scanner.nextLine().split(" "));
		}
		return this.lines;
	}
}
