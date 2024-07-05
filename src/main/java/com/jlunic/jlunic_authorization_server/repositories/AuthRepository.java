package com.jlunic.jlunic_authorization_server.repositories;

import com.jlunic.jlunic_authorization_server.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<UserEntity, Integer>
{
    Optional<UserEntity> findByUsername(String username);
}
