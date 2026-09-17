class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);
        
        int sum = 0;
        int left = 0;
        int ans = Integer.MAX_VALUE;
        int minLenSoFar = Integer.MAX_VALUE;
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            if (sum == target) {
                int currLen = right - left + 1;
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, currLen + best[left - 1]);
                }
                minLenSoFar = Math.min(minLenSoFar, currLen);
            }
            best[right] = minLenSoFar;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
