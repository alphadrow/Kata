package main.java;

import java.util.Scanner;

public class Calculator {
    Converter converter = new Converter();

    public Calculator() {
    }

    protected int calculateWithInts(int a, String operator, int b) {
        int result;
        if (operator.equals("+")) {
            result = a + b;
        } else if (operator.equals("-")) {
            result = a - b;
        } else if (operator.equals("*")) {
            result = a * b;
        } else {
            if (!operator.equals("/")) {
                throw new IllegalArgumentException("Неверная арифметическая операция!");
            }

            result = a / b;
        }

        return result;
    }

    public String calculate(String input) {
        String[] tokens = input.split(" ");
        String operator = tokens[1];
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Неправильный формат выражения!");
        } else {
            int result;
            int a;
            int b;
            if (tokens[0].matches("[IVX]") && tokens[2].matches("[IVX]")) {
                a = this.converter.romanToArabic(tokens[0]);
                b = this.converter.romanToArabic(tokens[2]);
                result = this.calculateWithInts(a, operator, b);
                if (result <= 0) {
                    throw new IllegalArgumentException("Результат не может быть меньше единицы!");
                } else {
                    return this.converter.arabicToRoman(result);
                }
            } else {
                try {
                    a = Integer.parseInt(tokens[0]);
                    b = Integer.parseInt(tokens[2]);
                } catch (NumberFormatException var8) {
                    throw new IllegalArgumentException("Неверный формат чисел!");
                }

                if (a >= 1 && a <= 10 && b >= 1 && b <= 10) {
                    result = this.calculateWithInts(a, operator, b);
                    return String.valueOf(result);
                } else {
                    throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно!");
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        while(true) {
            System.out.print("Введите выражение: ");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                return;
            }

            String result = calculator.calculate(input);
            System.out.println("Результат: " + result);
        }
    }
}
