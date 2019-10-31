package com.cc.base.part01;

/**
 *  reference
 */
public class L1_reference {

    String s;
    int a;

    public static void main(String[] args) {
        int b;
        L1_reference l1 =  new L1_reference();
        // 成员变量默认被初始化
        System.out.println(l1.a + l1.s);
        // 局部变量没有手动初始化
        //System.out.println(b);
        System.out.println(l1.getB(1) + "-" + l1.getB(1));

    }

    int getB(int i){
        int b = i;
        i = 2;
        return b;
    }

    Integer getB(Integer i){
        Integer b = i;
        i = 2;
        return b;
    }
}
