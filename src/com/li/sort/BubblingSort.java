package com.li.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @author liql
 * @date 2021/1/31
 * 冒泡排序
 * 冒泡排序（Bubble Sorting）的基本思想是：通过对待
 * 排序序列从前向后（从下标较小的元素开始）,依次比较
 * 相邻元素的值，若发现逆序则交换，使值较大
 * 的元素逐渐从前移向后部，就象水底下的气泡一样逐渐
 * 向上冒。
 *
 * 因为排序的过程中，各元素不断接近自己的位置，如果一趟比较下
 * 来没有进行过交换，就说明序列有序，因此要在排序过程中设置
 * 一个标志flag判断元素是否进行过交换。从而减少不必要的比较。(这里说的优化，可以在冒泡排序写好后，在进行)
 *
 * (1) 一共进行 数组的大小-1 次 大的循环
 * (2)每一趟排序的次数在逐渐的减少
 * (3) 如果我们发现在某趟排序中，没有发生一次交换， 可以提前结束冒泡排序。这个就是优化
 */
public class BubblingSort {
    public static void main(String[] args){
       // int[] ints = {0, -2, 300, 50, -78, 35, -90, 121};
     //   int[] ints2 = {0, -2, 300, 50, -78, 35, -90, 121};
        int[] ints = {0, 1,2,3,4,5,6};
        int[] ints2 = {0, 1,2,3,4,5,6};
        sort(ints);
        System.out.println("------优化后的-----");
        optimization(ints2);

        //测试10w个数排序耗时
        int[] arr=new int[80000];
        for (int i = 0; i <80000 ; i++) {
            arr[i]=(int) (Math.random()*80000);//[0,100000)之间的随机数
        }


        //格式化时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date11 = simpleDateFormat.format(date1);
        System.out.println(date11);
        optimization(arr);
        Date date2 = new Date();
        String date22 = simpleDateFormat.format(date2);

        System.out.println(date22);
    }


    public static void sort(int[] ints){
        int count=0;
        System.out.println("排序前："+ Arrays.toString(ints));
        //临时变量 用于交换
        int temp=0;
        for (int i = 0; i <ints.length-1 ; i++) {
            System.out.println("这是外排序的第 "+i+" 次排序");
            for (int j = 0; j < ints.length-1-i; j++) {
                if (ints[j]>ints[j+1]){
                    temp=ints[j];
                    ints[j]=ints[j+1];
                    ints[j+1]=temp;
                }
                System.out.println("这是第 " + (i+1)+ " 次外排序的第 "+(j+1)+" 内排序");
                System.out.println(Arrays.toString(ints));
                count++;
            }

        }
        System.out.printf("共进行了%d 次排序\n",count);
    }
    /**
     *  优化的冒泡排序算法  当一个外层大循环没有发生一次交换时  则说明已经完成排序，可以终止。
     * @param ints
     */
    public static void optimization(int[] ints){
        int temp = 0;
        int count2=0;
        boolean flag = false;//表示知否发生过交换顺序 ，有交换过则置为true，
        for (int i = 0; i < ints.length - 1; i++) {
          //  System.out.println("这是外排序的第 " + i + " 次排序");
            for (int j = 0; j < ints.length - 1 - i; j++) {
                if (ints[j] > ints[j + 1]) {
                    flag = true;
                    temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                }
          //      System.out.println("这是第 " + (i + 1) + " 次外排序的第 " + (j + 1) + " 内排序");
         //       System.out.println(Arrays.toString(ints));
                count2++;
            }

            //判断是否交换过
            if (!flag) { //没交换过 进来 ！falase   可以进来
                break;//退出循环
            } else {
                flag = false;//继续循环
            }
        }

     //   System.out.printf("共进行了%d 次排序",count2);
    }
}
