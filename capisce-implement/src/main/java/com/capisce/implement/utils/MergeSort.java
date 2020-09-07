package com.capisce.implement.utils;

public class MergeSort {
    public static void main(String[] arg) {
        int[] arr = {1, 3141, 4152, 241152, 422, 2341, 256, 43};
        int[] result = new int[arr.length];
        mergeSort(arr, result, 0, arr.length-1);
        System.out.println(1);
    }

    private static void mergeSort(int[] arr, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }
        int len = end - start;
        int mid = start + len / 2;
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        mergeSort(arr, result, start1, end1);
        mergeSort(arr, result, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            result[k++] = arr[start1] > arr[start2] ? arr[start1++] : arr[start2++];
        }
        while (start1 <= end1) {
            result[k++] = arr[start1++];
        }
        while (start2 <= end2) {
            result[k++] = arr[start2++];
        }
        for(k = start; k <=end ;k++){
            arr[k] = result[k];
        }
    }
}
