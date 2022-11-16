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

import com.pj.untapped.domain.Order;
import com.pj.untapped.dtos.OrderDTO;
import com.pj.untapped.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/order")
public class OrderResource {

    @Autowired
    private OrderService orderService;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Integer id) {
        OrderDTO objDTO = new OrderDTO(orderService.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        List<OrderDTO> orders = orderService.findAll().stream().map(obj -> new OrderDTO(obj)).collect(Collectors.toList()); 
        return ResponseEntity.ok().body(orders);
    }
    
    @PostMapping
    public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO objDTO){
        Order newObj = orderService.create(objDTO);
        OrderDTO newDTO = new OrderDTO(newObj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(newDTO);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable Integer id, @Valid @RequestBody OrderDTO objDTO){
        OrderDTO newObj = new OrderDTO(orderService.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
