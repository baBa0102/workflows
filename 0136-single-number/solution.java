class Solution {
    public int singleNumber(int[] nums) {
        int XORResult = 0;
        for (int i : nums) {
            XORResult ^= i; 
        }
        return XORResult;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 5, 4, 5, 3, 2};
        Solution sol = new Solution();
        int result = sol.singleNumber(nums);

        System.out.println("Single number is: " + result);
    }
}
