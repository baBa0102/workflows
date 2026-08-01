import java.util.*;

class Solution {
    public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {

        int n = series1.length;
        int m = series2.length;

        List<List<Integer>> result = new ArrayList<>(n + m);

        int i = 0;
        int j = 0;

        while (i < n && j < m) {

            int t1 = series1[i][0];
            int t2 = series2[j][0];

            if (t1 < t2) {
                result.add(List.of(t1, series1[i][1] + series2[j][1]));
                i++;

            } else if (t2 < t1) {
                result.add(List.of(t2, series1[i][1] + series2[j][1]));
                j++;

            } else {
                result.add(List.of(t1, series1[i][1] + series2[j][1]));
                i++;
                j++;
            }
        }

        // Remaining series1
        while (i < n) {
            result.add(List.of(series1[i][0], series1[i][1]));
            i++;
        }

        // Remaining series2
        while (j < m) {
            result.add(List.of(series2[j][0], series2[j][1]));
            j++;
        }

        return result;
    }
}
