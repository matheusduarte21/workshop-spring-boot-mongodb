package com.matheusduarte.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusduarte.workshopmongo.domain.Post;
import com.matheusduarte.workshopmongo.repository.PostRepository;
import com.matheusduarte.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id){
		Optional<Post> userOptional = repo.findById(id);
        Post post = userOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
        return post ;
	}
	
	public List<Post> findByTitle(String text){
		return repo.findByTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 *1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
	
	
}
