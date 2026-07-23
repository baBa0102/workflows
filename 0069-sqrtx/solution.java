class Solution {
    public int mySqrt(int x) {
        if (x == 1 || x == 0)
            return x;
        int start =1;
        int end = x;
        while(start<=end){
            int mid = start+(end - start)/2;
            if ((long) mid*mid > (long) x){
                end = mid -1;
            }
            else if( mid * mid == x){
                return mid;
            }
            else{
                start = mid +1;
            }

        }
        return (int) end;
    }

}
