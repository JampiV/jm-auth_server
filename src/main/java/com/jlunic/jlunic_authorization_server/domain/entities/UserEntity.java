package com.jlunic.jlunic_authorization_server.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity
{
    @Id
    private Long id;

    private String username;

    private String password;

    @ManyToOne
    private RoleEntity role;


}
