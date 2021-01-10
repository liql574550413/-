package com.li.linkedlist;

/**
 * @author liql
 * @date 2021/1/10
 * 单向链表
 *
 * 1)链表是以 节点的方式来存储，是链式存储
 * 2)每个节点包含data 域，next 域:指向下一个节点.
 * 3)如图: 发现链表的各个节点不- -定 是连续存储.
 * 4)链表分带头节 点的链表和没有头节点的链表，根据实际的需求来确定
 */
public class SingleLinkedListDemo {
    
    public static void main(String[] args){
        //先创建节点
        HeroNode herol = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //创建单向链表
        SingleLinkedList singleLinkedList=new SingleLinkedList();
        //加入
      /*  singleLinkedList.add(herol);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);*/

      //按 order插入
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(herol);
        singleLinkedList.addByOrder(hero3);

        //展示链表  改动 2和 3的添加顺序 观察展示顺序
        singleLinkedList.list();

        //测试修改
        System.out.println("修改之前");
        singleLinkedList.list();
        HeroNode newh=new HeroNode(3, "吴-", "小吴");
        singleLinkedList.update(newh);
        System.out.println("修改之后遍历");
        singleLinkedList.list();
        singleLinkedList.delete(1);
        singleLinkedList.delete(4);
        System.out.println("修改之后遍历");
        singleLinkedList.list();

        //测试单链表的有效节点的个数
        System.out.printf("有效节点的个数为%d\n",getLength(singleLinkedList.getHead()));

    }

    //查找单链表中的倒数第k个结点[新浪面试题]
    //思路
    //1.编写一个方法，接收head节点，同时接收一个index
    //2. index 表示是倒数第index个节点
    //3.先把链表从头到尾遍历，得到链表的总的长度getlength
    //4.得到size 后,我们从链表的第-一个开始遍历(size-index)个,就可以得到


    /**
     *  //方法:获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
     * @param head 链表的头结点
     * @return  链表的有效节点个数
     */
    public static int getLength(HeroNode head){
        if (head.next==null){
            //链表有效节点为空
            return 0;
        }
        int length=0;
        //定义辅助变量  这里没有统计头结点
        HeroNode cur=head.next;
        while (cur!=null){//说明不是最后一个 继续遍历
            length++;
            cur=cur.next;
        }
        return length;
    }
}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList{
    //先初始化一个头结点 不要动 动了以后就找不到其它节点了  不存放具体的数据
    private HeroNode head=new HeroNode(0, "","");
    //返回头结点 给查询个数使用
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode){
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp=head;
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
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)

    /**
     * 需要按照编号的顺序添加
     * 1.首先找到新添加的节点的位置，是通过辅助变量(指针)通过遍历来搞定
     * 2.新的节点.next=temp.next
     * 3.将temp.next=新的节点
     */
    public void  addByOrder(HeroNode heroNode){
        //因为头节点不能动，因，此我们仍然通过--个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因为我们找的temp是位于添加位置的前-一个节点， 否则插入不了
        HeroNode temp=head;
         // flag标志添加的编号是否存在，默认为false
        boolean flag=false;
        while (true){
            if (temp.next==null){//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no>heroNode.no){//位置找到，就在temp的后面插入 ,
                // 注意理解temp的后面 不是temp.next  ，插入新的后 temp、新的、temp.next  这是三个节点
                break;
            }else if(temp.next.no==heroNode.no){///说明希望添加的heroNode的编号已然存在
                flag=true;//说明编号已经存在
                break;
            }
            temp=temp.next;//后移 遍历当前链表
        }

        //判断flag的值
            if (flag){ //编号存在 不能添加
                System. out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n", heroNode .no);

            }else{
                //插入到链表中  temp的后面
                heroNode.next=temp.next;
                temp.next=heroNode;
            }
    }

    //修改节点的信息,根据no编号来修改，即no编号不能改.
    //说明
    //1.根据newHeroNode 的no来修改即可
    public void update(HeroNode newHeroNode){
        //首先检查节点是否为空
        if (head.next==null){
            System.out.println("节点为空，不能跟新");
            return;
        }

        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp=head.next;
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

    /**
     * 从单链表中，删除-一个节 点的思路
     * 1.我们先找到需要删除的这个节点的前一个节点temp
     * 2. temp.next = temp.next.next
     * ) 3.被删除的节点，将不会有其它引用指向，会被垃圾回收机制回收
     */
    //删除节点
    //思路
    //1. head不能动，因此我们需要一个temp辅助节 点找到待删除节点的前一个节点
    //2.说明我们在比较时，是temp.next.no和需要删除的节点的no比较
    public void delete(int no){
        //辅助变量
        HeroNode temp=head;
        boolean flag=false;//标志是否找到被删除的节点
        //变量查找
        while (true){
            if (temp.next==null){//遍历完链表
                break;
            }
            if (temp.next.no==no){//找到待删除节点的 前一个节点 temp
                flag=true;
                break;
            }
            //后移 继续遍历
            temp=temp.next;
        }

        //判断是否找到
        if (flag){//找到
            //删除  把节点移到后一个 ，当它不被指向的时候 ， 就会被垃圾回收机制回收
            temp.next=temp.next.next;
        }else {
            System.out.printf("没有找到带删除的%d 几点",no);
        }
    }



    //显示链表 遍历
    public void list(){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }

        //因为头节点，不能动，因此我们需要- - 个辅助变量来遍历
        HeroNode temp=head.next;
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
}

//定义HeroNode  每个HeroNode 对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public  String nickname;
    public HeroNode next; //指向下一个节点

    //构造器


    public HeroNode(int no, String name, String nickname) {
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