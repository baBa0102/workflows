class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];
        for (int num : nums) {
            long[] nextDp = new long[k];
            int val = num % k;
            nextDp[val]++;
            for (int i = 0; i < k; i++) {
                if (dp[i] > 0) {
                    nextDp[(i * val) % k] += dp[i];
                }
            }
            for (int i = 0; i < k; i++) {
                result[i] += nextDp[i];
                dp[i] = nextDp[i];
            }
        }
        
        return result;
    }
}
