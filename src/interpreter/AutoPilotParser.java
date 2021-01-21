package interpreter;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import commands.CommandExpression;

public class AutoPilotParser
{
    CompParser p;
    public static volatile boolean stop;
    public static volatile boolean close;
    public static Thread thread1;
    public int i;

    public AutoPilotParser(final CompParser p) {
        this.i = 0;
        this.p = p;
    }

    public void parse() {
        this.p.parse();
        this.i = 0;
    }

    public void execute() {
        (AutoPilotParser.thread1 = new Thread(() -> {
            while (!AutoPilotParser.close) {
                while (!AutoPilotParser.stop && this.i < this.p.comds.size()) {
                    this.p.comds.get(this.i).calculate();
                    ++this.i;
                }
            }
        })).start();
    }

    public void add(final ArrayList<String[]> lines) {
        this.p.lines.clear();
        this.p.lines.addAll(lines);
        CompParser.symbolTable.put("stop", new Var(1.0));
        for (final String[] s : this.p.lines) {
            if (s[0].equals("while")) {
                final StringBuilder tmp = new StringBuilder(s[s.length - 2]);
                tmp.append("&&stop!=0");
                s[s.length - 2] = tmp.toString();
            }
        }
    }

    public void stop() {
        final Var v = CompParser.symbolTable.get("stop");
        if (v != null) {
            v.setV(0.0);
        }
        AutoPilotParser.stop = true;
    }

    public void Continue() {
        CompParser.symbolTable.get("stop").setV(1.0);
    }

    static {
        AutoPilotParser.stop = true;
        AutoPilotParser.close = false;
    }
}
