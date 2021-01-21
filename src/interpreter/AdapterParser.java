package interpreter;

import java.util.ArrayList;

public class AdapterParser
{
    MyParser p;
    public static volatile boolean stop;
    public static volatile boolean close;
    public static Thread thread1;
    public int i;

    public AdapterParser(final MyParser p) {
        this.i = 0;
        this.p = p;
    }

    public void parse() {
        this.p.parse();
        this.i = 0;
    }

    public void execute() {
        (AdapterParser.thread1 = new Thread(() -> {
            while (!AdapterParser.close) {
                while (!AdapterParser.stop && this.i < this.p.comds.size()) {
                    this.p.comds.get(this.i).calculate();
                    ++this.i;
                }
            }
        })).start();
    }

    public void add(final ArrayList<String[]> lines) {
        this.p.lines.clear();
        this.p.lines.addAll(lines);
        MyParser.symTable.put("stop", new CustomVar(1.0));
        for (final String[] s : this.p.lines) {
            if (s[0].equals("while")) {
                final StringBuilder tmp = new StringBuilder(s[s.length - 2]);
                tmp.append("&&stop!=0");
                s[s.length - 2] = tmp.toString();
            }
        }
    }

    public void stop() {
        final CustomVar v = MyParser.symTable.get("stop");
        if (v != null) {
            v.setV(0.0);
        }
        AdapterParser.stop = true;
    }

    public void Continue() {
        MyParser.symTable.get("stop").setV(1.0);
    }

    static {
        AdapterParser.stop = true;
        AdapterParser.close = false;
    }
}
