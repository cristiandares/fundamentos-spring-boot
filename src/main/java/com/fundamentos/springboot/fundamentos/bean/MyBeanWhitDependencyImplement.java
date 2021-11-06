package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanWhitDependencyImplement implements  MyBeanWithDependency{

    MyOperation  myOperation;

    public MyBeanWhitDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependecy() {
        int numero=1;
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implementacion de un bean con dependencia");
    }
}
