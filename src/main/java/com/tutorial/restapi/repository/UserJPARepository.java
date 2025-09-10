package com.tutorial.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.restapi.model.User;

@Repository
public interface UserJPARepository extends JpaRepository<User, Integer>{

}
