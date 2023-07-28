package com.matheusduarte.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheusduarte.workshopmongo.domain.Post;
import com.matheusduarte.workshopmongo.resources.util.URL;
import com.matheusduarte.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/post")
public class PostResource {
	
	@Autowired
	private PostService service;
	 
	@GetMapping(value = "/{id}") 
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/titlesearch") 
	public ResponseEntity <List<Post>> findByTitle(@RequestParam(value = "text",defaultValue =" ") String text){
		text = URL.decodeParam(text); // isso decodificar o texto 
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/fullsearch") 
	public ResponseEntity <List<Post>> fullSearch(
			@RequestParam(value = "text",defaultValue =" ") String text,
			@RequestParam(value = "minDate",defaultValue =" ") String minDate,
			@RequestParam(value = "maxDate",defaultValue =" ") String maxDate)
	{
		text = URL.decodeParam(text); // isso decodificar o texto
		Date minD = URL.converDate(minDate, new Date());
		Date maxD = URL.converDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text, minD, maxD);
		return ResponseEntity.ok().body(list);
	}

}
