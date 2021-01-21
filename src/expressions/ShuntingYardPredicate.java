
package expressions;

import interpreter.MyParser;

import java.util.Stack;
import java.util.LinkedList;

public class ShuntingYardPredicate
{
    public static double calculate(final String expression) {
        if (!validations(expression)) {
            System.out.println("throw exception");
        }
        final LinkedList<String> queue = new LinkedList<String>();
        final Stack<String> stack = new Stack<String>();
        final int len = expression.length();
        String token = "";
        String tmp = null;
        for (int i = 0; i < len; ++i) {
            if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                for (token = expression.charAt(i) + ""; (i + 1 < len && expression.charAt(i + 1) >= '0' && expression.charAt(i + 1) <= '9') || (i + 1 < len && expression.charAt(i + 1) == '.'); token += expression.charAt(++i)) {}
            }
            else if ((expression.charAt(i) >= '<' && expression.charAt(i) <= '>') || expression.charAt(i) == '!') {
                if (expression.charAt(i + 1) == '=') {
                    token = expression.charAt(i) + "";
                    token += expression.charAt(++i);
                }
                else {
                    token = expression.charAt(i) + "";
                }
            }
            else if ((expression.charAt(i) >= 'A' && expression.charAt(i) <= 'Z') || (expression.charAt(i) >= 'a' && expression.charAt(i) <= 'z')) {
                for (token = expression.charAt(i) + ""; i < expression.length() - 1 && ((expression.charAt(i + 1) >= 'A' && expression.charAt(i + 1) <= 'Z') || (expression.charAt(i + 1) >= 'a' && expression.charAt(i + 1) <= 'z')); token += expression.charAt(++i)) {}
                token = MyParser.symbolTable.get(token).getV() + "";
            }
            else {
                token = expression.charAt(i) + "";
            }
            final String s = token;
            switch (s) {
                case "+":
                case "-": {
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        queue.addFirst(stack.pop());
                    }
                    stack.push(token);
                    break;
                }
                case "||":
                case "&&":
                case "*":
                case "/": {
                    while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/"))) {
                        queue.addFirst(stack.pop());
                    }
                    stack.push(token);
                    break;
                }
                case "<":
                case "<=":
                case ">":
                case ">=":
                case "!=":
                case "==": {
                    tmp = token;
                    break;
                }
                case "(": {
                    stack.push(token);
                    break;
                }
                case ")": {
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        queue.addFirst(stack.pop());
                    }
                    stack.pop();
                    break;
                }
                default: {
                    queue.addFirst(token);
                    break;
                }
            }
        }
        while (!stack.isEmpty()) {
            queue.addFirst(stack.pop());
        }
        queue.addFirst(tmp);
        final Expression finalExpression = buildExpression(queue);
        final double answer = finalExpression.calculate();
        return Double.parseDouble(String.format("%.3f", answer));
    }

    private static boolean validations(final String expression) {
        return true;
    }

    private static Expression buildExpression(final LinkedList<String> queue) {
        Expression returnedExpression = null;
        Expression right = null;
        Expression left = null;
        final String currentExpression = queue.removeFirst();
        if (currentExpression.equals("+") || currentExpression.equals("-") || currentExpression.equals("*") || currentExpression.equals("/") || currentExpression.equals("<") || currentExpression.equals(">") || currentExpression.equals("<=") || currentExpression.equals(">=") || currentExpression.equals("==") || currentExpression.equals("!=")) {
            right = buildExpression(queue);
            left = buildExpression(queue);
        }
        final String s = currentExpression;
        switch (s) {
            case "+": {
                returnedExpression = new Plus(left, right);
                break;
            }
            case "-": {
                returnedExpression = new Minus(left, right);
                break;
            }
            case "*": {
                returnedExpression = new Mul(left, right);
                break;
            }
            case "/": {
                returnedExpression = new Div(left, right);
                break;
            }
            case "<=":
            case ">":
            case ">=":
            case "==":
            case "!=":
            case "<": {
                returnedExpression = new PredicateExp(left, right, currentExpression);
                break;
            }
            default: {
                returnedExpression = new Number(Double.parseDouble(String.format("%.2f", Double.parseDouble(currentExpression))));
                break;
            }
        }
        return returnedExpression;
    }
}
