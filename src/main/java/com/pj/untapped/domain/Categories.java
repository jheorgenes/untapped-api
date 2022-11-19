package com.pj.untapped.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.pj.untapped.domain.enuns.Category;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Categories implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "Category cannot be null")
    @NotEmpty(message = "Category is required")
    private Integer category;
    
    private LocalDate createdAt;
    
    @ManyToMany(mappedBy = "categories")
    private List<Event> events;
    
    public Categories(Integer id,
            @NotNull(message = "Category cannot be null") 
            @NotEmpty(message = "Category is required") Category category,
            List<Event> events) {
        this.id = id;
        this.category = (category == null) ? 0 : category.getCod();;
        this.events = events;
    } 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return Category.toEnum(this.category);
    }

    public void setCategory(Category category) {
        this.category = category.getCod();
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
