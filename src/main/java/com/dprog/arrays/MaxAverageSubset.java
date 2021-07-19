package com.dprog.arrays;


import java.util.Arrays;
import java.util.OptionalDouble;

public class MaxAverageSubset {
    //[1, 3, 2, 6, -1, 4, 1, 8, 2], K=4

    public static void main(String[] args) {
        // brute force max average subset.
        int[] maxAvgArray = {1, 3, 2, 6, -1, 14, 1, 1, 2};
        int[] maxAvgSubBF = getMaxAverageSubsetBruteForce(maxAvgArray, 4);
        System.out.println(Arrays.toString(maxAvgSubBF) + " with average = " + Arrays.stream(maxAvgSubBF).average());
        // space optimised max average subset.
        System.out.println("optimised");
        int[] maxAvgSubOpt = getOptimisedAverage(maxAvgArray, 4);
        System.out.println(Arrays.toString(maxAvgSubOpt) + " with average = " + Arrays.stream(maxAvgSubOpt).average());
    }

    private static Integer getSum(int[] a) {
        return Arrays.stream(a).sum();
    }

    private static int[] getMaxAverageSubsetBruteForce(int arr[], int k) {
        // make subset of k integers
        // brute force method................
        int low = 0;
        int high = k;
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
                    System.out.println(Arrays.toString(sub) + " has avg: " + Arrays.stream(sub).average().getAsDouble());
                    if(getSum(sub)/k > maxAvg) {
                        maxAvg = getSum(sub)/k;
                        low = i;
                        high = i+k;
                    }
                }
            }
        }
        //...................................
        return Arrays.stream(arr, low, high).toArray();
    }

    private static int[] getOptimisedAverage(int[] arr, int k) {
        //[1, 3, 2, 6, -1, 4, 1, 8, 2], K=4
        int start=0, low=0, high=0;
        int winSum=0, maxSum = 0;
        double maxAvg = 0.0;
        if (k <= 0 || k > arr.length) {
            System.out.println("Cannot create subset of " + k + " length.");
            return null;
        }

        for(int end =0; end < arr.length; end ++) {
            winSum += arr[end];

            if(end > k-1) {
                //maxSum = Math.max(maxSum, winSum);
                if(maxSum < winSum) {
                    maxSum = winSum;
                    low = start-1; high = end-1;
                }
                winSum -= arr[start];
                start ++;
            }
        }
        return Arrays.stream(arr, low, high).toArray();
    }

}
