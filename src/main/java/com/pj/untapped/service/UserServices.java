package com.pj.untapped.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.untapped.domain.User;
import com.pj.untapped.dtos.UserDTO;
import com.pj.untapped.repositories.UserRepository;
import com.pj.untapped.service.exceptions.ObjectNotFoundException;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    UserRepository repository;

    public UserServices(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username);
        if(user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
    }
    
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + User.class.getName()));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public User create(@Valid UserDTO objDTO) {
        String senhaCriptografada = encriptPassword(objDTO.getPassword());
        User newUser = new User(
                null, 
                objDTO.getUsername(), 
                objDTO.getEmail(), 
                senhaCriptografada, 
                objDTO.getFullName(), 
                objDTO.getCpf(), 
                objDTO.getBirthDate(), 
                objDTO.getAccountNonExpired(), 
                objDTO.getAccountNonLocked(), 
                objDTO.getCredentialsNonExpired(), 
                objDTO.getEnabled()
        );
        newUser.setAdresses(objDTO.getAdresses()); //Insere o endereço que tiver.
        newUser.setAvatar(objDTO.getAvatar());
        return repository.save(newUser);
    }

    public User update(Integer id, @Valid UserDTO objDTO) {
        User oldObj = findById(id);
        dataUpdates(objDTO, oldObj);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        findById(id); //Retorna exception se não encontrar um user com esse ID
        repository.deleteById(id);
    }
    
    private void dataUpdates(UserDTO objDTO, User oldObj) {
        oldObj.setUsername(objDTO.getUsername());
        oldObj.setEmail(objDTO.getEmail());
        String newPassword = encriptPassword(objDTO.getPassword());
        oldObj.setPassword(newPassword);
        objDTO.setFullName(objDTO.getFullName());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setBirthDate(objDTO.getBirthDate());
        oldObj.setAvatar(objDTO.getAvatar());
        Optional<User> userFoundOp = repository.findById(oldObj.getId());
        if(userFoundOp.isPresent()) {
            User userFound = userFoundOp.get();
            oldObj.setCreatedAt(userFound.getCreatedAt());
        }
        /* Atualizando a data de Alteração */
        oldObj.setUpdatedAt(LocalDateTime.now());
        oldObj.setAccountNonExpired(objDTO.getAccountNonExpired());
        oldObj.setAccountNonLocked(objDTO.getAccountNonLocked());
        oldObj.setCredentialsNonExpired(objDTO.getCredentialsNonExpired());
        oldObj.setEnabled(objDTO.getEnabled());
    }
    
    private String encriptPassword(String password) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());
        return passwordEncoder.encode(password);
    }
}
