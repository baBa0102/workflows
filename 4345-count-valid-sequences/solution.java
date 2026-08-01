class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int MAX = 500_000;

    private static final int[] fact = new int[MAX + 1];
    private static final int[] invFact = new int[MAX + 1];

    private static boolean initialized = false;

    private static void init() {
        if (initialized) return;

        fact[0] = 1;

        for (int i = 1; i <= MAX; i++) {
            fact[i] = (int) ((long) fact[i - 1] * i % MOD);
        }

        invFact[MAX] = (int) power(fact[MAX], MOD - 2);

        for (int i = MAX; i > 0; i--) {
            invFact[i - 1] = (int) ((long) invFact[i] * i % MOD);
        }

        initialized = true;
    }

    public int countValidSequences(int n, int k) {

        init();

        // Total positive sequences:
        // C(n - 1, k - 1)
        long total = combination(n - 1, k - 1);

        // If n and k have different parity,
        // it is impossible for all k numbers to be odd.
        if (((n - k) & 1) != 0) {
            return (int) total;
        }

        // All numbers odd:
        //
        // x1 + x2 + ... + xk = n
        //
        // xi = 2*yi + 1
        //
        // y1 + ... + yk = (n-k)/2
        //
        // Number of solutions:
        // C((n+k-2)/2, k-1)

        int top = (n + k - 2) / 2;

        long odd = combination(top, k - 1);

        return (int) ((total - odd + MOD) % MOD);
    }

    private static long combination(int n, int r) {

        if (r < 0 || r > n) {
            return 0;
        }

        return (long) fact[n]
                * invFact[r] % MOD
                * invFact[n - r] % MOD;
    }

    private static long power(long a, long b) {

        long result = 1;

        while (b > 0) {

            if ((b & 1) != 0) {
                result = result * a % MOD;
            }

            a = a * a % MOD;
            b >>= 1;
        }

        return result;
    }
}
