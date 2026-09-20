class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        for (int i=0; i<s.length(); i++){
            char c =s.charAt(i);
            int revPos = 26 - (c-'a');
            int strPos = i+ 1;
            totalSum += revPos * strPos;
        }
        return totalSum;
    }
}
