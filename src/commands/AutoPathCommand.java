package commands;

import model.Model;

public class AutoPathCommand implements Command {
    @Override
    public void execute(String[] array) {

        Model.turn=false;
    }
}
