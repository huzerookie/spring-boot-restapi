package com.tutorial.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.restapi.model.Post;

public interface PostJPARepository extends JpaRepository<Post, Integer>{

}
