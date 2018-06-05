package com.study.leetcode;

/**
 * @author zhenxuan.zhuo
 * 476. Number Complement
 */
public class SolutionFor476 {
	
	public int findComplement(int num) {
		String s = Integer.toBinaryString(num);
		int t = (int) (Math.pow(2, s.length()) - 1);
		System.out.println( Integer.lowestOneBit(num) -1);
		return t - num;
    }

	public static void main(String[] args) {
		SolutionFor476 for476 = new SolutionFor476();
		for476.findComplement(5);
	}

}
