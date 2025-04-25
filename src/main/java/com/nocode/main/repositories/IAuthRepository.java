package com.nocode.main.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nocode.main.entities.UserEntity;

@Repository
public interface IAuthRepository extends JpaRepository<UserEntity, String> {

      Optional<UserEntity> findByUserName(String userName);

      Optional<UserEntity> findByEmail(String email);

      Optional<UserEntity> findByPhone(String phone);

      Optional<UserEntity> findByDocumentNumber(String documentNumber);

}
