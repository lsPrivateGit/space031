package com.threadpool_�̳߳�1;
class Grandpa
{
    static
    {
        System.out.println("үү�ھ�̬�����");
    }
}    
class Father extends Grandpa
{
    static
    {
        System.out.println("�ְ��ھ�̬�����");
    }
    public static int factor = 25;
    public Father()
    {
        System.out.println("���ǰְ�~");
    }
}
class Son extends Father
{
    static 
    {
        System.out.println("�����ھ�̬�����");
    }
    public Son()
    {
        System.out.println("���Ƕ���~");
    }
}
public class InitializationDemo
{
    public static void main(String[] args)
    {
        System.out.println("�ְֵ�����:" + Son.factor);    //���
    }
}