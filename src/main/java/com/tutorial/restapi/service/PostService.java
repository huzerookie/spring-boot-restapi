package com.tutorial.restapi.service;

import org.springframework.stereotype.Service;

import com.tutorial.restapi.model.Post;
import com.tutorial.restapi.repository.PostJPARepository;

@Service
public class PostService {
	PostJPARepository postRepository;

	public PostService(PostJPARepository postJPARepository) {
		super();
		this.postRepository = postJPARepository;
	}
	
	public Post createPost(Post post) {
		return postRepository.save(post);
	}
}
