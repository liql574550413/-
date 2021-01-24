package com.li.stack;


import java.util.Scanner;

/**
 * @author liql
 * @date 2021/1/24
 */
public class ArrayStackDemo {
    public static void main(String[] args){

        //测试ArrayStack
        ArrayStack stack=new ArrayStack(4);
        String key="";
        boolean loop=true;
        Scanner scanner = new Scanner(System.in);

        while (loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push：入栈");
            System.out.println("pop：出栈");
            System.out.println("请输入你的选择");
            key=scanner.next();//扫描器
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int v=scanner.nextInt();
                    stack.push(v);
                    break;
                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.printf("出栈的数据%d\n",pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }

        }
        System.out.println("程序退出了");
    }
}

//定义一个 ArrayStack 表示栈
class ArrayStack{
    private  int maxSize;//栈的大小
    private int[] stack;//数组 模拟栈
    private int top=-1;//栈顶  初始化为-1 表示没有数据

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack=new int[this.maxSize];//初始化数组的大小
    }

    //栈满
    public boolean isFull(){
        return top==maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return  top==-1;
    }

    // 入栈 push
    public void push(int value){
        //先判断栈是否满
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }

    //出栈 pop  将栈顶的数据返回
    public int pop(){
        //先判断是否为空
        if (isEmpty()){
         //   System.out.println("空");
            //抛出异常来处理
            throw new RuntimeException("栈空，没有数据");
        }

        int value=stack[top];
        top--;
        return value;
    }

    //显示栈的情况，遍历栈 ，需要从栈顶开始显示数据。
    public void list(){
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}