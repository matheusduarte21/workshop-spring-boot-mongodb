package com.matheusduarte.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.matheusduarte.workshopmongo.domain.Post;
import com.matheusduarte.workshopmongo.domain.User;
import com.matheusduarte.workshopmongo.repository.PostRepository;
import com.matheusduarte.workshopmongo.repository.UserRepository;

@Configuration 
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
		sfd.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll(); // isso vai limpar o mongo
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post post1 = new Post(null, sfd.parse("21/12/2002"), "Partiu viagem", "vou viajar para SP, abraços!", maria);
		Post post2 = new Post(null, sfd.parse("22/12/2002"), "Bom dia", "Acordei feliz hoje!", maria);

		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}
	
	

}
