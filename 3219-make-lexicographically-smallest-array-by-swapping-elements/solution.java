import java.util.Arrays;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;
        int[][] a = new int[n][2];

        for (int i = 0; i < n; i++) {
            a[i][0] = nums[i];
            a[i][1] = i;
        }

        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));

        long[] packed = new long[n];
        int[] result = new int[n];

        int start = 0;

        while (start < n) {

            int end = start + 1;

            while (end < n &&
                   (long)a[end][0] - a[end - 1][0] <= limit) {
                end++;
            }

            for (int i = start; i < end; i++) {
                packed[i] =
                    ((long)a[i][1] << 32) |
                    (a[i][0] & 0xffffffffL);
            }

            Arrays.sort(packed, start, end);

            for (int i = start; i < end; i++) {
                int originalIndex = (int)(packed[i] >>> 32);
                result[originalIndex] = a[i][0];
            }

            start = end;
        }

        return result;
    }
}
