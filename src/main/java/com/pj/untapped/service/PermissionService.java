package com.pj.untapped.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.Permission;
import com.pj.untapped.dtos.PermissionDTO;
import com.pj.untapped.repositories.PermissionRepository;
import com.pj.untapped.service.exceptions.ObjectNotFoundException;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository repository;
    
    public Permission findById(Integer id) {
        Optional<Permission> newObj = repository.findById(id);
        return newObj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + Permission.class.getName()));
    }
    
    public List<Permission> findAll(){
        return repository.findAll();
    }
    
    public Permission create(@Valid PermissionDTO objDTO) {
        Permission newObj = new Permission();
        return fromDTO(newObj, objDTO);
    }
    
    public Permission update(Integer id, @Valid PermissionDTO objDTO) {
        Permission oldObj = findById(id);
        return fromDTO(oldObj, objDTO);
    }
    
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
    
    private Permission fromDTO(Permission newObj, PermissionDTO objDTO) {
        newObj.setId(objDTO.getId());
        newObj.setDescription(objDTO.getDescription());
        return repository.save(newObj);
    }
}
