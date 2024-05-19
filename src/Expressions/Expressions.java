package Expressions;

import java.util.ArrayDeque;
import java.util.Deque;
public class Expressions {
    /**
     * Вычисляет математическое выражение и возвращает результат.
     *
     * @return результат вычисления.
     */
    public static double calculate(String str) {
        String string = converter(str);
        Deque<Double> stack = new ArrayDeque<>();
        String[] tokens = string.split(" ");

        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0)))
                stack.push(Double.parseDouble(token));
            else if (token.equals("~")) {
                double operand = stack.pop();
                stack.push(-operand);
            }
            else if ("+-*/^".contains(token)) {
                double second = stack.pop();
                double first = stack.pop();
                double res = operator(token, first, second);
                stack.push(res);
            }
        }
        return stack.pop();
    }

    /**
     * Преобразует инфиксное математическое выражение в его постфиксную форму
     *
     * @return постфиксную форму выражения
     */
    private static String converter(String exp) {
        StringBuilder res = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        int i = 0;
        while (i < exp.length()) {
            char symbol = exp.charAt(i);
            if (Character.isDigit(symbol)) {
                res.append(symbol);
                while (i + 1 < exp.length() && (Character.isDigit(exp.charAt(i + 1)) || exp.charAt(i + 1) == '.')) {
                    i++;
                    res.append(exp.charAt(i));
                }
                res.append(" ");
            }
            else if (symbol == '(')
                stack.push(symbol);
            else if (symbol == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    res.append(stack.pop()).append(" ");
                stack.pop();
            }
            else if (symbol == '-' && (i == 0 || exp.charAt(i - 1) == '('))
                stack.push('~');
            else if ("+-*/^".contains(String.valueOf(symbol))) {
                while (!stack.isEmpty() && priority(symbol) <= priority(stack.peek()))
                    res.append(stack.pop()).append(" ");
                stack.push(symbol);
            }
            else if (symbol != ' ')
                throw new IllegalArgumentException("Ошибка!");
            i++;
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                throw new IllegalArgumentException("Ошибка!");
            res.append(stack.pop()).append(" ");
        }
        return res.toString();
    }

    /**
     * Применяет двоичный оператор к двум операндам
     *
     * @return результат применения оператора к операндам
     */
    private static double operator(String operator, double a, double b) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };
    }

    /**
     * Возвращает приоритет двоичного оператора.
     *
     * @return значение приоритета
     */
    private static int priority(char i) {
        return switch (i) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }
}