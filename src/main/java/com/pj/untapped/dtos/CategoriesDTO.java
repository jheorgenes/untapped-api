package com.pj.untapped.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.ManyToMany;

import com.pj.untapped.domain.Categories;
import com.pj.untapped.domain.Event;
import com.pj.untapped.domain.enuns.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoriesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer category;
    private LocalDate createdAt;
    
    @ManyToMany(mappedBy = "categories")
    private List<Event> events;

    public CategoriesDTO(Categories obj) {
        this.id = obj.getId();
        this.category = obj.getCategory().getCod();
        this.events = obj.getEvents();
    }

    public Category getCategory() {
        return Category.toEnum(this.category);
    }

    public void setCategory(Category category) {
        this.category = category.getCod();
    }
}
