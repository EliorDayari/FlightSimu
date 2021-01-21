package commands;

import expressions.ShuntingYard;
import interpreter.MyParser;
import interpreter.CustomVar;

public class DeclareVarCommand implements Command {

	@Override
	public void execute(String[] array) {
		CustomVar v=new CustomVar();
		if(array.length>2) {
			if (array[3].equals("bind")) {
				MyParser.symTable.put(array[1], MyParser.symTable.get(array[4]));
			}
			else {
				StringBuilder exp = new StringBuilder();
				for (int i = 3; i < array.length; i++)
					exp.append(array[i]);
				v.setV(ShuntingYard.calc(exp.toString()));
				MyParser.symTable.put(array[1],v);
			}
		}
		else
			MyParser.symTable.put(array[1],new CustomVar());

	}

}
