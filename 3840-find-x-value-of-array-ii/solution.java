import java.util.List;
import java.util.ArrayList;

class Solution {
    
    private static class Node {
        int[] remain;
        int prod;

        public Node(int k) {
            remain = new int[k];
            prod = 1;
        }
    }

    private int k;
    private Node[] tree;
    private int n;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;
        
        for (int i = 0; i < n; i++) {
            nums[i] %= k;
        }
        
        tree = new Node[4 * n];
        for (int i = 0; i < 4 * n; i++) {
            tree[i] = new Node(k);
        }
        
        build(nums, 0, 0, n - 1);
        
        List<Integer> ans = new ArrayList<>();
        for (int[] query : queries) {
            int index = query[0];
            int value = query[1] % k;
            int start = query[2];
            int x = query[3];
            
            update(0, 0, n - 1, index, value);
            
            Node res = queryRange(0, 0, n - 1, start, n - 1);
            ans.add(res.remain[x]);
        }
        
        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }
        
        return result;
    }

    private void build(int[] nums, int cur, int left, int right) {
        if (left == right) {
            tree[cur].remain[nums[left]] = 1;
            tree[cur].prod = nums[left];
            return;
        }
        int mid = (left + right) / 2;
        build(nums, 2 * cur + 1, left, mid);
        build(nums, 2 * cur + 2, mid + 1, right);
        tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private void update(int treeIndex, int lo, int hi, int i, int val) {
        if (lo == hi) {
            for (int j = 0; j < k; j++) {
                tree[treeIndex].remain[j] = 0;
            }
            tree[treeIndex].remain[val] = 1;
            tree[treeIndex].prod = val;
            return;
        }
        int mid = (lo + hi) / 2;
        if (i <= mid) {
            update(2 * treeIndex + 1, lo, mid, i, val);
        } else {
            update(2 * treeIndex + 2, mid + 1, hi, i, val);
        }
        tree[treeIndex] = merge(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
    }

    private Node queryRange(int treeIndex, int lo, int hi, int i, int j) {
        if (i <= lo && hi <= j) {
            return tree[treeIndex];
        }
        int mid = (lo + hi) / 2;
        if (j <= mid) {
            return queryRange(2 * treeIndex + 1, lo, mid, i, j);
        } else if (i > mid) {
            return queryRange(2 * treeIndex + 2, mid + 1, hi, i, j);
        }
        Node leftNode = queryRange(2 * treeIndex + 1, lo, mid, i, j);
        Node rightNode = queryRange(2 * treeIndex + 2, mid + 1, hi, i, j);
        return merge(leftNode, rightNode);
    }

    private Node merge(Node left, Node right) {
        Node node = new Node(k);
        node.prod = (left.prod * right.prod) % k;
        for (int i = 0; i < k; i++) {
            node.remain[i] = left.remain[i];
        }
        for (int i = 0; i < k; i++) {
            node.remain[(i * left.prod) % k] += right.remain[i];
        }
        return node;
    }
}
