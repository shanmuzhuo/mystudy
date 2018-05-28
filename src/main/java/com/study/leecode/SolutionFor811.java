package com.study.leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhenxuan.zhuo leecode - 811 Subdomain Visit Count
 * 没有通过submission ，但是比对了结果是正确的。？？？？？
 */
public class SolutionFor811 {
	public List<String> subdomainVisits(String[] cpdomains) {
		List<String> result = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < cpdomains.length; i++) {
			String[] regx = cpdomains[i].split(" ");
			map.put(regx[1], regx[0]);
			while (regx[1].contains(".")) {
				String temp = regx[1].split("\\.", 2)[1];
				if(map.containsKey(temp)) {
					int count = Integer.parseInt(regx[0]) + Integer.parseInt(map.get(temp));
					map.put(temp, count+"");
				}else {
					map.put(temp, regx[0]);
				}
				
				regx[1] = temp;
			}
		}
		
		for (String str : map.keySet()) {
			result.add(map.get(str) + " " +  str);
			System.out.println(map.get(str) + " " +  str);
		}

		return result;
	}

	public static void main(String[] args) {
		SolutionFor811 for811 = new SolutionFor811();
		String[] arrs = {"2777 nak.mkw.co","654 yaw.lmm.ca","3528 jyx.arz.us","3215 bll.hoh.network","408 tdt.zfz.network","3322 xhe.team" };
		for811.subdomainVisits(arrs);
	}

}
