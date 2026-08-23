class Solution {
    public int longestSubarray(int[] nums, int k) {
        int max = 0;
        for (int x : nums) {
            if (x > max) max = x;
        }

        int[] spf = new int[max + 1];
        for (int i = 2; i * i <= max; i++) {
            if (spf[i] == 0) {
                for (int j = i * i; j <= max; j += i) {
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }

        int[] freq = new int[max + 1];
        int left = 0, distinct = 0;

        for (int right = 0; right < nums.length; right++) {
            int val = nums[right];
            
            while (val > 1) {
                int p = spf[val] == 0 ? val : spf[val];
                if (freq[p]++ == 0) distinct++;
                while (val % p == 0) val /= p;
            }

            if (distinct > k) {
                int leftVal = nums[left++];
                while (leftVal > 1) {
                    int p = spf[leftVal] == 0 ? leftVal : spf[leftVal];
                    if (--freq[p] == 0) distinct--;
                    while (leftVal % p == 0) leftVal /= p;
                }
            }
        }

        return nums.length - left;
    }
}
