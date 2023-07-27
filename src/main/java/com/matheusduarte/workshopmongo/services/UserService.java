package com.matheusduarte.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusduarte.workshopmongo.domain.User;
import com.matheusduarte.workshopmongo.dto.UserDTO;
import com.matheusduarte.workshopmongo.repository.UserRepository;
import com.matheusduarte.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		User user = repo.findOne(id); // retorna o user pelo id
		if(user == null) { // findOne retorna null caso o id não exista // fazer a verificação para dar a exception
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user;
	}
	
	public User inser(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.delete(id);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	

}
