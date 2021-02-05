package com.li.sort;

import java.util.Arrays;

/**
 * @author liqiuliang
 * @version 1.0
 * @date 2021/2/2 22:06
 * 插入排序
 *
 * 7.7 插入排序 7
 * .7.1插入排序法介绍:
 *      插入式排序属于内部排序法，是对于欲排序的元素以插入的方式找寻该元素的适当位置，
 *      以达到排序的目的。
 * 7.7.2插入排序法思想: 插入排序（Insertion Sorting）的基本思想是：
 *          把 n 个待排序的元素看成为一个有序表和一个无序表，开始时有 序表中只包含一个元素，
 *          无序表中包含有 n-1 个元素，排序过程中每次从无序表中取出第一个元素，
 *          把它的排 序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，
 *          使之成为新的有序表。
 *          8w 条数据大概是4~5秒
 *  弊端： 如果是 2 3 4 5 1， 插入会做很多浪费的步骤，shell排序解决了这种问题
 */
public class InsertSort {
    public static void main(String[] args) {
            int[] arr=new int[]{8,7,5,6,2,9};
            insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void  insertSort(int[] arr){
        //从下标为1 也就是第二个开始判断，判断第二个数据要不要移动到第一个的前面，
        for (int i = 1; i < arr.length; i++) {
            //把当前需要排序的的这个数字记录下来
            int value=arr[i];
            //用来记录下标,起始是 当前排序的前一个 ，然后每比较一次 前移一次
            int index=i-1;
            //开始判断  先于前面第一个判断  ，再与前面第二个 第三个 第四个....
            //index>0 防止下标前移后越界
            //value>arr[index] 下标前移的条件 ，当前值小于它前面的
            while (index>=0&& value<arr[index]){ //进来 说明还没赵傲要插入的位置
                //让与当前这个vulae比较的这个值后移
                arr[index+1]=arr[index];
                index--;
            }
            //出while循环 说明已经找到了插入的位置 当前index记录的下标的数比value小
            //所以value应该插入到index后面，所以是 index+1
            //增加的这个if 对性能并没有什么提升。
            if (index + 1 !=i) {//说明不用动 ，value的当前位置就是需要放的位置
                arr[index+1]=value;
            }

        }

    }
}
