package com.project.products.database;

import com.project.products.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionsRepository extends JpaRepository<User, Long> {

}
