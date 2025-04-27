package com.nocode.main.business;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nocode.main.dtos.AuthDto;
import com.nocode.main.dtos.UserDto;
import com.nocode.main.entities.UserEntity;
import com.nocode.main.exception.ConflictException;
import com.nocode.main.exception.ResourceNotFoundException;
import com.nocode.main.helper.AesUtil;
import com.nocode.main.helper.JwtUtil;
import com.nocode.main.repositories.IAuthRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthBusiness {

      @Autowired
      private IAuthRepository _repo;

      @Transactional
      public void registerUser(UserDto userDto) {

            /*
             * _repo.findByUserName(userDto.getUserName())
             * .ifPresent( n -> {
             * throw new ConflictException("El nombre de usuario ya existe.");
             * } );
             * 
             * _repo.findByEmail(userDto.getEmail())
             * .ifPresent( n -> {
             * throw new ConflictException("El correo electrónico ya existe.");
             * } );
             * 
             * _repo.findByPhone(userDto.getPhone())
             * .ifPresent( n -> {
             * throw new ConflictException("El número de teléfono ya existe.");
             * } );
             * 
             * _repo.findByDocumentNumber(userDto.getDocumentNumber())
             * .ifPresent( n -> {
             * throw new ConflictException("El número de documento ya existe.");
             * } );
             */
            UserEntity entity = UserEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .userName(userDto.getUserName())
                        /*
                         * .email(userDto.getEmail())
                         * .phone(userDto.getPhone())
                         * .documentNumber(userDto.getDocumentNumber())
                         * .documentType(userDto.getDocumentType())
                         * .address(userDto.getAddress())
                         * .name(userDto.getName())
                         * .role(userDto.getRole())
                         */
                        .password(AesUtil.encrypt(userDto.getPassword()))
                        .status(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
            _repo.save(entity);


      }

      public AuthDto login(String userName, String password) {
            Optional<UserEntity> user = _repo.findByUserName(userName);

            if (user.isPresent()) {
                  UserEntity userEntity = user.get();
                  if (AesUtil.decrypt(userEntity.getPassword()).equals(password)) {
                        return AuthDto.builder()
                                    .userName(userEntity.getUserName())
                                    .token(new JwtUtil().generateToken(userEntity.getId(), userName))
                                    .build();
                  } else {
                        throw new ConflictException("Contraseña incorrecta.");
                  }
            } else {
                  throw new ConflictException("Usuario no encontrado.");
            }
      }

      public UserDto findById(String id) {

            UserEntity user = _repo.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Categoria", "id", id));
            return UserDto.builder()
                        .id(user.getId())
                        .userName(user.getUserName())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .documentNumber(user.getDocumentNumber())
                        .documentType(user.getDocumentType())
                        .address(user.getAddress())
                        .name(user.getName())
                        .password(user.getPassword())
                        .role(user.getRole())
                        .status(user.isStatus())
                        .createdAt(user.getCreatedAt())
                        .updatedAt(user.getUpdatedAt())
                        .build();

      }

}
