/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int matchingCount = 0;
    public int averageOfSubtree(TreeNode root) {
        matchingCount =0;
        dfs(root);
        return matchingCount;
    }
    private int[] dfs(TreeNode node){
        if(node ==null){
            return new int []{0,0};
        }
        int [] leftResult = dfs(node.left);
        int[] rightResult = dfs (node.right);
        int currentSum = node.val + leftResult[0] + rightResult[0];
        int currentCount = 1+ leftResult[1] + rightResult[1];
        if(node.val == currentSum / currentCount){
            matchingCount ++;
        }
        return new int [] {currentSum, currentCount};
    }
}
