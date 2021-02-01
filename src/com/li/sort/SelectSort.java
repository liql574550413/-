package com.li.sort;

import java.util.Arrays;

/**
 * @author liqiuliang
 * @version 1.0
 * @date 2021/2/1 0:44
 * 选择排序
 * 选择排序（select sorting）也是一种简单的排序方法。
 * 它的基本思想是：第一次从 arr[0]~arr[n-1]中选取最小值， 与 arr[0]交换，
 * 第二次从 arr[1]~arr[n-1]中选取最小值，与 arr[1]交换，
 * 第三次从 arr[2]~arr[n-1]中选取最小值，与 arr[2] 交换，…，
 * 第 i 次从 arr[i-1]~arr[n-1]中选取最小值，与 arr[i-1]交换，…,
 * 第 n-1 次从 arr[n-2]~arr[n-1]中选取最小值， 与 arr[n-2]交换，
 * 总共通过 n-1 次，得到一个按排序码从小到大排列的有序序列
 *
 * 时间复杂度也是 n的平方  ,但是速度比冒泡排序快的多，冒泡8w数据20s 选择排序只要3秒
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[]{41, 3, 63, 1};
       // selectSort(arr);
        System.out.println("交换前："+Arrays.toString(arr));
        SelectSort2(arr);
    }

    public static void SelectSort2(int[] arr){
        for (int i = 0; i <arr.length-1 ; i++) {
            //假设记录最小值得下标
            int minIndex=i;
            //记录最小值
            int min=arr[i];
            //第一轮比较 让下标为 0 的一次与后面的值比较, 首先与 下标为 1的进行比较
            for (int j = minIndex+1; j <arr.length ; j++) {
                if (min>arr[j]){//只记录 不交换 在后面做优化
                    min=arr[j];
                    minIndex=j;
                }
            }
            if (minIndex!=i){//交换
                arr[minIndex]=arr[i];
                arr[i]=min;
            }
            System.out.printf("第 %d 轮排序后 %s\n", i+1,Arrays.toString(arr));


        }
    }

    /**
     * 帮助理解版
     * @param arr
     */
    public static void selectSort(int[] arr){
      //  int[] arr = new int[]{41, 52, 63, 1};

        //记录最小值得下标 先假设第一个就为最小的
        int minIndex=0;
        //记录最小值
        int min=arr[minIndex];
        //第一轮比较 让下标为 0 的一次与后面的值比较, 首先与 下标为 1的进行比较
        for (int i = minIndex+1; i <arr.length ; i++) {
            if (min>arr[i]){//交换
                arr[minIndex]=arr[i];  //交换可以优化 做个判断，当需要交换时在交换
                arr[i]=min;
            }
        }
        System.out.printf("第一轮排序后 %s\n", Arrays.toString(arr));

        //第二轮排序  并优化交换
         minIndex=1;
         min=arr[minIndex];
        for (int i = minIndex+1; i <arr.length ; i++) {
            if (min>arr[i]){//交换
                /* 注释掉 在后面优化
                arr[minIndex]=arr[i];
                arr[i]=min;*/
                min=arr[i];
                minIndex=i;
            }
        }
        //判断第二轮是否需要交换
        if (minIndex!=1){
            arr[minIndex]=arr[1];  //把当前下标为1的值赋值给那个最小值下标的地方
            arr[1]=min;//把循环中记录的最小值 赋值给下标 1 处
        }
        System.out.printf("第二轮排序后 %s\n", Arrays.toString(arr));

        //第三轮轮排序
        minIndex=2;
        min=arr[minIndex];
        for (int i = minIndex+1; i <arr.length ; i++) {
            if (min>arr[i]){//交换
               /* 注释掉 在后面优化
                arr[minIndex]=arr[i];
                arr[i]=min;*/
                min=arr[i];
                minIndex=i;

            }
        }
        //判断第三轮是否需要交换
        if (minIndex!=2){
            arr[minIndex]=arr[2];  //把当前下标为1的值赋值给那个最小值下标的地方
            arr[2]=min;//把循环中记录的最小值 赋值给下标 1 处
        }
        System.out.printf("第三轮排序后 %s\n", Arrays.toString(arr));
    }
}
