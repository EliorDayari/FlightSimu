package commands;

import expressions.ShuntingYard;
import interpreter.MyParser;

public class ReturnCommand implements Command {

    @Override
    public void execute(String[] array) {

        StringBuilder exp = new StringBuilder();
        for (int i = 1; i < array.length; i++)
            exp.append(array[i]);
        MyParser.returnValue = ShuntingYard.calc(exp.toString());
    }

}
