package commands;

import interpreter.MyParser;

public class PrintCommand implements Command {
    @Override
    public void execute(String[] array) {
       for (int i=1;i<array.length;i++)
       {
           if(MyParser.symTable.containsKey(array[i]))
                System.out.print(array[i]+" "+ MyParser.symTable.get(array[i]).getV());
           else
               System.out.print(array[i]);
       }
        System.out.println("");
    }
}
