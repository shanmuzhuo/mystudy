package com.study.leecode;

/**
 * @author zhenxuan.zhuo
 * @describe 806. Number of Lines To Write String We are to write the letters of
 *           a given string S, from left to right into lines. Each line has
 *           maximum width 100 units, and if writing a letter would cause the
 *           width of the line to exceed 100 units, it is written on the next
 *           line. We are given an array widths, an array where widths[0] is the
 *           width of 'a', widths[1] is the width of 'b', ..., and widths[25] is
 *           the width of 'z'.
 * 
 *           Now answer two questions: how many lines have at least one
 *           character from S, and what is the width used by the last such line?
 *           Return your answer as an integer list of length 2.
 */
public class SolutionFor806 {
	public int[] numberOfLines(int[] widths, String S) {
		int rows = 0, cols = 0 ; 
		for (int i = 0; i < S.length(); i++) {
			cols += widths[S.charAt(i)-'a'];
			if(cols > 100) {
				rows ++;
				cols = widths[S.charAt(i)-'a'];
			}
		}
		int[] res = {rows+1, cols};
		return res;
	}

	public static void main(String[] args) {
		SolutionFor806 for806 = new SolutionFor806();
		int[] widths = {10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
		for806.numberOfLines(widths, "abcdefghijklmnopqrstuvwxyz");
	}

}
