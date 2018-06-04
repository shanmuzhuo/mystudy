package com.study.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenxuan.zhuo
 * @describe Given a string S and a character C, 
 * return an array of integers representing the shortest distance from the character C in the string.
 */
public class SolutionFor821 {
	public int[] shortestToChar(String S, char C) {
		int[] res = new int[S.length()];

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < S.length(); i++) {
			if(S.charAt(i) == C) {
				list.add(i);
			}
		}
		
		for (int i = 0; i < S.length(); i++) {
			int temp = 0;
			for (int j = 0; j < list.size(); j++) {
				if(j == 0) {
					temp = Math.abs(i - list.get(j));
				}
				if(temp > Math.abs(i - list.get(j))) {
					temp = Math.abs(i - list.get(j));
				}
			}	
			res[i] = temp;
		}
		
		for (int i : res) {
			System.out.print("   " + i);
		}
		return res;
    }

	public static void main(String[] args) {
		SolutionFor821 for821 = new SolutionFor821();
		for821.shortestToChar("aaab",'b');

	}

}
