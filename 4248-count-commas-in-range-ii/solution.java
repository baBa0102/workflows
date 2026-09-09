class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long thershold = 1000;
        while(n >= thershold){
            totalCommas += (n-thershold +1);
            thershold *= 1000;
        }
        return totalCommas;
    }
}
