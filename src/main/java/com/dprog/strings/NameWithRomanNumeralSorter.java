package com.dprog.strings;

import sun.security.util.ArrayUtil;

import java.util.*;
import java.util.stream.Collectors;

public class NameWithRomanNumeralSorter {
    /**
     * Classic Ancestor problem, where name also contains a Roman numeral.
     * sort a given list based on
     * @param args
     */

    public static void main(String[] args) {

        List<String> romanNames =
                Arrays.asList("Steven XL", "Steven XVI", "David IX", "Mary XV", "MARY XIII", "Mary XX", "");
        System.out.println("Before Sorting : " + romanNames);
        List<Roman> romanNamesList = romanNames.stream()
                .filter(Objects::nonNull)
                .map(name -> name.split(" "))
                .map(arr -> new RomanBuilder()
                        .withName(arr[0])
                        .withRomanNumeral(arr[1])
                        .withDecimalEquivalent(getIntFromRoman(arr[1].toUpperCase())).build())
                .collect(Collectors.toList());
        romanNamesList.sort(Comparator.comparing(Roman::getName).thenComparingInt(Roman::getDecimalEquivalent));
        System.out.println("After Sorting : "+ romanNamesList );
    }

    private static Integer getIntFromRoman(String input) {
        int sum = 0;
        int n = input.length();
        for(int i=0; i < n; i++) {
            if(i != n-1 && getRomanValue(input.charAt(i)) < getRomanValue(input.charAt(i + 1))) {
                sum += getRomanValue(input.charAt(i + 1)) - getRomanValue(input.charAt(i));
                i ++;
            } else {
                sum += getRomanValue(input.charAt(i));
            }
        }
        return sum;
    }

    private static int getRomanValue(char r) {
        switch (r) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return -1;
        }
    }

}

class Roman {
    private String name;
    private String romanNumeral;
    private Integer decimalEquivalent;

    public Roman(String name, String romanNumeral, Integer decimalEquivalent) {
        this.name = name;
        this.romanNumeral = romanNumeral;
        this.decimalEquivalent = decimalEquivalent;
    }

    public Integer getDecimalEquivalent() { return decimalEquivalent; }
    public String getName() { return name; }
    public String getRomanNumeral() { return romanNumeral; }

    @Override
    public String toString() {
        return name + " " + romanNumeral;
    }
}

class RomanBuilder {
    private String name;
    private String romanNumeral;
    private Integer decimalEquivalent;

    public RomanBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RomanBuilder withRomanNumeral(String romanNumeral) {
        this.romanNumeral = romanNumeral;
        return this;
    }

    public RomanBuilder withDecimalEquivalent(Integer decimalEquivalent) {
        this.decimalEquivalent = decimalEquivalent;
        return this;
    }

    public Roman build() {
        return new Roman(name, romanNumeral, decimalEquivalent);
    }
}
