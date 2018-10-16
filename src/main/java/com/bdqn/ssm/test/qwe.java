package com.bdqn.ssm.test;

/**
 * @author 贺威
 * @create 2018-10-14 14:16
 */
public class qwe {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(abc(n)+"---");
    };

    public static int abc(int n){
        int t1,t2;
        System.out.println(n);
        if(n==1||n==2){
            return 1;
        }else{
            t1 = abc(n-1);
            t2 = abc(n-2);
            System.out.println(t2+"***"+t1);
            return t1+t2;
        }

    }

}
