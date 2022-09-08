package com.pj.untapped.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.User;
import com.pj.untapped.dtos.UserDTO;
import com.pj.untapped.repositories.UserRepository;
import com.pj.untapped.service.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User findById(Integer id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + User.class.getName()));
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User create(@Valid UserDTO objDTO) {
		User newUser = new User(null, objDTO.getUserName(), objDTO.getEmail(), objDTO.getPassword(), objDTO.getCpf(), objDTO.getBirthDate());
		newUser.setAdresses(objDTO.getAdresses());
		return userRepository.save(newUser);
	}

	public User update(Integer id, @Valid UserDTO objDTO) {
		User oldObj = findById(id);
		dataUpdates(objDTO, oldObj);
		return userRepository.save(oldObj);
	}

	public void delete(Integer id) {
		findById(id); //Retorna exception se não encontrar um user com esse ID
		userRepository.deleteById(id);
	}
	
	private void dataUpdates(UserDTO objDTO, User oldObj) {
		oldObj.setUserName(objDTO.getUserName());
		oldObj.setEmail(objDTO.getEmail());
		oldObj.setPassword(objDTO.getPassword());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setBirthDate(objDTO.getBirthDate());
		/* Prevalecendo a data de cadastro */
		Optional<User> userFoundOp = userRepository.findById(oldObj.getId());
		if(userFoundOp.isPresent()) {
			User userFound = userFoundOp.get();
			oldObj.setCreatedAt(userFound.getCreatedAt());
		}
		/* Atualizando a data de Alteração */
		oldObj.setUpdatedAt(LocalDateTime.now());
	}

}
