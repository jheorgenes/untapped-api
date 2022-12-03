package com.pj.untapped.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pj.untapped.domain.Address;
import com.pj.untapped.domain.Permission;
import com.pj.untapped.domain.User;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "UserName is required")
    private String username;
    
    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;
    
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;
    
    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;
    
    @Column(name = "enabled")
    private Boolean enabled;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @CPF
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime updatedAt;

    @NotEmpty(message = "FullName is required")
    private String fullName;
    
    private String avatar;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> adresses = new ArrayList<>();
    
    @ManyToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Permission> permissions;

    public UserDTO() {
        super();
    }

    public UserDTO(User obj) {
        super();
        this.id = obj.getId();
        this.username = obj.getUsername();
        this.fullName = obj.getFullname();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.cpf = obj.getCpf();
        this.birthDate = obj.getBirthDate();
        this.createdAt = obj.getCreatedAt();
        this.avatar = obj.getAvatar();
        this.updatedAt = obj.getUpdatedAt();
        this.adresses = obj.getAdresses();
        this.accountNonExpired = obj.getAccountNonExpired();
        this.accountNonLocked = obj.getAccountNonLocked();
        this.credentialsNonExpired = obj.getCredentialsNonExpired();
        this.enabled = obj.getEnabled();
        this.permissions = obj.getPermissions();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Address> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Address> adresses) {
        this.adresses = adresses;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
