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

import com.pj.untapped.domain.Permission;
import com.pj.untapped.dtos.PermissionDTO;
import com.pj.untapped.service.PermissionService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/permission")
public class PermissionResource {

    @Autowired
    private PermissionService permissionService;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<PermissionDTO> findById(@PathVariable Integer id) {
        PermissionDTO objDTO = new PermissionDTO(permissionService.findById(id));
        return ResponseEntity.ok().body(objDTO); 
    }
    
    @GetMapping
    public ResponseEntity<List<PermissionDTO>> findAll() {
        List<PermissionDTO> listPermissions = permissionService.findAll().stream().map(obj -> new PermissionDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listPermissions);
    }
    
    @PostMapping
    public ResponseEntity<PermissionDTO> create(@Valid @RequestBody PermissionDTO objDTO){
        Permission newObj = permissionService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<PermissionDTO> update(@PathVariable Integer id, @Valid @RequestBody PermissionDTO objDTO){
        PermissionDTO newObj = new PermissionDTO(permissionService.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
