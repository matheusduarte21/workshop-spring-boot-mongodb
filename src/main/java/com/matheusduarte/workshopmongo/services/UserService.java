package com.matheusduarte.workshopmongo.services;

import java.util.List;
import java.util.Optional;

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
	
	public User findById(String id){
		Optional<User> userOptional = repo.findById(id);
        User user = userOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
        return user;
	}
	
	public User inser(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id); // chamar a função para descobrir se tem um usuário primeiro
		repo.deleteById(id);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	
	
	public User update(User obj) {
		User  newObj = repo.findById(obj.getId()); // vai no banco de dados e pega o user com o id que vc passou
		updateDate(newObj, obj);
		return repo.save(newObj);
	}

	private void updateDate(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}
	

}
