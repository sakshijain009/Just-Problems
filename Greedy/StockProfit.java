/*
Given an array prices[] of length n, representing the prices of the stocks on different days.
The task is to find the maximum profit possible by buying and selling the stocks on different days
when at most one transaction is allowed.

One transaction = 1 buy + 1 sell.

Note:
- You must buy the stock before you sell it.
- If it is not possible to make a profit, return 0.

Example:
Input: prices = [7, 1, 5, 3, 6, 4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6 - 1 = 5.

Input: prices = [7, 6, 4, 3, 1]
Output: 0
Explanation: No transaction is done, i.e., max profit = 0.
*/

public class StockProfit {

    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            // Update minPrice if we find a new lower price
            if (price < minPrice) {
                minPrice = price;
            }
            // Calculate profit if selling at current price
            else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {7, 6, 4, 3, 1};

        System.out.println("Max profit for prices1: " + maxProfit(prices1)); // 5
        System.out.println("Max profit for prices2: " + maxProfit(prices2)); // 0
    }
}
