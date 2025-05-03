package com.nocode.main.application.service;

import com.nocode.main.application.mapper.CategoryDtoMapper;
import com.nocode.main.application.mapper.UserDtoMapper;
import com.nocode.main.application.usecases.IAuthService;
import com.nocode.main.domain.model.Category;
import com.nocode.main.domain.model.User;
import com.nocode.main.domain.model.dto.AuthDto;
import com.nocode.main.domain.model.dto.UserDto;
import com.nocode.main.domain.model.dto.request.auth.Login;
import com.nocode.main.domain.model.dto.request.user.CreateUser;
import com.nocode.main.domain.port.IAuthRepositoryPort;
import com.nocode.main.infrastructure.shared.exception.ConflictException;
import com.nocode.main.infrastructure.shared.exception.ResourceNotFoundException;
import com.nocode.main.infrastructure.shared.util.AesUtil;
import com.nocode.main.infrastructure.shared.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements IAuthService {

    private final IAuthRepositoryPort authRepositoryPort;

    public AuthService(final IAuthRepositoryPort authRepositoryPort) {
        this.authRepositoryPort = authRepositoryPort;
    }

    @Override
    public void registerUser(CreateUser request) {
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
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .userName(request.getUserName())
                /*
                 * .email(request.getEmail())
                 * .phone(request.getPhone())
                 * .documentNumber(request.getDocumentNumber())
                 * .documentType(request.getDocumentType())
                 * .address(request.getAddress())
                 * .name(request.getName())
                 * .role(request.getRole())
                 */
                .password(AesUtil.encrypt(request.getPassword()))
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        authRepositoryPort.registerUser(user);

    }

    @Override
    public AuthDto login(Login request) {
        Optional<User> user = authRepositoryPort.findByUserName(request.getUserName());

        if (user.isPresent()) {
            User userEntity = user.get();
            if (AesUtil.decrypt(userEntity.getPassword()).equals(request.getPassword())) {
                return AuthDto.builder()
                        .userName(userEntity.getUserName())
                        .token(new JwtUtil().generateToken(userEntity.getId(), request.getUserName()))
                        .build();
            } else {
                throw new ConflictException("Contraseña incorrecta.");
            }
        } else {
            throw new ConflictException("Usuario no encontrado.");
        }
    }

    @Override
    public UserDto findById(String id) {
        User user = authRepositoryPort.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User", "user", id)
                );

        return UserDtoMapper.toDto(user);
    }
}
