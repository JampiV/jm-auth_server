package com.jlunic.jlunic_authorization_server.domain.repositories;

import com.jlunic.jlunic_authorization_server.domain.entities.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Integer>
{
    Optional<Auth> findByUsername(String username);
}
