package com.li.stack;

import java.util.Stack;

/**
 * @author liql
 * @date 2021/1/17
 * 演示栈的使用
 */
public class UseStack {
    public static void main(String[] args){
           Stack< Integer> stack=new Stack<>();
           //压栈也可以用add
           stack.push(1);
           stack.add(2);
           stack.push(3);
           stack.push(4);

           //出栈用while 因为栈的下标不是从0开始 用for循环会很麻烦
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
