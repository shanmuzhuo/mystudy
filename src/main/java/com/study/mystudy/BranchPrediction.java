package com.study.mystudy;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhenxuan.zhuo
 * @desc 分支预测的问题，见于stackoverflow,
 * https://stackoverflow.com/questions/11227809/why-is-it-faster-to-process-a-sorted-array-than-an-unsorted-array#
 * 详细的中文解释：
 * https://blog.csdn.net/kiyoki/article/details/52620366
 */
public class BranchPrediction {
	public static void main(String[] args) {
		int size = 32768;
		int data[] = new int[size];
		Random random = new Random(0);
		
		for (int i = 0; i < data.length; ++i) 
			data[i] = random.nextInt() % 265;
		
//		Arrays.sort(data); // 排序与不排序对分支预测的结果影响较大
		
		long startime = System.nanoTime();
		long sum = 0;

        for (int i = 0; i < 100000; ++i)
        {
            for (int c = 0; c < size; ++c)
            {
                if (data[c] >= 128)  // 这个IF就是造成时间延长的关键。因为不确定性，分支预测猜错的概率较大，指定处于多次减速-热启动的过程中，耗时巨大。
                    sum += data[c];
                
                //优化方案，用位运算优化。将分支判断改为： 此时排序与不排序对结果影响不大。 在大量的逻辑循环中应该避免数据强依赖的分支。
               /* int t = ( data[c] - 128 ) >> 31; //负数右移31为一定为0xffff = -1 , 非负数右移31为一定为0
        		sum += ~t & data[c] ; // 0取反为-1 ,-1取反为0 . ~0 & data == -1 & data == 0xffff & data == data; 0 & data == 0
*/                
            }
        }

        System.out.println((System.nanoTime() - startime) / 1000000000.0);
        System.out.println("sum = " + sum);
	}
}
