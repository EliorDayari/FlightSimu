package model;

import interpreter.MyParser;
import interpreter.MyLexer;
import interpreter.Lexer;
import interpreter.AdapterParser;

public class Interpreter
{
    AdapterParser parser;
    Lexer lexer;

    public Interpreter() {
        final String[] start = { "openDataServer 5400 10", "connect 127.0.0.1 5402", "var breaks = bind /controls/flight/speedbrake", "var throttle = bind /controls/engines/current-engine/throttle", "var heading = bind /instrumentation/heading-indicator/indicated-heading-deg", "var airspeed = bind /instrumentation/airspeed-indicator/indicated-speed-kt", "var roll = bind /instrumentation/attitude-indicator/indicated-roll-deg", "var pitch = bind /instrumentation/attitude-indicator/internal-pitch-deg", "var rudder = bind /controls/flight/rudder", "var aileron = bind /controls/flight/aileron", "var elevator = bind /controls/flight/elevator", "var alt = bind /instrumentation/altimeter/indicated-altitude-ft", "var rpm = bind /engines/engine/rpm", "var hroute = 0", "var goal = 0", "var altr = 2000", "var e=0", "var r=0" };
        this.lexer = new MyLexer(start);
        (this.parser = new AdapterParser(new MyParser(this.lexer.lexer()))).parse();
        AdapterParser.stop = false;
        this.parser.execute();
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        AdapterParser.stop = true;
    }

    public void interpet(final String[] list) {
        this.lexer = new MyLexer(list);
        this.parser.add(this.lexer.lexer());
        this.parser.parse();
    }

    public void execute() {
        if (this.parser.i != 0) {
            final AdapterParser parser = this.parser;
            --parser.i;
        }
        this.parser.Continue();
        AdapterParser.stop = false;
    }

    public void stop() {
        this.parser.stop();
    }
}
