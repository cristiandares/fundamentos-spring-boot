package com.fundamentos.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWhitDependencyImplement implements  MyBeanWithDependency{

    Log LOGGER = LogFactory.getLog(MyBeanWhitDependencyImplement.class);

    MyOperation  myOperation;

    public MyBeanWhitDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependecy() {
        LOGGER.info("Hemos ingresado al metodo printWithDependecy");
        int numero=1;
        LOGGER.debug("El numero enviado como parametro a la dependencia operation es: " + numero);
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implementacion de un bean con dependencia");
    }
}
