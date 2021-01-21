

package commands;

import expressions.ShuntingYard;
import interpreter.MyParser;

public class AssignCommand implements Command
{
    @Override
    public void execute(final String[] array) {
        if (array[2].equals("bind")) {
            if (MyParser.symbolTable.get(array[0]).getV() != MyParser.symbolTable.get(array[3]).getV()) {
                MyParser.symbolTable.get(array[0]).setV(MyParser.symbolTable.get(array[3]).getV());
            }
            MyParser.symbolTable.get(array[3]).addObserver(MyParser.symbolTable.get(array[0]));
            MyParser.symbolTable.get(array[0]).addObserver(MyParser.symbolTable.get(array[3]));
        }
        else {
            final StringBuilder exp = new StringBuilder();
            for (int i = 2; i < array.length; ++i) {
                exp.append(array[i]);
            }
            final double tmp = ShuntingYard.calc(exp.toString());
            if (MyParser.symbolTable.get(array[0]).getLocation() != null) {
                ConnectCommand.out.println("set " + MyParser.symbolTable.get(array[0]).getLocation() + " " + tmp);
                ConnectCommand.out.flush();
                System.out.println("set " + MyParser.symbolTable.get(array[0]).getLocation() + " " + tmp);
            }
            else {
                MyParser.symbolTable.get(array[0]).setV(tmp);
            }
        }
    }
}
