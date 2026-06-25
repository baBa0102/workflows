//solution - without converting integer into string
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0)
        return false;
        int reverse = 0;
        int original = x;
        while(original != 0){
            reverse = reverse * 10 + original % 10;
            original = original / 10;
        }
        return (reverse == x);
    }
}
        
