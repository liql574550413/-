package com.li.queue;

import java.util.Scanner;

/**
 * @author liql
 * @date 2021/1/10
 * 用数组模拟环形队列
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args){
        //初始化一个队列
        CircleArrayQueue arrayQueue=new CircleArrayQueue(4);//有效数据只有3个 因为有一个预留空间作为约定
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
 * 思路如下:
 * 1. front 变量的含义做一个调整: front 就指向队列的第一个元素， 也就是说ar[front]就是队列的第一个元素
 * front的初始值=0
 * 2. rear 变量的含义做一-个调整: rear 指向队列的最后-一个元素的后一个位置.因为希望空出-一个空间做为约定.
 * rear的初始值=0
 * 3.当队列满时，条件是(rear + 1) % maxSize=front [满]
 * 4.对队列为空的条件，rear== front空
 * 5.当我们这样分析，队列中有效的数据的个数(rear+ maxsize - front) % maxSize // rear=1 front=0
 * 6.我们就可以在原来的队列上修改得到，-个环形队列
 */
class  CircleArrayQueue{
    private int maxSize;//表示数组的最大容量
    private int front;//队列头   front 就指向队列的第一个元素， 也就是说ar[front]就是队列的第一个元素
    private  int rear;//队列尾巴 rear 指向队列的最后一个元素的后一个位置.因为希望空出一个空间做为约定
    private int[] array; //数组 模拟队列

    //队列的构造器
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;//最大容量
        front=0;//指向队列头部，分析出front  front 就指向队列的第一个元素，
        rear=0;//指向队列尾，rear 指向队列的最后一个元素的后一个位置
        array=new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        //举例
        //front=0
        //rear=3
        //maxSize=4       (3+1)%4=0==front  刚好相等  为满
        return (rear+1)%maxSize==front;
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
        //队列未满  先添加 后移动，必须考虑取模
        array[rear]=n;//给队列赋值
        rear=(rear+1)%maxSize;
    }

    //取数据
    public int getQueue(){
        //先判断是否为空
        if (isEmpty()){
            //通过抛异常 结束程序，可以省略return来结束
            throw  new RuntimeException("队列为空");
        }
        //先取 后移动 考虑取模
        //这里需要分析出front 是指向队列的第一一个元素
        //1.先把front对应的值保留到-一个临时变量
        //2. 将front 后移,考虑取模
        //3.将临时保存的变量返回

       int v= array[front];
        front=(front+1)%maxSize;
        return v;
    }

    //显示队列所有数据
    public void  showQueue(){
        if (isEmpty()){
            System.out.println("队列没有数据，不能显示");
            return;
        }
        //遍历  用格式化的方法  考虑取模
        // 思路:从front开始遍历，遍历多少个元素
        for (int i=front;i<front+size();i++){
            //考虑取模
            /*if (array[i]==0){
                continue;
            }*/
            System.out.printf("array[%d]=%d\n",i%maxSize,array[i%maxSize]);
        }
    }
    //计算队列的有效数据
    public int size(){
        //举例
        //front=0
        //rear=3
        //maxSize=4       (3+4-0)%4=3  刚好有效数据为3
        return  (rear+maxSize-front)%maxSize;
    }


    //显示队列的头数数据  ，不是取数据 所以front不用移动
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("空，没有头数据");
        }

        return array[front];//因为front指向的是队列头的那一个
    }
}