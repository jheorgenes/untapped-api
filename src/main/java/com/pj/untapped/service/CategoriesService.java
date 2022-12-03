package com.pj.untapped.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.Categories;
import com.pj.untapped.dtos.CategoriesDTO;
import com.pj.untapped.repositories.CategoriesRepository;
import com.pj.untapped.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository repository;
    
    public Categories findById(Integer id) {
        Optional<Categories> newObj = repository.findById(id);
        return newObj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Categories.class.getName()));
    }
    
    public List<Categories> findAll(){
        return repository.findAll();
    }
    
    public Categories create(@Valid CategoriesDTO objDTO) {
        Categories newObj = new Categories();
        return fromDTO(newObj, objDTO);
    }
    
    public Categories update(Integer id, @Valid CategoriesDTO objDTO) {
        Categories oldObj = findById(id);
        return fromDTO(oldObj, objDTO);
    }
    
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }
    
    private Categories fromDTO(Categories newObj, CategoriesDTO objDTO) {
        newObj.setId(objDTO.getId());
        newObj.setDescription(objDTO.getDescription());
        return repository.save(newObj);
    }
}
