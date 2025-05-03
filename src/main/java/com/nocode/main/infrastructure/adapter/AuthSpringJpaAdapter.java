package com.nocode.main.infrastructure.adapter;

import com.nocode.main.domain.model.User;
import com.nocode.main.domain.port.IAuthRepositoryPort;
import com.nocode.main.infrastructure.adapter.entity.UserEntity;
import com.nocode.main.infrastructure.adapter.mapper.UserMapper;
import com.nocode.main.infrastructure.adapter.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class AuthSpringJpaAdapter implements IAuthRepositoryPort {

    private final IUserRepository userRepository;

    public AuthSpringJpaAdapter(final IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        UserEntity entity = UserMapper.toEntity(user);

        userRepository.save(entity);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id)
                .map(UserMapper::toDomain);
    }
}
