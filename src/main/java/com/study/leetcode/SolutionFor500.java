package com.study.leetcode;

/**
 * @author zhenxuan.zhuo
 * 500. Keyboard Row
 */
public class SolutionFor500 {
	
	public String[] findWords(String[] words) {
		String[] keyboard = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
		for (int i = 0; i < words.length; i++) {
			words[i].toLowerCase();
		}
        
		return null;
    }

	public static void main(String[] args) {
		SolutionFor500 for500 = new SolutionFor500();
		String[] words = {"Hello", "Alaska", "Dad", "Peace"};
		for500.findWords(words);
	}
}
