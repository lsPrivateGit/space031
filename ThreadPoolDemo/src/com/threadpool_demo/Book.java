package com.threadpool_demo;
public class Book {
   /* public static void main(String[] args)
    {
        staticFunction();
    }*/
    static Book book = new Book();
    static
    {
        System.out.println("��ľ�̬�����");
    }
    {
        System.out.println("�����ͨ�����");
    }
    Book(){
        System.out.println("��Ĺ��췽��");
        System.out.println("price=" + price +",amount=" + amount);
    }
    public static void staticFunction(){
        System.out.println("��ľ�̬����");
    }
    int price = 110;
    static int amount = 112;
}