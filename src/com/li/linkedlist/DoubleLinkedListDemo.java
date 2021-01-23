package com.li.linkedlist;

/**
 * @author liql
 * @date 2021/1/17
 * ➢管理单向链 表的缺点分析:
 * 1)单向链表，查找的方向只能是一个方向，而双向链表可以向前或者向后查找。
 * 2)单向链表不能自我删除，需要靠辅助节点，而双向链表，则可以自我删除，所以前面我们单链表删除
 * 时节点，总是找到temp,temp是待删除节点的前一个节点(认真体会).
 * 3)分析了双向链表如何完成遍历，添加，修改和删除的思路
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args){

    }
}

class DoubleLinkedList{
    //先初始化一个头结点 不要动 动了以后就找不到其它节点了  不存放具体的数据
    private HeroNode2 head=new HeroNode2(0, "","");
    //返回一个头结点
    public HeroNode2 getHead() {
        return head;
    }

    //显示双向链表 遍历
    public void list(){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }

        //因为头节点，不能动，因此我们需要- - 个辅助变量来遍历
        HeroNode2 temp=head.next;
        while (true){
            //判断是否到链表最后
            if (temp==null){
                break;
            }
            //不是最后 就输出节点信息 并将temp后移 一定要小心
            System.out.println(temp);
            temp=temp.next;

        }
    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode){
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp=head;
        //遍历链表找到链表的最后
        while (true){
            //next域为空 退出循环，不为空 将temp 后移
            if (temp.next==null){
                break;
            }
            //如果没有找到最后，将将temp后移
            temp=temp.next;
        }
        //当退出while 循环时，temp 就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next=heroNode;
        heroNode.pre=temp;
    }


    //双向链表的修改  与单向链表一样  都是先找到改节点
    public void update(HeroNode2 newHeroNode){
        //首先检查节点是否为空
        if (head.next==null){
            System.out.println("节点为空，不能跟新");
            return;
        }

        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp=head.next;
        boolean flag=false;//表示是否找到该节点
        while (true){
            if (temp==null){//说明已经遍历到链表尾部
                break;//遍历完链表
            }
            if (temp.no==newHeroNode.no){
                //找到
                flag=true;
                break;
            }
            temp=temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if(flag){
            //更新节点的值为新节点的值
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;
        }else {
            System.out.printf("没有找到编号为%d 的节点，不能修改\n",newHeroNode.no);
        }
    }

    //从双向链表中删除- -个节点,
    //说明
    //1对于双向链表，我们可以直接找到要删除的这个节点
    //2找到后，自我删除即可
    public void delete(int no){
        //判断当前链表是否为空
        if (head.next==null){
            System.out.println("链表为空，无法删除");
            return;
        }

        //辅助变量
        HeroNode2 temp=head;
        boolean flag=false;//标志是否找到被删除的节点
        //变量查找
        while (true){
            if (temp==null){//遍历完链表
                break;
            }
            if (temp.no==no){//找到待删除节点的 前一个节点 temp
                flag=true;
                break;
            }
            //后移 继续遍历
            temp=temp.next;
        }

        //判断是否找到
        if (flag){//找到
            //删除  把节点移到后一个 ，当它不被指向的时候 ， 就会被垃圾回收机制回收
         //   temp.next=temp.next.next;  这是单向链表的删除
            //双向链表的删除
            temp.pre.next=temp.next;
          /*
           //下面这个代码是youbug的 ，因为如果要删除的当前节点为最后一个  则没有 temp.next.pre
                会报空指针异常，所以 如果要是最后一个节点 则不需要这个代码
            temp.next.pre=temp.pre;
            */
          if (temp.next!=null){
              temp.next.pre=temp.pre;
          }

        }else {
            System.out.printf("没有找到带删除的%d 几点",no);
        }
    }



}

//定义HeroNode2  每个HeroNode2 对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public  String nickname;
    public HeroNode2 next; //指向下一个节点
    public HeroNode2 pre; //指向上一个节点  默认为空

    //构造器


    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}