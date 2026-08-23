class Solution {
    public boolean isPalindromic(String s) {

        int left = 0;
        int right = s.length() * 8 - 1;

        while (left < right) {

            int leftChar = s.charAt(left / 8);
            int rightChar = s.charAt(right / 8);

            int leftBit = (leftChar >> (7 - (left % 8))) & 1;
            int rightBit = (rightChar >> (7 - (right % 8))) & 1;

            if (leftBit != rightBit) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
