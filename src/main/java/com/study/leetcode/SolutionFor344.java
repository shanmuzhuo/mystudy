package com.study.leetcode;

/**
 * @author zhenxuan.zhuo
 *	344 Reverse String
 */
public class SolutionFor344 {
    public String reverseString(String s) {
    	char c[] = s.toCharArray();
    	StringBuilder res = new StringBuilder();
    	long start = System.nanoTime();
    	for (int i = 0 ; i < c.length ; i++) {
			res.append(c[c.length - 1 - i]);
			
/*			String es = ""; 这样虽然也能够得到结果，但是时间太久了，每一次的 += 操作都会在常量池中创建一个数组，影响效率
			es += c[c.length - 1 - i];*/
		}
    	return res.toString();
    }
	
	public static void main(String[] args) {
		SolutionFor344  for344 = new SolutionFor344();
		for344.reverseString("\"A man");
	}

}
