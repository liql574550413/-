package com.li.sort;

import java.util.Arrays;

/**
 * @author liqiuliang
 * @version 1.0
 * @date 2021/2/5 22:32
 * 7.8.2希尔排序法介绍 希尔排序是希尔（Donald Shell）于 1959 年提出的一种排序算法。
 * 希尔排序也是一种插入排序，它是简单插入
 *  排序经过改进之后的一个更高效的版本，也称为缩小增量排序。
 *
 *  7.8.3希尔排序法基本思想 希尔排序是把记录按下标的一定增量分组，
 *  对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含 的关键词越来越多，
 *  当增量减至 1 时，整个文件恰被分成一组，算法便终止.
 *
 *  有一群小牛, 考试成绩分别是 {8,9,1,7,2,3,5,4,6,0} 请从小到大排序. 请分别使用
 *  1) 希尔排序时， 对有序序列在插入时采用交换法, 并测试排序速度.
 *  2) 希尔排序时， 对有序序列在插入时采用移动法, 并测试排序速度
 *
 *  shell 交换法8w数据要20s左右，比其它都要长
 *  shell 移位法  8w数据2s左右
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr=new int[]{10,6,-2,8,-11,9,-5,60,30,20};
        System.out.println("交换排序前："+ Arrays.toString(arr));
       // shellSortProcess(arr);
       // shellSort(arr);
        shellMove(arr);
        System.out.println("移位排序后："+ Arrays.toString(arr));
    }
    //交换法 推导过程
    // 希尔排序时， 对有序序列在插入时采用交换法
    public static void shellSortProcess(int[] arr){
        //第一轮排序，除以2 将10个数据除以5组  步长为5 ，分成几组步长就为几
        int temp=0;
        //i=5 是因为共有5组 i从5开始 刚好可以循环5次（也就是5组数的量）
        //或者也可以理解为每组的下标较大的哪个 ，同组的另一个数 就是下标减去5
        for (int i = 5; i <arr.length ; i++) {
            //这个循环是用来遍历每组中的两个数，下标较小的那个数的，和下标加5（步长）的那个数
            //j=j-5 该组只有两个数的没有体现出作用，当每组有3个4个数的时候，就能找到第三个第四个数了
            for (int j = i-5; j >=0 ; j=j-5) {
                //如果当前元素[j] 大于[j+5]的元素 ，则交换
                    if (arr[j]>arr[j+5]){
                        temp=arr[j];
                        arr[j]=arr[j+5];
                        arr[j+5]=temp;
                    }
            }
        }
        System.out.println("第一轮交换排序后："+ Arrays.toString(arr));

        //第二轮处理
        //在原先分组的情况下再除以2 也就是length/4  =2组（只保留整数），步长为2
        for (int i = 2; i <arr.length ; i++) {
            //这个循环是用来遍历每组中的两个数，下标较小的那个数的，和下标加2（步长）的那个数
            //j=j-2 该组只有两个数的没有体现出作用，当每组有3个4个数的时候，就能找到第三个第四个数了
            for (int j = i-2; j >=0 ; j=j-2) {
                //如果当前元素[j] 大于[j+5]的元素 ，则交换
                if (arr[j]>arr[j+2]){
                    temp=arr[j];
                    arr[j]=arr[j+2];
                    arr[j+2]=temp;
                }
            }
        }
        System.out.println("第二轮交换排序后："+ Arrays.toString(arr));

        //第三轮处理
        //在原先分组的情况下再除以2 也就是length/2/2/2  =1组（只保留整数），步长为1
        for (int i = 1; i <arr.length ; i++) {
            //这个循环是用来遍历每组中的两个数，下标较小的那个数的，和下标加2（步长）的那个数
            //j=j-2 该组只有两个数的没有体现出作用，当每组有3个4个数的时候，就能找到第三个第四个数了
            for (int j = i-1; j >=0 ; j=j-1) {
                //如果当前元素[j] 大于[j+5]的元素 ，则交换
                if (arr[j]>arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        System.out.println("第二轮交换排序后："+ Arrays.toString(arr));
    }
    //对上面的方法进行一个提取  有3层循环
    public static void shellSort(int[] arr) {

        int temp = 0;
        int count = 0;//用来记录轮数
        //gap是步长 每一次分组（步长）都是在原先的基础上除以2
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j > 0; j = j - gap) {
                    //如果当前元素[j] 大于[j+gap]的元素 ，则交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("第" + (++count) + "轮交换排序后：" + Arrays.toString(arr));
        }
    }

    //shell排序 移位法  有两层循环
    public static void shellMove(int[] arr){

        int count = 0;//用来记录轮数
        //gap是步长 每一次分组（步长）都是在原先的基础上除以2
        // 增量 gap, 并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第 gap 个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;//记录待插入的下标
                int temp = arr[j];//记录带插入的数

                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //说明没有找到 ，待插入的数比他前面差距一个步长的数小， 把这个数前移
                        arr[j] = arr[j - gap];
                        j = j - gap;
                    }
                    //当退出 while 后，就给 temp 找到插入的位置
                    arr[j] = temp;
                }
            }
            //System.out.println("第" + (++count) + "轮交换排序后：" + Arrays.toString(arr));
        }
    }
}
