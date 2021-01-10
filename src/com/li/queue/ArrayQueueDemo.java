package com.li.queue;

import java.lang.reflect.Array;
import java.util.Scanner;

/**
 * @author liql
 * @date 2021/1/10
 * 用数组模拟队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args){
        //初始化一个队列
        ArrayQueue arrayQueue=new ArrayQueue(3);
        char key=' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop=true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out. println("e(exit):退出程序");
            System.out. println("e(add;添加数据到队列");
            System.out. println("g(get):从队列取出数据");
            System.out. println("h(head):查看队列头的数据");
             key = scanner.next().charAt(0);

            //检查用户输入的key 调用对应的功能
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("添加数据");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g'://取数据 注意 它为空抛异常 处理
                    try {
                        int queue = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",queue);
                    } catch (Exception e) {
                     //   e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查看队列头数据 注意 它为空抛异常 处理
                    try {
                        int queue = arrayQueue.headQueue();
                        System.out.printf("队列的头数据是%d\n",queue);
                    } catch (Exception e) {
                        //   e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e'://退出
                    scanner.close();//关闭scanner
                    loop=false;//关闭循环
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

/**
 * 创建一个数组类 模拟队列
 * 目前这个队列不是环形队列 所以 散掉后不能再加
 * */
class  ArrayQueue{
    private int maxSize;//表示数组的最大容量
    private int front;//队列头  从-1开始
    private  int rear;//队列尾巴 也是从-1开始
    private int[] array; //数组 模拟队列

    //队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;//最大容量
        front=-1;//指向队列头部，分析出front 是指向队列头的前一个位置，
        rear=-1;//指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
        array=new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        return rear==maxSize-1;
    }
    //判断队列是否为空
    private boolean isEmpty(){
        return rear==front;
    }

    //添加数据
    public void addQueue(int n){
        //先判断队列是否满
        if (isFull()){
            System.out.println("队列已满");
            return;
        }
        //队列未满  rear 后移 因为它随着队列的添加而移动
        rear++;
        array[rear]=n;//给队列赋值
    }

    //取数据
    public int getQueue(){
        //先判断是否为空
        if (isEmpty()){
            //通过抛异常 结束程序，可以省略return来结束
            throw  new RuntimeException("队列为空");
        }
        //front与取数据有关 所以front后移
        front++;
        return array[front];
    }

    //显示队列所有数据
    public void  showQueue(){
        if (isEmpty()){
            System.out.println("队列没有数据，不能显示");
            return;
        }
        //遍历  用格式化的方法
        for (int i=0;i<array.length;i++){
            //过滤为0的
            if (array[i]==0){
                continue;
            }
            System.out.printf("array[%d]=%d\n",i,array[i]);
        }
    }

    //显示队列的头数数据  ，不是取数据 所以front不用移动
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("空，没有头数据");
        }

        return array[front+1];//因为front指向的是队列头的前一个，所以取头 要加+
    }
}