import java.util.Arrays;

class Solution {

    private static final long INF = Long.MAX_VALUE / 4;

    public long minCost(int m, int n, int[][] penalty) {

        int cells = m * n;
        int states = cells << 1;

        long[] dist = new long[states];
        Arrays.fill(dist, INF);

        /*
         * State:
         *
         * state = (cell << 1) | parity
         *
         * parity 0 = next action is ODD
         * parity 1 = next action is EVEN
         */

        dist[0] = 1L;

        MinHeap heap = new MinHeap(states + 16);

        heap.push(1L, 0);

        int target = cells - 1;

        while (!heap.isEmpty()) {

            long curCost = heap.peekCost();
            int state = heap.peekState();

            heap.pop();

            if (curCost != dist[state]) {
                continue;
            }

            int cell = state >> 1;
            int parity = state & 1;

            if (cell == target) {
                return curCost;
            }

            int r = cell / n;
            int c = cell - r * n;

            int nextParity = parity ^ 1;

            /*
             * WAIT
             *
             * Waiting always flips parity and costs penalty[r][c].
             */
            long waitCost = curCost + penalty[r][c];

            int waitState = (cell << 1) | nextParity;

            if (waitCost < dist[waitState]) {
                dist[waitState] = waitCost;
                heap.push(waitCost, waitState);
            }

            /*
             * ENTER COST:
             *
             * destination (nr,nc):
             * (nr + 1) * (nc + 1)
             */

            // -----------------------------------------
            // DOWN
            // -----------------------------------------
            if (r + 1 < m) {

                int nextCell = cell + n;
                long moveCost =
                    curCost + (long)(r + 2) * (c + 1);

                // Odd action => DOWN is valid.
                // Even action => DOWN violates rule.
                if (parity == 1) {
                    moveCost += penalty[r][c];
                }

                int nextState = (nextCell << 1) | nextParity;

                if (moveCost < dist[nextState]) {
                    dist[nextState] = moveCost;
                    heap.push(moveCost, nextState);
                }
            }

            // -----------------------------------------
            // UP
            // -----------------------------------------
            if (r > 0) {

                int nextCell = cell - n;
                long moveCost =
                    curCost + (long)r * (c + 1);

                // Odd action => UP violates rule.
                // Even action => UP is valid.
                if (parity == 0) {
                    moveCost += penalty[r][c];
                }

                int nextState = (nextCell << 1) | nextParity;

                if (moveCost < dist[nextState]) {
                    dist[nextState] = moveCost;
                    heap.push(moveCost, nextState);
                }
            }

            // -----------------------------------------
            // RIGHT
            // -----------------------------------------
            if (c + 1 < n) {

                int nextCell = cell + 1;
                long moveCost =
                    curCost + (long)(r + 1) * (c + 2);

                // Odd action => RIGHT is valid.
                // Even action => RIGHT violates rule.
                if (parity == 1) {
                    moveCost += penalty[r][c];
                }

                int nextState = (nextCell << 1) | nextParity;

                if (moveCost < dist[nextState]) {
                    dist[nextState] = moveCost;
                    heap.push(moveCost, nextState);
                }
            }

            // -----------------------------------------
            // LEFT
            // -----------------------------------------
            if (c > 0) {

                int nextCell = cell - 1;
                long moveCost =
                    curCost + (long)(r + 1) * c;

                // Odd action => LEFT violates rule.
                // Even action => LEFT is valid.
                if (parity == 0) {
                    moveCost += penalty[r][c];
                }

                int nextState = (nextCell << 1) | nextParity;

                if (moveCost < dist[nextState]) {
                    dist[nextState] = moveCost;
                    heap.push(moveCost, nextState);
                }
            }
        }

        return -1;
    }


    // -------------------------------------------------
    // Custom primitive binary min-heap
    // -------------------------------------------------

    static class MinHeap {

        private long[] costs;
        private int[] states;
        private int size;

        MinHeap(int capacity) {
            costs = new long[capacity];
            states = new int[capacity];
        }

        boolean isEmpty() {
            return size == 0;
        }

        long peekCost() {
            return costs[0];
        }

        int peekState() {
            return states[0];
        }

        void push(long cost, int state) {

            if (size == costs.length) {
                int newCapacity = costs.length << 1;

                costs = Arrays.copyOf(costs, newCapacity);
                states = Arrays.copyOf(states, newCapacity);
            }

            int i = size++;

            while (i > 0) {

                int parent = (i - 1) >>> 1;

                if (costs[parent] <= cost) {
                    break;
                }

                costs[i] = costs[parent];
                states[i] = states[parent];

                i = parent;
            }

            costs[i] = cost;
            states[i] = state;
        }

        void pop() {

            int last = --size;

            if (last < 0) {
                return;
            }

            long lastCost = costs[last];
            int lastState = states[last];

            if (size == 0) {
                return;
            }

            int i = 0;

            while (true) {

                int left = (i << 1) + 1;

                if (left >= size) {
                    break;
                }

                int right = left + 1;

                int child = left;

                if (right < size &&
                    costs[right] < costs[left]) {
                    child = right;
                }

                if (costs[child] >= lastCost) {
                    break;
                }

                costs[i] = costs[child];
                states[i] = states[child];

                i = child;
            }

            costs[i] = lastCost;
            states[i] = lastState;
        }
    }
}
