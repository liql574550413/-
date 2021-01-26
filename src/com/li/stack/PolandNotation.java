package com.li.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author liql
 * @date 2021/1/26
 * 我们完成一个逆波兰计算器，要求完成如下任务:
 * 1) 输入一个逆波兰表达式(后缀表达式)，使用栈(Stack), 计算其结果
 * 2) 支持小括号和多位数整数，因为这里我们主要讲的是数据结构，因此计算器进行简化，只支持对整数的计算。
 * 3) 思路分析
 * 例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
 * 1．从左至右扫描，将 3 和 4 压入堆栈；
 * 2．遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
 * 3．将 5 入栈；
 * 4．接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈；
 * 5．将 6 入栈；
 * 6．最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
 */

public class PolandNotation {
    public static void main(String[] args){
        //先定义一个逆波兰表达式
        ////(30+4)×5-6 => 30 4 + 5 × 6 - => 164 为了方便中间用空格隔开
        String suffixExpression="30 4 + 5 * 6 -";
        //1. 先将 "3 4 + 5 × 6 - " => 放到 ArrayList 中
        // 2. 将 ArrayList 传递给一个方法，遍历 ArrayList 配合栈 完成计算
        List<String> rpnList = new ArrayList<>();
        rpnList=getListString(suffixExpression);
        System.out.println(rpnList);
        int calculate = calculate(rpnList);
        System.out.printf("公式 %s 的运算结果为 %d",suffixExpression,calculate);
    }

    ////将一个逆波兰表达式， 依次将数据和运算符 放入到 ArrayList 中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression进行分割
        String[] split=suffixExpression.split(" ");
       List<String> list = new ArrayList<>();
        for (String item:split) {
            list.add(item);
        }
        return list;
    }

//完成对逆波兰表达式的运算
 /**
  * 1)从左至右扫描，将 3 和 4 压入堆栈；
  * 2)遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
  * 3)将 5 入栈；
  * 4)接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈；
  * 5)将 6 入栈；
  * 6)最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
  * */
 public static  int calculate(List<String> list){
     //只需要一个栈即可 因为符号扫描的时候不入栈， 是从栈中弹出两个数进行运算
     Stack<String> stack = new Stack<String>();
     //b遍历list
     for (String item:list) {
         //使用正则表达式取出
         if (item .matches("\\d+")) {//这个符号匹配的是多位数字
               //入栈
             stack.push(item);
         }else {//当不是数字  也就是匹配到了运算符时
             //pop出连个数 计算后 在入栈
             int num1=Integer.valueOf(stack.pop());
             int num2=Integer.valueOf(stack.pop());
             int res=0;
                switch (item){
                    case "+":
                      res=num1+num2;
                      break;
                    case "-":
                        res=num2-num1;//先弹出来的是减数
                        break;
                    case "*":
                        res=num1*num2;
                        break;
                    case "/":
                        res=num2/num1;//先弹出来的是除数
                        break;
                    default:
                        throw new RuntimeException("运算符有误");

                }
                //把res入栈
               stack.push(res+"");
         }
     }
     //最后留在栈中的就是运算结果
     return Integer.valueOf(stack.pop());
 }
}
