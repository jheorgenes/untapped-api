package com.pj.untapped.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.Address;
import com.pj.untapped.dtos.AddressDTO;
import com.pj.untapped.repositories.AddressRepository;
import com.pj.untapped.service.exceptions.ObjectNotFoundException;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	public Address findById(Integer id) {
		Optional<Address> obj = addressRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Address.class.getName()));
	}

	public List<Address> findAll(){
		return addressRepository.findAll();
	}

	public Address create(@Valid AddressDTO objDTO) {
		return addressRepository.save(new Address(null, objDTO.getStreet(), objDTO.getDistrict(), objDTO.getCep(), objDTO.getCity(), objDTO.getState(), objDTO.getContry()));
	}
	
	public Address createObject(@Valid Address obj) {
	    return addressRepository.save(obj);
	}

	public Address update(Integer id, @Valid AddressDTO objDTO) {
		Address oldObj = findById(id);
		dataUpdate(objDTO, oldObj);
		return addressRepository.save(oldObj);
	}

	public void delete(Integer id) {
		Address obj = findById(id);
		addressRepository.deleteById(obj.getId());
	}
	
	private void dataUpdate(AddressDTO objDTO, Address oldObj) {
		oldObj.setStreet(objDTO.getStreet());
		oldObj.setDistrict(objDTO.getDistrict());
		oldObj.setCep(objDTO.getCep());
		oldObj.setCity(objDTO.getCity());
		oldObj.setState(objDTO.getState());
		oldObj.setContry(objDTO.getContry());
	}

}
