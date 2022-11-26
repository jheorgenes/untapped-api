package com.pj.untapped.dtos;

import org.springframework.security.core.GrantedAuthority;

import com.pj.untapped.domain.Permission;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PermissionDTO implements GrantedAuthority {

    private Integer id;
    private String description;
    
    public PermissionDTO(Permission obj) {
        this.id = obj.getId();
        this.description = obj.getDescription();
    }
    
    @Override
    public String getAuthority() {
        return this.description;
    }
}
