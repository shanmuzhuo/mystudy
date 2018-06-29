package com.study.leetcode;

/**
 * @author zhenxuan.zhuo
 * 557. Reverse Words in a String III
 */
public class SolutionFor557 {
	public String reverseWords(String s) {
		String str[] = s.split(" ");
		String res = "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			sb.delete( 0, sb.length() );
			char c[] = str[i].toCharArray();
			for(int j = 0 ; j < c.length; j++) {
				sb.append(c[c.length - 1 - j]);
			}
			str[i] = sb.toString();
		}
		
		for (int i = 0; i < str.length; i++) {
			res += str[i] + " ";
		}
		return res.trim();
    }
	
	public static void main(String[] args) {
		SolutionFor557 for557 = new SolutionFor557();
		for557.reverseWords("Let's take LeetCode contest");
	}
}
