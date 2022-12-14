package com.pj.untapped.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pj.untapped.domain.User;
import com.pj.untapped.dtos.UserDTO;
import com.pj.untapped.service.UserServices;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/users")
public class UserResource {
    
    @Autowired
    private UserServices userServices;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        UserDTO objDTO = new UserDTO(userServices.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> listUsers = userServices.findAll().stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listUsers);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO objDTO){
        User newObj = userServices.create(objDTO);
        UserDTO newDTO = new UserDTO(newObj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                             .buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(newDTO);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @Valid @RequestBody UserDTO objDTO){
        UserDTO newObj = new UserDTO(userServices.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        userServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
