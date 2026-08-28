class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        char[] a = s.toCharArray();
        char[] t = target.toCharArray();
        int n = a.length;

        int[] cnt = new int[26];

        for (char c : a) {
            cnt[c - 'a']++;
        }

        char mid = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) != 0) {
                if (mid != 0) {
                    return "";
                }
                mid = (char) ('a' + i);
                cnt[i]--;
            }
        }

        int half = n / 2;
        boolean found = false;
        int idx = 0;

        for (; idx < half; idx++) {
            int x = t[idx] - 'a';

            if (cnt[x] < 2) {
                for (int j = x + 1; j < 26; j++) {
                    if (cnt[j] >= 2) {
                        a[idx] = (char) ('a' + j);
                        cnt[j] -= 2;
                        found = true;
                        break;
                    }
                }
                break;
            }

            a[idx] = t[idx];
            cnt[x] -= 2;
        }

        if (!found && idx == half) {
            if (n % 2 == 1 && mid > t[half]) {
                found = true;
            } else if (n % 2 == 0 || mid == t[half]) {
                for (int i = half - 1; i >= 0; i--) {
                    if (a[i] > t[n - 1 - i]) {
                        found = true;
                        break;
                    }

                    if (a[i] < t[n - 1 - i]) {
                        break;
                    }
                }
            }
        }

        if (!found) {
            for (--idx; idx >= 0; idx--) {
                int x = t[idx] - 'a';
                cnt[x] += 2;

                for (int j = x + 1; j < 26; j++) {
                    if (cnt[j] >= 2) {
                        a[idx] = (char) ('a' + j);
                        cnt[j] -= 2;
                        found = true;
                        break;
                    }
                }

                if (found) {
                    break;
                }
            }
        }

        if (!found) {
            return "";
        }

        for (++idx; idx < half; idx++) {
            for (int j = 0; j < 26; j++) {
                if (cnt[j] >= 2) {
                    a[idx] = (char) ('a' + j);
                    cnt[j] -= 2;
                    break;
                }
            }
        }

        String left = new String(a, 0, half);
        StringBuilder ans = new StringBuilder(left);
        ans.reverse();
        ans.insert(0, left);

        if (mid != 0) {
            ans.insert(half, mid);
        }

        return ans.toString();
    }
}
