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
       /*  演示死路回溯 放开这俩
       map[2][3]=1;
        map[1][3]=1;
        */
       //输出地图的情况
        System.out.println("---地图的情况---");
        for (int i=0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

        //让小球找路 从 map[1][1] 开始走

        setWay(map, 1, 1);
        //打印走过后的地图
     //   System.out.printf("走过的步数为：%d",res);
        System.out.println("---走过之后地图的情况---");
        for (int i=0;i<8;i++){
            for (int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

    }

    //使用递归回溯来给小球找路
    // 说明
    // 1. map 表示地图
    // 2. i,j 表示从地图的哪个位置开始出发 (1,1)
    // 3. 如果小球能到 map[6][5] 位置，则说明通路找到.
    // 4. 约定： 当 map[i][j]
            // 为 0 表示该点没有走过
            // 当为 1 表示墙 ；
            // 2 表示通路可以走 ；
            // 3 表示该点已经 走过，但是走不通
    //5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯
    /**
     *
     * @param map  表示地图
     * @param i
     * @param j   int[i][j] 表示从哪里开始走
     * @return
     */
    public static boolean setWay(int[][] map,int i,int j ){
       // res++;
        if (map[6][5]==2){//说明 当前小球已经走到了终点
            return true;
        }else {//还没有找到终点 接下来按策略走
            if (map[i][j] == 0) {//表示当前小球还没走到这个格子
                //假设这个格子能走的通 给这个格子置为2，然后去找下一条路 如果找不到下调路，
                // 则说明这个格子怒能走通 ，再把这个格子从2置为3，表示当前走的这条路为死路，然后 回溯
               //原理 会验证下一步走的格子是不是死路 是死路则返回false 则会验证下一个if语句
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay(map, i - 1, j)) { //向上
                    return true;
                } else if (setWay(map, i, j - 1)) { // 向左走
                    return true;
                }else {//表示 上下左右都走不通 把当前路置为3 表示此路不通
                    map[i][j]=3;
                    return false;
                }

            }else {//map[i][j]!=0 的情况， 即为 1墙  2走过  3死路
                return false;

            }



        }
    }
}
