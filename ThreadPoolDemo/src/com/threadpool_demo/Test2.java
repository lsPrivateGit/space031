package com.threadpool_demo;

public class Test2 {

    static  int i= 0 ,j=0;


    static  void one(){
        i++;
        j++;
    }

    static  void two(){
        if(i<j){
            System.out.println("i= " + i +"j= "+j);
        }
    }

    public static void main(String[] args){
        new Thread(() ->{
            for (int k=0;k<1000000;k++){
                Test2.one();
            }
        }).start();

        new Thread(() ->{
            for (int k=0;k<1000000;k++){
                Test2.two();
            }
        }).start();

    }


}
