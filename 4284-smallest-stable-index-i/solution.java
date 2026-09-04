class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        for(int i = 0; i< n; i++){
            long maxVal = nums[0];
            for (int j = 0; j<= i; j++){
                if (nums[j] > maxVal){
                    maxVal = nums[j];
                }
            }
            long minVal = nums[i];
            for(int j = i; j<n; j++){
                if(nums[j]< minVal){
                    minVal = nums[j];
                }
            }
            if(maxVal - minVal <= k ){
                return i;
            }
        }
        return -1;
    }
}
