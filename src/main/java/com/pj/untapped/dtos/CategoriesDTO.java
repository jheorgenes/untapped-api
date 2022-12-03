package com.pj.untapped.dtos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ManyToMany;

import com.pj.untapped.domain.Categories;
import com.pj.untapped.domain.Event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoriesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String description;
    
    @ManyToMany(mappedBy = "categories")
    private List<Event> events;

    public CategoriesDTO(Categories obj) {
        this.id = obj.getId();
        this.description = obj.getDescription();
        this.events = obj.getEvents();
    }
}
