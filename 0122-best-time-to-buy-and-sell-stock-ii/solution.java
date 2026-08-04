class Solution {
    public int maxProfit(int[] prices) {
        int totalprofit = 0;
        for(int sell= 1; sell < prices.length ; sell++){
            if(prices[sell]> prices[sell-1]){
                totalprofit += prices[sell] - prices[sell-1];
            }
        }
        return totalprofit;
    }
}
