// Файл: Main.java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] dataset = {56, 52, 53, 84, 45, 36, 11, 41, 95, 77};
        int T1 = 95, T2 = 52;

        System.out.println("========== A1: QUICK SORT ==========");
        int[] arr1 = dataset.clone();
        QuickSort.partition(arr1, 0, arr1.length - 1);

        System.out.println("\n========== A2: MERGE SORT ==========");
        int[] arr2 = dataset.clone();
        MergeSort.mergeSort(arr2, 0, arr2.length - 1);
        System.out.println("Sorted: " + Arrays.toString(arr2));

        System.out.println("\n========== A3: BUBBLE SORT ==========");
        int[] arr3 = dataset.clone();
        BubbleSort.sort(arr3);

        System.out.println("\n========== B1: LINEAR SEARCH ==========");
        LinearSearch.search(dataset, T1);

        System.out.println("\n========== B2: DIVIDE & CONQUER ==========");
        DivideConquer.findRange(dataset);

        System.out.println("\n========== B3: BINARY SEARCH ==========");
        int[] sorted = {11, 36, 41, 45, 52, 53, 56, 77, 84, 95};
        BinarySearch.search(sorted, T2);
    }
}

// ─── Все остальные классы ниже, без слова public ───

class QuickSort {
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int storeIndex = low + 1;
        System.out.println("Pivot = " + pivot);
        for (int j = low + 1; j <= high; j++) {
            if (arr[j] < pivot) {
                int temp = arr[storeIndex];
                arr[storeIndex] = arr[j];
                arr[j] = temp;
                System.out.println("Swap → " + Arrays.toString(arr));
                storeIndex++;
            }
        }
        int temp = arr[low];
        arr[low] = arr[storeIndex - 1];
        arr[storeIndex - 1] = temp;
        System.out.println("Pivot " + pivot + " → index " + (storeIndex - 1));
        System.out.println("Result: " + Arrays.toString(arr));
        return storeIndex - 1;
    }
}

class MergeSort {
    static void merge(int[] arr, int left, int mid, int right) {
        int[] L = Arrays.copyOfRange(arr, left, mid + 1);
        int[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);
        System.out.println("Merging: " + Arrays.toString(L) + " + " + Arrays.toString(R));
        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length)
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        while (i < L.length) arr[k++] = L[i++];
        while (j < R.length) arr[k++] = R[j++];
        System.out.println("Result:  " + Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
    }

    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
}

class BubbleSort {
    static void sort(int[] arr) {
        int n = arr.length;
        System.out.println("Initial: " + Arrays.toString(arr));
        for (int pass = 1; pass <= 3; pass++) {
            boolean swapped = false;
            for (int i = 0; i < n - pass; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i]; arr[i] = arr[i+1]; arr[i+1] = temp;
                    swapped = true;
                }
            }
            System.out.println("Pass " + pass + ": " + Arrays.toString(arr));
            if (!swapped) { System.out.println("Early Exit!"); break; }
        }
    }
}

class LinearSearch {
    static void search(int[] arr, int target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            System.out.println("Comparison " + comparisons + ": arr[" + i + "]=" + arr[i] + (arr[i] == target ? " ✓ FOUND!" : ""));
            if (arr[i] == target) {
                System.out.println("Found at index " + i + " | Total comparisons: " + comparisons);
                return;
            }
        }
    }
}

class DivideConquer {
    static void findRange(int[] arr) {
        int[] result = minMax(arr, 0, arr.length - 1);
        System.out.println("Min=" + result[0] + ", Max=" + result[1] + ", Range=" + (result[1] - result[0]));
    }

    static int[] minMax(int[] arr, int low, int high) {
        if (low == high) return new int[]{arr[low], arr[low]};
        if (high == low + 1)
            return arr[low] < arr[high] ? new int[]{arr[low], arr[high]} : new int[]{arr[high], arr[low]};
        int mid = (low + high) / 2;
        int[] L = minMax(arr, low, mid);
        int[] R = minMax(arr, mid + 1, high);
        int mn = Math.min(L[0], R[0]);
        int mx = Math.max(L[1], R[1]);
        System.out.println("Combine → min=" + mn + ", max=" + mx);
        return new int[]{mn, mx};
    }
}

class BinarySearch {
    static void search(int[] arr, int target) {
        int low = 0, high = arr.length - 1, step = 1;
        System.out.println("Array: " + Arrays.toString(arr));
        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println("Step " + step + ": Low=" + low + " High=" + high + " Mid=" + mid + " arr[mid]=" + arr[mid]);
            if (arr[mid] == target) { System.out.println("✓ Found at index " + mid); return; }
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
            step++;
        }
    }
}