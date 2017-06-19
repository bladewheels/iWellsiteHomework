package com.iwellsite.homework.domain.model;

import com.iwellsite.homework.domain.model.AbstractDomainClass;
import com.iwellsite.homework.domain.model.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mshields on 2017-06-19.
 */
@Data
@Entity
public class User extends AbstractDomainClass {

    @Transient
    private String password;

    private String username;
    private String encryptedPassword;
    private Boolean enabled = true;
    private Integer failedLoginAttempts = 0;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    // Defaults to:
    // @JoinTable(name = "USER_ROLE",
    // joinColumns = @JoinColumn(name = "user_id"),
    // inverseJoinColumns = @joinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public void addRole(Role role){
        if(!this.roles.contains(role)){
            this.roles.add(role);
        }

        if(!role.getUsers().contains(this)){
            role.getUsers().add(this);
        }
    }

    public void removeRole(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
}
