class Solution {
    private record T(long weight, List<Integer> selected){}
    private record Interval(int left, int right, int weight, int originalIndex){}
    
    public int[] maximumWeight(List<List<Integer>> rawIntervals) {
        int n = rawIntervals.size();
        List<Interval> intervals = new ArrayList<>();
        
        for (int i = 0; i < n; ++i) {
            List<Integer> interval = rawIntervals.get(i);
            intervals.add(new Interval(interval.get(0), interval.get(1), interval.get(2), i));
        }
        
        intervals.sort(Comparator.comparingInt(Interval::left));
        T[][] memo = new T[n][5];
        T result = dp(intervals, memo, 0, 4);
        
        int[] ans = new int[result.selected.size()];
        for (int i = 0; i < result.selected.size(); i++) {
            ans[i] = result.selected.get(i);
        }
        return ans;
    }
    
    private T dp(List<Interval> intervals, T[][] memo, int i, int quota) {
        if (i == intervals.size() || quota == 0) {
            return new T(0, new ArrayList<>()); // Fixed Array to ArrayList
        }
        if (memo[i][quota] != null) {
            return memo[i][quota];
        }
        
        T skip = dp(intervals, memo, i + 1, quota);
        Interval curr = intervals.get(i);
        int j = findFirstGreater(intervals, i + 1, curr.right);
        T nextRes = dp(intervals, memo, j, quota - 1);
        
        List<Integer> newSelected = new ArrayList<>(nextRes.selected);
        newSelected.add(curr.originalIndex);
        Collections.sort(newSelected);
        
        T pick = new T(curr.weight + nextRes.weight, newSelected);
        
        if (pick.weight > skip.weight || (pick.weight == skip.weight && compareLists(pick.selected, skip.selected) < 0)) {
            memo[i][quota] = pick;
        } else {
            memo[i][quota] = skip;
        }
        
        return memo[i][quota];
    }
    
    private int findFirstGreater(List<Interval> intervals, int startFrom, int rightBoundary) {
        int l = startFrom;
        int r = intervals.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (intervals.get(m).left > rightBoundary) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
    
    private int compareLists(List<Integer> list1, List<Integer> list2) {
        int minSize = Math.min(list1.size(), list2.size());
        for (int i = 0; i < minSize; i++) {
            int cmp = Integer.compare(list1.get(i), list2.get(i));
            if (cmp != 0) return cmp;
        }
        return Integer.compare(list1.size(), list2.size());
    }
}
