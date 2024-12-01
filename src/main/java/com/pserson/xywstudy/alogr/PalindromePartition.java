package com.pserson.xywstudy.alogr;

/**
 * 回文子串
 */
public class PalindromePartition {

    public static void main(String[] args) {
        String s = "aab";
        System.out.println(minCut(s));
    }

    public static int minCut(String s){
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int[] minCut = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++){
            minCut[i] = i -1;
        }
        boolean[][] pali = new boolean[s.length()][s.length()];
        assignPali(pali,s);
        for (int i = 1; i < s.length(); i ++){
            for (int j = 0; j <= i; j++){
                if (pali[j][i - 1]){
                    minCut[i] = Math.min(minCut[i], minCut[j] + 1);
                }
            }
        }
        return minCut[s.length()];
    }

    private static void assignPali(boolean[][] pali, String s) {
        int length = s.length();
        for (int i = 0; i < length; i++){
            pali[i][i] = true;
        }
        for (int i = 0; i < length - 1; i++){
            if (s.charAt(i) == s.charAt(i + 1)){
                pali[i][i + 1] = true;
            }
        }
        for (int i = 2; i < length; i ++){
            for (int j = 0; j < length - i; j++){
                if (pali[j + 1][j + i - 1] && s.charAt(j) == s.charAt(j + i)){
                    pali[j][j + i] = true;
                }
            }
        }
    }
}
