package main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator {

    Map<Character, Integer> romanToArabicMap = new HashMap();
    String[] romanNumerals;
    int[] values;

    {
        this.romanToArabicMap.put('I', 1);
        this.romanToArabicMap.put('V', 5);
        this.romanToArabicMap.put('X', 10);
        this.romanToArabicMap.put('L', 50);
        this.romanToArabicMap.put('C', 100);
        this.romanNumerals = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        this.values = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
    }

    public String arabicToRoman(int number) {
        if (number >= 1 && number <= 399) {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < this.romanNumerals.length; ++i) {
                while (number >= this.values[i]) {
                    result.append(this.romanNumerals[i]);
                    number -= this.values[i];
                }
            }

            return result.toString();
        } else {
            throw new IllegalArgumentException("Число должно быть от 1 до 399 включительно!");
        }
    }

    public int romanToArabic(String romanNumeral) {
        int arabicNumeral = 0;
        int previousValue = 0;

        for (int i = romanNumeral.length() - 1; i >= 0; --i) {
            int currentValue = this.romanToArabicMap.get(romanNumeral.charAt(i));
            if (currentValue < previousValue) {
                arabicNumeral -= currentValue;
            } else {
                arabicNumeral += currentValue;
            }

            previousValue = currentValue;
        }

        return arabicNumeral;
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

    static boolean isRomanNumerals(String num) {
        return num.equals("I") || num.equals("II") || num.equals("III") || num.equals("IV") || num.equals("V")
                || num.equals("VI") || num.equals("VII") || num.equals("VIII") || num.equals("IX") || num.equals("X");
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
            if (isRomanNumerals(tokens[0]) && isRomanNumerals(tokens[2])) {
                a = romanToArabic(tokens[0]);
                b = romanToArabic(tokens[2]);
                result = this.calculateWithInts(a, operator, b);
                if (result <= 0) {
                    throw new IllegalArgumentException("Результат не может быть меньше единицы!");
                } else {
                    return arabicToRoman(result);
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

        while (true) {
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


