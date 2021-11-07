package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
    private UserRepository userRepository;

    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency component,
                                  MyBean myBean,
                                  MyBeanWithDependency myBeanWithDependency,
                                  MyBeanWithProperties myBeanWithProperties,
                                  UserPojo userPojo,
                                  UserRepository userRepository){
        this.componentDependency = component;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //ejemplosAnteriores();
        saveUsersInDataBase();
    }

    private void saveUsersInDataBase(){
        User user1 = new User("Cristian", "cristian@mail.com", LocalDate.of(2021, 3, 19));
        User user2 = new User("Alejandra", "aborrero@mail.com", LocalDate.of(1996, 6, 22));
        User user3 = new User("Carolina", "carolina@mail.com", LocalDate.of(1986, 9, 26));
        User user4 = new User("Carlos", "carlos@mail.com", LocalDate.of(2001, 2, 30));
        User user5 = new User("Andres", "andres@mail.com", LocalDate.of(1999, 4, 15));
        User user6 = new User("Juan", "juan@mail.com", LocalDate.of(1998, 8, 24));
        User user7 = new User("Diego", "diego@mail.com", LocalDate.of(1995, 7, 16));
        User user8 = new User("Luz", "luz@mail.com", LocalDate.of(1992, 5, 12));
        User user9 = new User("Elena", "elena@mail.com", LocalDate.of(1991, 12, 5));
        User user10 = new User("Alberto", "alberto@mail.com", LocalDate.of(1993, 1, 2));
        List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
        list.stream().forEach(userRepository::save);
    }

    private void ejemplosAnteriores(){
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependecy();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword());
        try {
            int  value = 10/0;
            LOGGER.info("Mi valor :" + value);
        }catch (Exception e){
            LOGGER.error("Esto es error al dividir por cero: " + e.getMessage());
        }
    }
}
