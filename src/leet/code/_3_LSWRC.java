package leet.code;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class _3_LSWRC {

    /**
     * Longest Substring Without Repeating Characters
     *
     * Given a string, find the length of the longest substring without repeating characters.
     *
     * Example 1:
     *
     * Input: "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * Example 2:
     *
     * Input: "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     *
     * Input: "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     */



    public static void main(String[] args) {
        _3_LSWRC leet = new _3_LSWRC();
        int rslt = leet.lengthOfLongestSubstring_another("abbbaaaaccdefffabcdefkacl");
        System.out.println(rslt);
    }

    public int lengthOfLongestSubstring_another(String s) {
        int i = 0, j = 0, max = 0;
        Set<Character> set = new HashSet<>();

        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                max = Math.max(max, set.size());
                System.out.printf("j %d, i %d, max %d, set %s \n", j,i,max, set.toString());
            } else {
                set.remove(s.charAt(i++));
                System.out.printf("j %d, i %d, max %d, set %s \n", j,i,max, set.toString());
            }
        }

        return max;
    }

    public int lengthOfLongestSubstring(String s) {


        /*

        6 자리이면
        1자리씩 봤을때 logest dp[0] = 1 / 6
        2자리씩 끊었을 때 logest dp[1] = 2 / 5
        3자리씩 끊었을 떄 logest dp[2] = 3 / 4
        4자리씩 logest dp[3] = 0 / 3..
        5 dp[4] = 0
        6 dp[5] = 0

        Max(dp[1]...dp[6]) = 3

         */


        int length = s.length();

        if(length == 0) {
            return 0;
        }
        int[] dp = new int[length];
        dp[0] = 1; // always

        for(int i = 1 ; i < length ; i++) {

            for(int j = 0 ; j < length - i; j++) {

                String substr = s.substring(j, j+i+1);

//                System.out.printf("%s, %d, %d\n", substr, substr.length(), substr.chars().distinct().count());

                if(substr.length() == substr.chars().distinct().count()) {
                    dp[i] = i+1;
                    break;
                }
            }

//            System.out.println(Arrays.toString(dp));
        }

        return Arrays.stream(dp).max().orElse(0);
    }

}
