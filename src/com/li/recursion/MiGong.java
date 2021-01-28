package com.li.recursion;

import java.util.Arrays;

/**
 * @author liql
 * @date 2021/1/28
 * 递归解决迷宫问题  小球找出路
 *
 * 递归需要遵守的重要规则
 * 1、执行一个方法时，就创建一个新的受保护的独立空间(栈空间)
 * 2、方法的局部变量是独立的，不会相互影响, 比如n变量
 * 3、如果方法中使用的是引用类型变量(比如数组)，就会共享该引用类型的数据.
 * 4、递归必须向退出递归的条件逼近，否则就是无限递归,出现StackOverflowError，死龟了:)
 * 5、当一个方法执行完毕，或者遇到return，就会返回，遵守谁调用，就将结果返回给谁，同时当方法执行完毕或者返回时，
 *      该方法也就执行完毕。
 */
public class MiGong {
    public static void main(String[] args){
        //创建一个数组模拟迷宫
        int[][] map=new int[8][7];
        //把数组的四周置为 1  表示墙壁
        for (int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        for (int i=0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        map[3][1]=1;
        map[3][2]=1;
       //输出地图的情况
        System.out.println("---地图的情况---");
        for (int i=0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

    }
}
