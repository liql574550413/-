package com.li.recursion;

/**
 * @author liqiuliang
 * @version 1.0
 * @date 2021/1/28 16:10
 * 递归
 */
public class RecursionTest {
    public static void main(String[] args) {
        System.out.println(test(20));
    }
    public static long test(int s){
        if (s<1){
            return 1;
        }

        return test(s-1)*s;
    }
}
