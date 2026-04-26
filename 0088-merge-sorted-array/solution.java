import java.util.Arrays;

// The Solution class should be a top-level class
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;      // Pointer for end of valid nums1
        int j = n - 1;      // Pointer for end of nums2
        int k = m + n - 1;  // Pointer for end of total nums1 array

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // Only need to catch the remainder of nums2
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        
        sol.merge(nums1, 3, nums2, 3);
        
        System.out.println(Arrays.toString(nums1));
    }
}
