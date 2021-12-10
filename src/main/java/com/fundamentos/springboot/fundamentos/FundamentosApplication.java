package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

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
    private UserService userService;

    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency component,
                                  MyBean myBean,
                                  MyBeanWithDependency myBeanWithDependency,
                                  MyBeanWithProperties myBeanWithProperties,
                                  UserPojo userPojo,
                                  UserRepository userRepository,
                                  UserService userService){
        this.componentDependency = component;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //ejemplosAnteriores();
        saveWithErrorTransational();
        saveUsersInDataBase();
        getInformationJpqlFromUser();
    }

    private void saveWithErrorTransational(){
        User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
        User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
        User test3 = new User("TestTransactional3", "TestTransactional1@domain.com", LocalDate.now());
        User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

        List<User> users = Arrays.asList(test1,test2,test3,test4);

        try{
            userService.saveTransactional(users);
        }catch (Exception e){
            LOGGER.error("Esta es una excepcion dentro del metodo transational" + e);
        }
        userService.getAllUsers()
                .stream()
                .forEach(user -> LOGGER.info("Este es el usuario dentro del metodo Transational :" + user));
    }

    private void getInformationJpqlFromUser(){
        /*
        LOGGER.info("Usuario con el metodo findUserEmail :" +
                userRepository.findByUserEmail("cristian@mail.com")
                .orElseThrow(() -> new RuntimeException("NO se encontro el usuario")));

        userRepository.findAndSort("Car", Sort.by("id").descending())
                .stream()
                .forEach(user ->  LOGGER.info("Usuario con metodo Sort" + user));

        userRepository.findByName("Cristian")
                .stream()
                .forEach(user -> LOGGER.info("Usuario con Query Method :" + user));

        LOGGER.info("c" + userRepository.findByEmailAndName("aborrero@mail.com", "Alejandra")
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        userRepository.findByNameLike("%Cr%")
                .stream()
                .forEach(user -> LOGGER.info("Usuario findByNameLike :" + user));

        userRepository.findByNameOrEmail(null, "carlos@mail.com")
                .stream()
                .forEach(user -> LOGGER.info("Usuario findByNameOrEmail :" + user));

        userRepository
                .findByBirthDateBetween(LocalDate.of(2001,1,1), LocalDate.of(2021,12,31))
                .stream()
                .forEach(user -> LOGGER.info("Usuario con intervalo de fechas :" + user));

        userRepository.findByNameLikeOrderByIdDesc("%Car%")
                .stream()
                .forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado" + user));

        userRepository.findByNameContainingOrderByIdDesc("Car")
                .stream()
                .forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado" + user));
         */
        LOGGER.info("El Usuario a partir del named parameter :"+
                userRepository.getAllByBirthDateAndEmail(LocalDate.of(1993,1,2),"alberto@mail.com")
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario a partir del named parameter")));
    }

    private void saveUsersInDataBase(){
        User user1 = new User("Cristian", "cristian@mail.com", LocalDate.of(2021, 3, 19));
        User user2 = new User("Alejandra", "aborrero@mail.com", LocalDate.of(1996, 6, 22));
        User user3 = new User("Carolina", "carolina@mail.com", LocalDate.of(1986, 9, 26));
        User user4 = new User("Carlos", "carlos@mail.com", LocalDate.of(2001, 3, 30));
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
