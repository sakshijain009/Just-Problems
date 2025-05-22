import java.util.*;

public class Main {

    // Method to find k smallest elements while preserving original order
    static ArrayList<Integer> kSmallestElements(int arr[], int n, int k) {
        // Create a list to store the final answer
        ArrayList<Integer> ans = new ArrayList<>();

        // Temporary list to store array elements for sorting
        List<Integer> ls = new ArrayList<>();

        // Add all elements of the array to the temporary list
        for(int x : arr) {
            ls.add(x);
        }

        // Sort the list to find the k smallest elements
        Collections.sort(ls);

        // Get the k-th smallest element (0-based index)
        int last_element = ls.get(k - 1);

        // Calculate how many times the k-th smallest element should be included
        // This handles cases where the k-th smallest element appears multiple times
        // ls.indexOf(last_element) gives the first occurrence of that element in the sorted list.
        int diff = k - ls.indexOf(last_element);

        // Traverse the original array to maintain the input order
        for(int x : arr) {
            if(x == last_element) {
                // Include the k-th smallest element only 'diff' number of times
                if(diff > 0) {
                    ans.add(x);
                    diff--;
                }
            }
            else if(x < last_element) {
                // Include elements smaller than the k-th smallest element
                ans.add(x);
            }
        }

        // Return the list of k smallest elements in original order
        return ans;
    }

    public static void main(String[] args) {
        // Sample input
        int arr[] = {7, 10, 4, 3, 20, 15};
        int n = arr.length;
        int k = 3;

        // Get k smallest elements
        ArrayList<Integer> result = kSmallestElements(arr, n, k);

        // Print the result
        System.out.println("K smallest elements:");
        for(int val : result) {
            System.out.print(val + " ");
        }
    }
}
