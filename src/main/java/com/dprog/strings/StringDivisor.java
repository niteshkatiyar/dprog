package com.dprog.strings;

/*
 * Given 2 strings S and T, create a function that operates per the rules
 * Find if a S is divisible by T.
 * it is divisible when, T can be repeated n times and it becomes equals to S.
 */
public class StringDivisor {

    /*  b c d b c d b c d b c d
     *  0 1 2 3 4 5 6 7 8 9 10 11
     * ---------------------------
     *  b c d b c d
     *  0 1 2 3 4 5
     *  we need to consider that the smallest String create both S and T.
     */

    private static int getSmallestDivisor(String input, String divisor) {
        int inputLength = input.trim().length();
        int divisorLength = divisor.trim().length();
        int multiplier = 1;
        if(inputLength == 0 || divisorLength == 0 || divisorLength > inputLength) {
            return -1;
        }

        if(input.equals(divisor)) {
            return 0;
        }
        // if both length mod returns 0, we can simply slide divisor to check if it divides the input string
        if(inputLength % divisorLength == 0) {
            multiplier = inputLength/divisorLength;
            String repeater = divisor;

            for(int i =0; i < multiplier - 1; i++) {
                divisor += repeater;
            }
            if(divisor.equals(input)) {
                return 0;
            } else {
                return -1;
            }
        } else { // return -1, because
            return -1;
        }
    }

    private String getSmallestRepetition(String input, String divisor) {
        String repeater = "";
        int windowStart = 0;
        Character startChar = divisor.charAt(windowStart);
        for(int windowEnd = 0; windowEnd < divisor.length(); windowEnd ++) {

        }

        return repeater;
    }

    public static void main(String[] args) {
        String input = "aaaaaaaaaa";  //"bcdbcdbcdbcd";
        String divisor = "aa";

        System.out.println(getSmallestDivisor(input, divisor));
    }
}
