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

import com.pj.untapped.domain.TicketsOrder;
import com.pj.untapped.dtos.TicketsOrderDTO;
import com.pj.untapped.service.TicketsOrderService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/ticketsOrder")
public class TicketsOrderResource {

    @Autowired
    private TicketsOrderService ticketsOrderService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TicketsOrderDTO> findById(@PathVariable Integer id) {
        TicketsOrderDTO objDTO = new TicketsOrderDTO(ticketsOrderService.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<TicketsOrderDTO>> findAll() {
        List<TicketsOrderDTO> ticketList = ticketsOrderService.findAll().stream().map(obj -> new TicketsOrderDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(ticketList);
    }

    @PostMapping
    public ResponseEntity<TicketsOrderDTO> create(@Valid @RequestBody TicketsOrderDTO objDTO) {
        TicketsOrder newObj = ticketsOrderService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TicketsOrderDTO> update(@PathVariable Integer id, @Valid @RequestBody TicketsOrderDTO objDTO) {
        TicketsOrderDTO newObj = new TicketsOrderDTO(ticketsOrderService.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ticketsOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
