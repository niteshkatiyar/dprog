package com.dprog.arrays;


import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MaxAverageSubset {
    //[1, 3, 2, 6, -1, 4, 1, 8, 2], K=5

    private static Double getAverage(int[] a) {
        OptionalDouble average = Arrays.stream(a).average();
        if(average.isPresent()) {
            return average.getAsDouble();
        }
        return null;
    }

    private static int[] getMaxAverageSubsetBruteForce(int arr[], int k) {
        // make subset of k integers
        // brute force method................

        int low = 0;
        int high = arr.length;
        double maxAvg = 0;

        if (k <= 0 || k > arr.length) {
            System.out.println("Cannot create subset of " + k + " length.");
        } else {
            for (int i = 0; i < arr.length; i++) {
                int sub[] = new int[k];
                if (i + k > arr.length) {
                    break;
                } else {
                    for (int j = 0; j < k; j++) {
                        sub[j] = arr[i + j];
                    }
                }
//                System.out.println("sub array " + Arrays.toString(sub) + " has average = " + getAverage(sub));
                if(getAverage(sub) > maxAvg) {
                    low = i;
                    high = i+k;
                }
            }
        }
        //...................................
        return Arrays.stream(arr, low, high).toArray();
    }

    private static int[] getMaxAverageSubsetOptimised(int arr[], int k) {
        // make subset of k integers
        // optimised method................

        int low = 0;
        int high = 0;
        double maxAvg = 0;

        if (k <= 0 || k > arr.length) {
            System.out.println("Cannot create subset of " + k + " length.");
        } else {
            int sar[] = new int[k];

            for (int i =0; i < k; i ++) {
                sar[i] = arr[i];
            }
            maxAvg = Arrays.stream(sar).average().getAsDouble();

            for(int i = k; i < arr.length; i++) {
                if(i+k > arr.length) {
                    break;
                } else {
                    sar[0] = arr[i];

                }
            }

            for (int i = 0; i < arr.length; i++) {
                int sub[] = new int[k];
                if (i + k > arr.length) {
                    break;
                } else {
                    for (int j = 0; j < k; j++) {
                        sub[j] = arr[i + j];
                    }

                }
                System.out.println("sub array " + Arrays.toString(sub) + " has average = " + getAverage(sub));
                if(getAverage(sub) > maxAvg) {
                    low = i;
                    high = i+k;
                }
            }
        }
        //...................................
        return Arrays.stream(arr, low, high).toArray();
    }

    public static void main(String[] args) {
        // brute force max average subset.
        int[] maxAvgArray = {1, 3, 2, 6, -1, 4, 1, 8, 2};
        System.out.println(Arrays.toString(getMaxAverageSubsetBruteForce(maxAvgArray, 4)));
        // space optimised max average subset.


    }
}
