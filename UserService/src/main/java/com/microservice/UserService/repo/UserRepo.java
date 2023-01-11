package com.microservice.UserService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.UserService.entity.User;


@Repository
public interface UserRepo extends JpaRepository<User, String> {

}
