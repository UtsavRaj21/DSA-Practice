import java.util.*;

public class p1 {

    public static void merge(int arr1[], int arr2[], int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr1[i] > arr2[j]) {
                    int temp = arr1[i];
                    arr1[i] = arr2[j];
                    arr2[j] = temp;
                    break;
                }
            }
            Arrays.sort(arr2);
        }
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 2, 5, 8 };
        int[] arr2 = { 0, 3, 6, 7, 9 };

        merge(arr1, arr2, arr1.length, arr2.length);

        for (int ele : arr1) {
            System.out.print(ele + " ");
        }

        for (int ele : arr2) {
            System.out.print(ele + " ");
        }
    }
}
