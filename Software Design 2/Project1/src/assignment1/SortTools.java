package assignment1;// SortTools.java
/*
 * EE422C Project 1 submission by
 * Replace <...> with your actual data.
 * <Siddharth Benoy>
 * <sb62297>
 * <17195>
 * Fall 2021
 * Slip days used:
 */

public class SortTools {
    /**
      * Return whether the first n elements of x are sorted in non-decreasing
      * order.
      * @param x is the array
      * @param n is the size of the input to be checked
      * @return true if array is sorted
      */
    public static boolean isSorted(int[] x, int n) {
        // stub only, you write this!
        // TODO: complete it
        if(x.length == 0 || n == 0)
            return false;
        for(int i = 0; i < n-1; i++){
            if(x[i] > x[i+1])
                return false;
        }
        return true;
    }

    /**
     * Return an index of value v within the first n elements of x.
     * @param x is the array
     * @param n is the size of the input to be checked
     * @param v is the value to be searched for
     * @return any index k such that k < n and x[k] == v, or -1 if no such k exists
     */
    public static int find(int[] x, int n, int v) {
        // stub only, you write this!
        // TODO: complete it
        int high = n-1;             //binary search
        int low = 0;
        while(high > low){
            int mid = high+low/2;
            if(x[mid] == v)
                return high;
            else if(v > x[mid])
                low = mid+1;
            else
                high = mid-1;
            }
        return -1;
        }


    /**
     * Return a sorted, newly created array containing the first n elements of x
     * and ensuring that v is in the new array.
     * @param x is the array
     * @param n is the number of elements to be copied from x
     * @param v is the value to be added to the new array if necessary
     * @return a new array containing the first n elements of x as well as v
     */
    public static int[] copyAndInsert(int[] x, int n, int v) {
        // stub only, you write this!
        // TODO: complete it
        int y[] = new int[n+1];
        int z[] = x.clone();
        int j = 0;
        int flag = 0;
        if(find(x, n, v) != -1){
            return z;
        }
        else{
            for(int i = 0; i < n+1; i++){
                y[i] = x[j];
                if(i == n-1 && flag == 0){
                    i++;
                    y[i] = v;
                }
                else if(v >= x[j] && v <= x[j+1] && flag != 1){
                    i++;
                    y[i] = v;
                    flag = 1;
                }

                j++;

            }
        }
        return y;


    }

    /**
     * Insert the value v in the first n elements of x if it is not already
     * there, ensuring those elements are still sorted.
     * @param x is the array
     * @param n is the number of elements in the array
     * @param v is the value to be added
     * @return n if v is already in x, otherwise returns n+1
     */
    public static int insertInPlace(int[] x, int n, int v) {
        // stub only, you write this!
        // TODO: complete it
        int k =0;
        int j = 0;
        if(find(x, n, v) != -1){
            return n;
        }
        else{
            for(int i = 0; i < n; i++){
                if(i == n-1 && find(x,n, v) == -1){
                    x[i+1] = v;
                }
                else if(v >= x[i] && v < x[i+1] && k != 1){
                    j = x[i+1];
                    x[i+1] = v;
                    i++;
                    x[i+1] = j;
                }

            }
        }
        return n+1;
    }

    /**
     * Sort the first n elements of x in-place in non-decreasing order using
     * insertion sort.
     * @param x is the array to be sorted
     * @param n is the number of elements of the array to be sorted
     */
    public static void insertSort(int[] x, int n) {
        // stub only, you write this!
        // TODO: complete it
        int temp;                   //insertion sort
        for(int i = 1; i < n-1; i++){
            temp = x[i];
            int j = i-1;
            while(j >= 0 && x[j] >= temp){
                x[j+1] = x[j];
                j -= 1;
            }
            x[j+1] = temp;
        }

    }
}
