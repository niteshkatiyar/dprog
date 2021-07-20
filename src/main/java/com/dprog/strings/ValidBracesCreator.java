package com.dprog.strings;

public class ValidBracesCreator {
    /*
     *  For a given input size, print all valid parenthesis combinations
     *  if n =1, {}
     *  if n =2, {}{}, {{}}.. and so forth
     */

    public static void main(String[] args) {
        int size = 3;
        System.out.println(getParaCombos(size));
    }

    private static String getParaCombos(int size) {
        String baseCombo = "{}";
        String secondCombo = "{}{}, {{}}";
        String combo = "";

        return combo;
    }
}
