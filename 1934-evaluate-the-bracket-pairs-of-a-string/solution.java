class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> mapping = new HashMap<>();
        for (List<String> pair : knowledge) {
            mapping.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder res = new StringBuilder();
        int i = 0;
        int n = s.length();
        
        while (i < n) {
            if (s.charAt(i) == '(') {
                i++;
                int keyStart = i;
                while (i < n && s.charAt(i) != ')') {
                    i++;
                }
                String key = s.substring(keyStart, i);
                res.append(mapping.getOrDefault(key, "?"));
                i++;
            } else {
                res.append(s.charAt(i));
                i++;
            }
        }
        
        return res.toString();
    }
}
