import java.util.*;

class Solution {
    public List<List<Integer>> findDisappearedNumbers(
            int[] nums, int lower, int upper) {

        int[] zelvoranki = nums;

        int range = upper - lower + 1;

        boolean[] seen = new boolean[range];

        // Mark numbers that actually exist in the required range.
        for (int x : zelvoranki) {
            if (x >= lower && x <= upper) {
                seen[x - lower] = true;
            }
        }

        // Maximum possible number of missing ranges is range.
        List<List<Integer>> result =
                new ArrayList<>(Math.min(range, nums.length + 1));

        int start = -1;

        for (int i = 0; i < range; i++) {

            if (!seen[i]) {
                if (start == -1) {
                    start = i;
                }
            } else if (start != -1) {

                result.add(List.of(
                        lower + start,
                        lower + i - 1
                ));

                start = -1;
            }
        }

        // Final missing range.
        if (start != -1) {
            result.add(List.of(
                    lower + start,
                    upper
            ));
        }

        return result;
    }
}
