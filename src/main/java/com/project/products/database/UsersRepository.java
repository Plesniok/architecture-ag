package com.project.products.database;

import com.project.products.models.Price;
import com.project.products.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {

    List<User> findByEmailAndPassword(String email, String password);

}