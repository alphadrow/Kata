package main.java;

import java.util.HashMap;
import java.util.Map;

public class Converter {
    Map<Character, Integer> romanToArabicMap = new HashMap();
    String[] romanNumerals;
    int[] values;

    public Converter() {
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

            for(int i = 0; i < this.romanNumerals.length; ++i) {
                while(number >= this.values[i]) {
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

        for(int i = romanNumeral.length() - 1; i >= 0; --i) {
            int currentValue = (Integer)this.romanToArabicMap.get(romanNumeral.charAt(i));
            if (currentValue < previousValue) {
                arabicNumeral -= currentValue;
            } else {
                arabicNumeral += currentValue;
            }

            previousValue = currentValue;
        }

        return arabicNumeral;
    }
}