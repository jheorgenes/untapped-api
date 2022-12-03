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

import com.pj.untapped.domain.Categories;
import com.pj.untapped.dtos.CategoriesDTO;
import com.pj.untapped.service.CategoriesService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/categories")
public class CategoriesResource {

    @Autowired
    private CategoriesService categoryService;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriesDTO> findById(@PathVariable Integer id) {
        CategoriesDTO objDTO = new CategoriesDTO(categoryService.findById(id));
        return ResponseEntity.ok().body(objDTO); 
    }
    
    @GetMapping
    public ResponseEntity<List<CategoriesDTO>> findAll() {
        List<CategoriesDTO> listCategories = categoryService.findAll().stream().map(obj -> new CategoriesDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listCategories);
    }
    
    @PostMapping
    public ResponseEntity<CategoriesDTO> create(@Valid @RequestBody CategoriesDTO objDTO){
        Categories newObj = categoryService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriesDTO> update(@PathVariable Integer id, @Valid @RequestBody CategoriesDTO objDTO){
        CategoriesDTO newObj = new CategoriesDTO(categoryService.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
