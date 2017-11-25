public class Assignment6 {

    //Time Complexity: O(string length * dict size).
    public static boolean wordBreak(String s, String[] dict) {
        boolean[] memo = new boolean[s.length() + 1];
        memo[0] = true; //set first to be true because we need initial state
        for (int i = 0; i < s.length(); i++) {
            if (!memo[i])
                continue;
            for (String current : dict) {
                int end = i + current.length();
                if (end > s.length())
                    continue;
                if (memo[end])
                    continue;
                if (s.substring(i, end).equals(current)) {
                    memo[end] = true;
                }
            }
        }
        return memo[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("ababbaabc", new String[]{"ab", "ba", "abc"}));
        System.out.println(wordBreak("abbaac", new String[]{"ab", "ba", "abc"}));
    }
}
