

package commands;

import expressions.Expression;

public class CommandExpression implements Expression
{
    private Command command;
    private String[] tokens;

    public CommandExpression(final Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return this.command;
    }

    public void setCommand(final Command command) {
        this.command = command;
    }

    public String[] getTokens() {
        return this.tokens;
    }

    public void setTokens(final String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public double calculate() {
        this.command.execute(this.tokens);
        return 0.0;
    }
}
