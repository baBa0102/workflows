class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[][] intervals = new int[26][2];
        for (int i = 0; i < 26; i++) {
            intervals[i][0] = n; 
        }
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            intervals[c - 'a'][0] = Math.min(intervals[c - 'a'][0], i);
            intervals[c - 'a'][1] = Math.max(intervals[c - 'a'][1], i);
        }
        List<int[]> validIntervals = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (intervals[i][0] == n) continue; 
            
            int left = intervals[i][0];
            int right = intervals[i][1];
            boolean isValid = true;
            for (int j = left; j <= right; j++) {
                int charIdx = s.charAt(j) - 'a';
                if (intervals[charIdx][0] < left) {
                    isValid = false; 
                    break;
                }
                right = Math.max(right, intervals[charIdx][1]);
            }
            
            if (isValid) {
                validIntervals.add(new int[]{left, right});
            }
        }
        Collections.sort(validIntervals, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        List<String> result = new ArrayList<>();
        int prevEnd = -1;
        
        for (int[] interval : validIntervals) {
            int start = interval[0];
            int end = interval[1];
            if (start > prevEnd) {
                result.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }
        
        return result;
    }
}
