

package commands;

import expressions.Expression;

public class CommandExpression implements Expression
{
    private Command c;
    private String[] s;

    public CommandExpression(final Command c) {
        this.c = c;
    }

    public Command getCommand() {
        return this.c;
    }

    public void setCommand(final Command c) {
        this.c = c;
    }

    public String[] getTokens() {
        return this.s;
    }

    public void setTokens(final String[] s) {
        this.s = s;
    }

    @Override
    public double calculate() {
        this.c.execute(this.s);
        return 0.0;
    }
}
