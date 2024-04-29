package com.jlunic.jlunic_authorization_server.infrastructure.security;

import com.jlunic.jlunic_authorization_server.domain.entities.Auth;
import com.jlunic.jlunic_authorization_server.domain.repositories.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService
{
    private AuthRepository repository;

    @Autowired
    public MyUserDetailService(AuthRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Auth user = repository.findByUsername(username).orElseThrow();
        if (user == null)
        {
            throw new UsernameNotFoundException("USUARIO NO ENCONTRADO: " + username);
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        String role = user.getRole();
        roles.add(new SimpleGrantedAuthority(role));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }
}
