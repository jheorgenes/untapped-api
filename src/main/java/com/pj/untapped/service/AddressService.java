package com.pj.untapped.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.untapped.domain.Address;
import com.pj.untapped.domain.Event;
import com.pj.untapped.domain.User;
import com.pj.untapped.dtos.AddressDTO;
import com.pj.untapped.repositories.AddressRepository;
import com.pj.untapped.repositories.EventRepository;
import com.pj.untapped.repositories.UserRepository;
import com.pj.untapped.service.exceptions.ObjectNotFoundException;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public Address findById(Integer id) {
        Optional<Address> obj = addressRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + Address.class.getName()));
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address create(@Valid AddressDTO objDTO) {
        Address address = new Address(
            null, 
            objDTO.getTitle(), 
            objDTO.getStreet(), 
            objDTO.getDistrict(),
            objDTO.getCep(), 
            objDTO.getCity(), 
            objDTO.getState(), 
            objDTO.getCountry()
        );
        address.setAddressComplement(objDTO.getAddressComplement());
        address.setAddressNumber(objDTO.getAddressNumber());
        address.setLatitude(objDTO.getLatitude());
        address.setLongitude(objDTO.getLongitude());

        findUserRelated(objDTO, address);
        findEventRelated(objDTO, address);

        return addressRepository.save(address);
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
        oldObj.setId(objDTO.getId());
        oldObj.setTitle(objDTO.getTitle());
        oldObj.setStreet(objDTO.getStreet());
        oldObj.setDistrict(objDTO.getDistrict());
        oldObj.setCep(objDTO.getCep());
        oldObj.setCity(objDTO.getCity());
        oldObj.setState(objDTO.getState());
        oldObj.setCountry(objDTO.getCountry());
        oldObj.setAddressComplement(objDTO.getAddressComplement());
        oldObj.setAddressNumber(objDTO.getAddressNumber());
        oldObj.setLatitude(objDTO.getLatitude());
        oldObj.setLongitude(objDTO.getLongitude());

        findUserRelated(objDTO, oldObj);
        findEventRelated(objDTO, oldObj);
    }

    private void findUserRelated(AddressDTO dto, Address obj) {
        if (dto.getUserId() != null) {
            Optional<User> userEncontrado = userRepository.findById(dto.getUserId());
            if (userEncontrado.isPresent()) {
                obj.setUser(userEncontrado.get());
            }
        }
    }
    
    private void findEventRelated(AddressDTO dto, Address obj) {
        if (dto.getEventId() != null) {
            Optional<Event> eventEncontrado = eventRepository.findById(dto.getEventId());
            if (eventEncontrado.isPresent()) {
                obj.setEvent(eventEncontrado.get());
            }
        }
    }
}
