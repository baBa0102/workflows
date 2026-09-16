class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int totalItems = n + k - 1;
        int chosen = 2 * k;
        long[][] dp = new long[totalItems + 1][chosen + 1];
        
        for (int i = 0; i <= totalItems; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(i, chosen); j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }
        
        return (int) dp[totalItems][chosen];
    }
}
