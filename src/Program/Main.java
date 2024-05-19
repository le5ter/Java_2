package Program;

import java.util.Scanner;
import Expressions.Expressions;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите выражение (0 - выход): ");
            String exp = scanner.nextLine();

            if (exp.equals("0"))
                break;


            double result = Expressions.calculate(exp);
            System.out.println("Ответ: " + result);
        }

        scanner.close();
    }
}