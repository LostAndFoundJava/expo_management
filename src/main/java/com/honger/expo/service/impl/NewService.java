package com.honger.expo.service.impl;

import com.honger.expo.dto.response.home.CategoryListResponse;
import com.honger.expo.pojo.Category;
import com.honger.expo.pojo.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NewService {
    public static void main(String[] args) {

        Node node8 = new Node(null,8);
        Node node7 = new Node(node8,7);
        Node node6 = new Node(node7,6);
        Node node5 = new Node(node6,5);
        Node node4 = new Node(node5,4);
        Node node3 = new Node(node4,3);
        Node node2 = new Node(node3,2);
        Node node1 = new Node(node2,1);

        Node first = node1;
        while(first!=null){
            System.out.print(first.getValue()+" ");
            first = first.next;
        }
        System.out.println();

        int n = 8;

        Integer a ;
        Node newHead = null;
        Node tmp = node1;
        Node tem1 = null;
        for(int i = 0;i < 3;i++){
            Stack<Node> list = new Stack<>();
            int j = 3;
            while(j!=0){
                list.push(tmp);
                if(tmp.next!=null)
                    tmp = tmp.next;
                else
                    break;
                j--;
            }


            for(int k = 0;k < 3; k ++){
                if(i == 0&& k==0){
                    newHead = list.pop();
                    tem1 = newHead;
                }else{
                    if(list.size()==0)
                        break;
                    tem1.next = list.peek();
                    tem1 = list.pop();
                }

            }
        }
//        tem1.next = null;
        while(newHead!=null){
            System.out.print(newHead.getValue()+" ");
            newHead = newHead.next;
        }
        System.out.println();
    }

    static class Node{
        Node next;
        int value;

        public Node(Node next, int value) {
            this.next = next;
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
