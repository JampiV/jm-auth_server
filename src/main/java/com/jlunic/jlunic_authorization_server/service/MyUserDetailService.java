package com.jlunic.jlunic_authorization_server.service;

import com.jlunic.jlunic_authorization_server.domain.entities.RoleEntity;
import com.jlunic.jlunic_authorization_server.domain.entities.UserEntity;
import com.jlunic.jlunic_authorization_server.repositories.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MyUserDetailService implements UserDetailsService
{
    private AuthRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserEntity user = repository.findByUsername(username).orElseThrow();
        if (user == null)
        {
            throw new UsernameNotFoundException("USUARIO NO ENCONTRADO: " + username);
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        RoleEntity role = user.getRole();
        roles.add(new SimpleGrantedAuthority(role.getName()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }

    public String getPassword(String username)
    {
        UserEntity user = repository.findByUsername(username).orElseThrow();
        return user.getPassword();
    }
}
