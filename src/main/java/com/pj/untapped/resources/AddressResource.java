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

import com.pj.untapped.domain.Address;
import com.pj.untapped.dtos.AddressDTO;
import com.pj.untapped.service.AddressService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/address")
public class AddressResource {

	@Autowired
	private AddressService addressService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AddressDTO> findById(@PathVariable Integer id){
		AddressDTO objDTO = new AddressDTO(addressService.findById(id));
		return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<AddressDTO>> findAll(){
		List<AddressDTO> addressList = addressService.findAll().stream().map(obj -> new AddressDTO(obj)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(addressList);
	}
	
	@PostMapping
	public ResponseEntity<AddressDTO> create(@Valid @RequestBody AddressDTO objDTO){
		Address newObj = addressService.create(objDTO);
		AddressDTO newDTO = new AddressDTO(newObj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AddressDTO> update(@PathVariable Integer id, @Valid @RequestBody AddressDTO objDTO){
		AddressDTO newObj = new AddressDTO(addressService.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		addressService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
