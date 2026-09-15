class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len <= 2 || isPal[i + 1][j - 1]) {
                        isPal[i][j] = true;
                    }
                }
            }
        }
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            // Option 1: Carry over previous max count
            if (i > 0) {
                dp[i] = dp[i - 1];
            }
            for (int j = 0; j <= i; j++) {
                if (i - j + 1 >= k && isPal[j][i]) {
                    int prev = (j > 0) ? dp[j - 1] : 0;
                    dp[i] = Math.max(dp[i], prev + 1);
                }
            }
        }
        return dp[n - 1];
    }
}
