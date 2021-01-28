package com.li.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author liqiuliang
 * @version 1.0
 * @date 2021/1/28 11:27
 * 将中缀表达式转成逆缀表达式
 *
 * 5.7 中缀表达式转换为后缀表达式
 * 大家看到，后缀表达式适合计算式进行运算，但是人却不太容易写出来，尤其是表达式很长的情况下，
 * 因此在开发 中，我们需要将 中缀表达式转成后缀表达式。
 * 5.7.1具体步骤如下:
 * 1) 初始化两个栈：运算符栈 s1 和储存中间结果的栈 s2；
 * 2) 从左至右扫描中缀表达式；
 * 3) 遇到操作数时，将其压 s2；
 * 4) 遇到运算符时，比较其与 s1 栈顶运算符的优先级：
 *      1.如果 s1 为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
 *      2.否则，若优先级比栈顶运算符的高，也将运算符压入 s1；
 *      3.否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到(4.1)与 s1 中新的栈顶运算符相比较；
 *  5) 遇到括号时：
 *      (1) 如果是左括号“(”，则直接压入 s1
 *      (2) 如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
 *  6) 重复步骤 2 至 5，直到表达式的最右边
 *  7) 将 s1 中剩余的运算符依次弹出并压入 s2
 *  8) 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
 */

public class PolandNotation2 {
    public static void main(String[] args) {
        //将中缀表达式“1+((2+3)×4)-5”转换为后缀表达式的过程如下
        // 因此结果为 :"1 2 3 + 4 × + 5 –"
        //2. 因为直接对 str 进行操作，不方便，因此 先将 "1+((2+3)×4)-5" =》 中缀的表达式对应的 List
        // 即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        String str="1+((2+3)*4)-5";
        List<String> list= toInfixExpressionList(str);
        System.out.println(list);

        //转成逆波兰表达式：即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] =》 ArrayList [1,2,3,+,4,*,+,5,–]
        // 方法：将得到的中缀表达式对应的 List => 后缀表达式对应的 List
        System.out.println(toSuffixExpression(list));
    }

    public static List<String> toInfixExpressionList(String s){
        int i=0;//当做指针 用来记录臊面的字符串的下标
        char c=' ';//用来记录扫描的那个字符
        String str;//当扫描的是多位数子的时候用来拼接多为数字
        ArrayList<String> list = new ArrayList<>();//用来存中缀表达式
        //数字对应的ascII 码的范围是[48,57]
        do {
            //当遍历到的字符是非数字时，直接添加到list中
            if ((c=s.charAt(i))<48||(c=s.charAt(i))>57){
                list.add(""+c);
                i++;
            }else {
                //当遍历到的是数字时  注意处理多位数
                str="";//先初始化这个 避免上次遍历到的数字印象这次的结果
                while (i<s.length()&&(c=s.charAt(i))>=48&&(c=s.charAt(i))<=57){
                    str +=c;
                    i++;
                }
                list.add(str);
            }

        }while (i<s.length());

        return list;
    }

    //将中缀表达式转成后缀表达式的方法
    public static List<String> toSuffixExpression(List<String> list){
        //定义两个栈
        Stack<String> s1 = new Stack<String>();
        // 符号栈 //说明：因为 s2 这个栈，在整个转换过程中，没有 pop 操作，而且后面我们还需要逆序输出
        // 因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        // Stack<String> s2 = new Stack<String>();
        // 储存中间结果的栈 s2
        List<String> s2 = new ArrayList<String>(); // 储存中间结果的 List s2
        //处理中缀表达式
        for (String s : list) {
            if (s.matches("\\d+")) {
                //匹配操作数 直接亚茹到s2中
                s2.add(s);
            } else if (s.equals("(")) {
                //匹配到是 右括号直接压入到s1中
                s1.push(s);
            } else if (s.equals(")")) {
                //匹配到左括号后  弹s1运算符至s2中 直到遇到右括号为止
                while (!s1.peek().equals("(")) {
                    //把不是左括号的都从s1压到s2中
                    s2.add(s1.pop());
                }
                //当前匹配到的s1.peek()为右括号 丢弃
                s1.pop();
            } else {//匹配到运算符
                // 遇到运算符时，比较其与s1栈顶运算符的优先级：
                //如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
                // 否则，若优先级比栈顶运算符的高，也将运算符压入s1；
                // 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较
                /*
                总结：新的运算优先级的优先级大于栈顶的优先级或者括号 就入操作符栈s1
                        所以把括号优先级设置小于运算符  就可以了。
                     当peek到括号时，括号的优先级返回的是0，加减乘除的优先级别都大于零 所以也会入栈，
                     */
                while (s1.size() != 0 && Operation.getOper(s) <= Operation.getOper(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.push(s);
            }

        }


        //将 s1 中剩余的运算符依次弹出并加入 s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }


}


//写一个类 用来放 比较优先级的方法
class Operation{
    //分别对应加减乘除
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;
    //返回对应的优先级的顺序
    public static int getOper(String oper){
        int result=0;
        switch (oper){
            case "+":
             result=ADD;
             break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("运算符有误");
                break;
        }
        return result;
    }

}