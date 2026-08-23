class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sum1 = 0, sum2 = 0;
        int cnt1 = 0, cnt2 = 0;

        int mid = n/2 ;
        for(int i = 0; i<mid; i++){
            char c = num.charAt(i);
            if (c =='?'){
                cnt1++;
            }else{
                sum1 +=(c-'0');
            }
        }
        for(int i = mid; i<n; i++){
            char c= num.charAt(i);
            if(c=='?'){
                cnt2++;
            }else{
                sum2 +=(c - '0');
            }
        }
        int totalQ = cnt1 + cnt2;
        if (totalQ % 2 != 0){
            return true;
        }
        return (sum1 - sum2) != 9 * (cnt2 - cnt1)/2;
    }
}
