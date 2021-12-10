package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    //Uso de JPQL en anotación query
    @Query("Select u from User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("Select u from User u WHERE u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    //Obtención de información usando Query methods
    List<User> findByName(String name);

    List<User> findByNameLike(String name);

    //Uso de Query methods con Or, and, OrderBy, Between, Sort
    List<User> findByNameOrEmail(String name, String email);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLikeOrderByIdDesc (String name);

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    List<User> findByNameContainingOrderByIdDesc (String name);

    //Uso de JPQL con named parameters
    @Query("SELECT new com.fundamentos.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthDate)" +
            "FROM User u" +
            " where u.birthDate=:parametroFecha" +
            " and u.email=:paramtroEmail")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date,
                                                @Param("paramtroEmail") String email);


    List<User> findAll();
}
