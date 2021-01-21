

package commands;

import expressions.ShuntingYard;
import interpreter.MyParser;

public class AssignCommand implements Command
{
    @Override
    public void execute(final String[] array) {
        if (array[2].equals("bind")) {
            if (MyParser.symTable.get(array[0]).getV() != MyParser.symTable.get(array[3]).getV()) {
                MyParser.symTable.get(array[0]).setV(MyParser.symTable.get(array[3]).getV());
            }
            MyParser.symTable.get(array[3]).addObserver(MyParser.symTable.get(array[0]));
            MyParser.symTable.get(array[0]).addObserver(MyParser.symTable.get(array[3]));
        }
        else {
            final StringBuilder sb = new StringBuilder();
            for (int i = 2; i < array.length; ++i) {
                sb.append(array[i]);
            }
            final double tmp = ShuntingYard.calc(sb.toString());
            if (MyParser.symTable.get(array[0]).getLocation() != null) {
                ConnectCommand.out.println("set " + MyParser.symTable.get(array[0]).getLocation() + " " + tmp);
                ConnectCommand.out.flush();
                System.out.println("set " + MyParser.symTable.get(array[0]).getLocation() + " " + tmp);
            }
            else {
                MyParser.symTable.get(array[0]).setV(tmp);
            }
        }
    }
}
