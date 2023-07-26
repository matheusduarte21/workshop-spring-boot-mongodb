package com.matheusduarte.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matheusduarte.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@RequestMapping(method =RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		User matheus = new User("1", "Matheus Duarte", "Matheus@gmail.com");
		User jorge = new User("1", "jorge alves", "jorge@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(matheus, jorge));
		return ResponseEntity.ok().body(list);
	}
}
