class Solution {
    public int missingMultiple(int[] nums, int k) {
        boolean[] present = new boolean[105];
        for (int num: nums){
            if (num < present.length){
                present[num] = true;
            }
        }
        int i = 1;
        while(true){
            int multiple = k*i;
            if (multiple >= present.length || !present[multiple]){
                return multiple;
            }
            i++;
        }
    }
}
