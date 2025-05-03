package com.nocode.main.infrastructure.adapter.repository;

import com.nocode.main.infrastructure.adapter.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, String> {

    Page<UserEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPhone(String phone);

    Optional<UserEntity> findByDocumentNumber(String documentNumber);

    Optional<UserEntity> findByUserName(String userName);

}
