package com.dprog.strings;

public class StringCompressor {

    public static void main(String[] args) {
        System.out.println(getSuperCompressedString("abbacccvvvaa", 3));
    }

    /**
     * Not working correctly............
     * Below function needs to be corrected to slide the last k size window properly.
     * Currently it does not add the next char in case previous repetition was removed.
     **/
    private static String compressString(String in, int k) {
        //      0 1 2 3 4 5 6 7 8
        // in = s a b b b c c c c
        //  w1: [0  2] , s != a, slide to w2 : i =j, out = s
        //  w2:   [1  3], a != b slide to w3 : i = j out = sa
        //  w3:     [2  4] , b == b, j++, b == b, break; slide to w4 out = sa

        int windowStart = 0;
        StringBuilder sb = new StringBuilder();
        int currentCount = 0;
        char lastRepeatingChar = Character.MIN_VALUE;
        for (int windowEnd = 0; windowEnd < in.length(); windowEnd++) {
            char current = in.charAt(windowStart);
            char next = in.charAt(windowEnd);

            if(current != next && current != lastRepeatingChar) {
                windowStart++;
                sb.append(current);

            } else {
                currentCount++;
                if(currentCount == k ) {
                    lastRepeatingChar = current;
                    System.out.println("last repeating char:: " + lastRepeatingChar);
                    windowStart = windowEnd+1;
                    currentCount = 1;
                    lastRepeatingChar = Character.MIN_VALUE;
                } else {

                }
            }
        }
        return sb.toString();
    }

    /**
     * Below function compresses the given input upto possible k replacements of
     * repeating characters
     * @param input: Input with repeating chars
     * @param k: size of continuous chars to be removed from Input
     * @return non compressable string
     */
    private static String getSuperCompressedString(String input, int k) {
        // 0 1 2 3 4 5 6
        // a b b c c c b --> initial
        //       [k=3]
        // 1: c is k size, remove c
        // 2: recursion of (a b b b)
        // 3: b is k size, remove b
        if(input.length() < k ) {
            return input;
        }
        for(int i =0; i < input.length(); i ++) {
            char repeater = Character.MIN_VALUE;
            int count = 1;
            int repeaterCurrentLocation = i;
            while (i < input.length() -1 && input.charAt(i) == input.charAt(i + 1)) {
                count ++;
                repeater = input.charAt(i);
                i++;
            }
            if(count >= k) {
                input = getSuperCompressedString(removeSting(input, repeater, repeaterCurrentLocation, k ), k);
            }
        }
        return input;
    }

    private static String removeSting(String input, char repeater, int repeaterIndex, int size) {
        int endIndex = repeaterIndex + size;
        String repeated = input.substring(repeaterIndex, endIndex);
        return input.replace(repeated, "");
    }
}
