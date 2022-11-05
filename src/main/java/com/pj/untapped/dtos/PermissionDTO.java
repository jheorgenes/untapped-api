package com.pj.untapped.dtos;

import com.pj.untapped.domain.Permission;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PermissionDTO {

    private Integer id;
    private String description;
    
    public PermissionDTO(Permission obj) {
        this.id = obj.getId();
        this.description = obj.getDescription();
    }
}
