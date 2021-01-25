package com.li.stack;


import java.util.Scanner;

/**
 * @author liql
 * @date 2021/1/24
 * 使用栈完成表达式的计算思路
 * 1.通过一个index值(索引) , 来遍历我们的表达式
 * 2.如果我们发现是一个数字, 就直接入数栈
 * 3.如果发现扫描到是一一个符号，就分如下情况
 * 3.1如果发现当前的符号栈为空，就直接入栈
 * 3.2如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中
 * 的操作符，就需要从数栈中pop出两个数在从符号栈中pop出一个符号，进行运算,
 * 将得到结果，入数栈，然后将当前的操作符入符号栈，如果当前的操作符的优先级大
 * 于栈中的操作符，就直接入符号栈.
 * 4.当表达式扫瞄完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行.
 * 5.最后在数栈只有一个数字， 就是表达式的结果
 * 验证: 3+2*6-2=13
 */
public class Calculator {
    public static void main(String[] args){
        //完成表达式的运算
        String expression="4+2*6-2";
        //创建两个栈  数栈 和一个 符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack=  new ArrayStack2(10);

        //定义需要的相关变量
        int index=0;//用于扫描
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch=' ';//将每次扫描到的char保存到ch
        //开始while循环扫描expression
        while (true){
            //一次得到每个expression字符
            ch=expression.substring(index,index+1).charAt(0);
            //判断ch是什么 然后做响应的处理
            if (operStack.isOper(ch)){//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符,就需要从数栈中pop出两个数。
                    //在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if ( operStack.priority(ch)<=operStack.priority(operStack.peek())) {
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=numStack.cal(num1, num2, oper);
                        //把运算的结果入数栈  然后把当前的符号入符号栈
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈。
                        operStack.push(ch);
                    }

                }else {//当前符号栈为空  直接入符号栈
                    operStack.push(ch);
                }
            }else { //当前扫描的结果是数字  直接入数栈
                //注意 这时候的是字符型的数字  所以要转化成数字型的 数字，ascII码相差48
                numStack.push(ch-48);
            }
            //让index +1 ，并判断是否扫码到expression最后
            index++;
            if (index >=expression.length()) {
                break;//
            }

        }

        //当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出响应的数和符号 ，并运行
        while (true){
            if (operStack.isEmpty()){
                break;//符号栈为空 停止
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1, num2, oper);
            //把运算的结果入数栈  然后把当前的符号入符号栈
            numStack.push(res);
        }
        //将数栈的最后的数pop出来  这就是最终结果
      int  res2= numStack.pop();
        System.out.printf("表达式%s =%d",expression,res2);
    }
}

//定义一个 ArrayStack 表示栈
class ArrayStack2{
    private  int maxSize;//栈的大小
    private int[] stack;//数组 模拟栈
    private int top=-1;//栈顶  初始化为-1 表示没有数据

    public ArrayStack2(int maxSize) {
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
    //查看栈顶的数据，不是pop出栈
    public int peek(){
        return stack[top];
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

    //返回运算符的优先级，优先级使用数字表示 数字越大，优先级越高
    //目前认为运算符只有加减乘除
    //char字符也能用int传？
    public  int  priority(int oper){
        if (oper == '*'||oper=='/') {
            return 1;
        }else if (oper == '+'||oper=='-'){
            return 0;
        }else {
            return -1;
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val){
        return val=='+'||val=='/'||val=='-'||val=='*';
    }
    //计算方法
    public int cal(int num1,int num2,int oper){
        int res=0;
        switch (oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1;//注意顺序 是谁减谁
                break;
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num2/num1;
                break;
            default:
                break;
        }
        return res;
    }
}