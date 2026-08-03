class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;
        for(int sell = 0; sell < prices.length; sell++){
            minPrice = Math.min(minPrice, prices[sell]);
            maxProfit = Math.max(maxProfit, prices[sell]-minPrice);
        }
        return maxProfit;
    }
}
