package com.pj.untapped.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.untapped.domain.User2;
import com.pj.untapped.dtos.UserDTO;
import com.pj.untapped.repositories.User2Repository;
import com.pj.untapped.service.exceptions.ObjectNotFoundException;

@Service
public class UserService2 {
	
	@Autowired
	private User2Repository user2Repository;

	public User2 findById(Integer id) {
		Optional<User2> obj = user2Repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + User2.class.getName()));
	}

	public List<User2> findAll() {
		return user2Repository.findAll();
	}

	@Transactional(rollbackFor = Exception.class)
	public User2 create(@Valid UserDTO objDTO) {
//	    String generatedSecuredPasswordHash = BCrypt.hashpw(objDTO.getPassword(), BCrypt.gensalt(12));
		User2 newUser = new User2(null, objDTO.getUserName(), objDTO.getEmail(), objDTO.getPassword(), objDTO.getCpf(), objDTO.getBirthDate());
		newUser.setAdresses(objDTO.getAdresses());
		return user2Repository.save(newUser);
	}

	public User2 update(Integer id, @Valid UserDTO objDTO) {
		User2 oldObj = findById(id);
		dataUpdates(objDTO, oldObj);
		return user2Repository.save(oldObj);
	}

	public void delete(Integer id) {
		findById(id); //Retorna exception se não encontrar um user com esse ID
		user2Repository.deleteById(id);
	}
	
	private void dataUpdates(UserDTO objDTO, User2 oldObj) {
		oldObj.setUserName(objDTO.getUserName());
		oldObj.setEmail(objDTO.getEmail());
		oldObj.setPassword(objDTO.getPassword());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setBirthDate(objDTO.getBirthDate());
		/* Prevalecendo a data de cadastro */
		Optional<User2> userFoundOp = user2Repository.findById(oldObj.getId());
		if(userFoundOp.isPresent()) {
			User2 userFound = userFoundOp.get();
			oldObj.setCreatedAt(userFound.getCreatedAt());
		}
		/* Atualizando a data de Alteração */
		oldObj.setUpdatedAt(LocalDateTime.now());
	}
}
