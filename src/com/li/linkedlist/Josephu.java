package com.li.linkedlist;

import java.security.PublicKey;

/**
 * @author liql
 * @date 2021/1/24
 * 约瑟夫单向环形列表
 *
 * 构建一一个单向的环形链表思路
 * 1.先创建第一个节点,让first指向该节点，并形成环形
 * 2.后面当我们每创建一个新的节点， 就把该节点，加入到已有的环形链表中即可
 * 遍历环形链表
 * 1.先让一个辅助指针(变量)curBoy，指向first节点
 * 2.然后通过一- 个while循环遍历该环形链表即可curBoy.next == first结束
 */
public class Josephu {
    public static void main(String[] args){

        //测试单向环形链表的添加与遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        //加入5个小孩节点
        circleSingleLinkedList.addBoy(5);
        //遍历
    //    circleSingleLinkedList.showBoy();
        //小孩出圈
        circleSingleLinkedList.countBoy(1, 2, 5);

    }
}
//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个 first节点  当前没有标号
    private  Boy first=new Boy(-1);
    //添加小孩的节点 构建一个环形的链表
    public void addBoy(int nums){
        //nums 做一个数据校验
        if (nums<1){
            System.out.println("nums的值不正确");
            return;
        }
        //创建一个辅助指针 帮助构建环形链表
        Boy curBoy=null;
        //使用一个for循环 创建我们的环形链表
        for (int i=1;i<=nums;i++){
            //根据标号 创建boy节点
            Boy boy=new Boy(i);
            //如果是第一个小还
            if (i == 1) {
                first=boy;
                //构成环状
                first.setNext(first);
                //让curBoy指向第一个boy  因为first不能动
                curBoy=first;
            }else {
                //先让curBoy的next指向新节点 让新节点的next指向 fisrt头结点，然后再让curBoy后移
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;//curboy后移
            }
        }
    }

        /**
         * ➢
         * Josephu
         * 问题
         * Josephu问题为: 设编号为1，2，... n的n个人围坐- -圈，约定编号为k (1<=k<=n)的人从1开始报数，数到
         * m的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，由此
         * 产生一个出队编号的序列。
         * ➢提示
         * 用一个不带头结点的循环链表来处理Josephu问题:先构成一个有n个结点的单循环链表，然后由k结点起从1开
         * 始计数，计到m时，对应结点从链表中删除，然后再从被删除结点的下一个结点又从1开始计数，直到最后一个
         * 节点从链表中删除 ，算法结束
         */
        //根据用户输入 计算小孩出圈的顺序

    /**
     *
     * @param startNo 表示从第一个小孩开始数数
     * @param countNum  表示数几下
     * @param nums  表示最初有多少个小孩在圈中
     */
        public void countBoy(int startNo,int countNum,int nums){
            //数据校验
            if (first == null|| startNo<1||startNo>nums) {
                System.out.println("参数输入有误");
                return;
            }
            //创建一个辅助指针  帮助小孩除权
            Boy helper=first;
            //需求创建一个辅助指针(变量) helper，事先应该指向环形链表的最后这个节点
            while (true){
                if (helper.getNext()==first){//说明已经走到最后
                    break;
                }
                helper=helper.getNext();
            }
            //小孩报数前 先让first和helper移动 (开始的那个人 -1)次，
            // first移动到开始报数的那个人的前面一位
           //helper 移动到开始报数的那个人的前面两位
            for (int j=0;j<startNo-1;j++){
                first=first.getNext();
                helper=helper.getNext();
            }

            //当小孩报数时，让first和helper指针同时移动 (需要报的数 -1 )次，然后出圈
            //循环操作  直到圈中只有一个节点
            while (true){
                if (helper==first){//说明圈中只有一个节点
                    break;
                }
                //移动first和helper
                for (int j=0;j<countNum-1;j++){
                    first=first.getNext();
                    helper=helper.getNext();
                }
                //这时 first指向的节点就是要除权的节点
                System.out.printf("小孩%d出圈\n",first.getNo());
                //这时将first指向的小孩节点出圈 删除
                first=first.getNext();
                helper.setNext(first);
            }

            System.out.printf("最后留在圈中的额小孩的编号%d \n",first.getNo());
        }


    //遍历当前的环形链表
    public void showBoy(){
        //先判断聊表是否为空
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        //因为first不能动 所以要用辅助指针完成遍历
        Boy curBoy=first;
        while (true){
            System.out.printf("小孩的标号%d \n",curBoy.getNo());
            //判断是否遍历到结尾
            if (curBoy.getNext()==first){
                System.out.println("遍历完毕");
                break;
            }
            //没有遍历完 让curBoy后移
            curBoy=curBoy.getNext();
        }
    }
}


//创建一个boy类 ，表示一个节点
class Boy{
    private int no;
    private Boy next;//指向下一个节点 默认为null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}