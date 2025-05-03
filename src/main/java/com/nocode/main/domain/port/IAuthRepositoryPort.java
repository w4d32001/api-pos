package com.nocode.main.domain.port;

import com.nocode.main.domain.model.User;

import java.util.Optional;

public interface IAuthRepositoryPort {

    void registerUser(User user);

    Optional<User> findByUserName(String userName);

    Optional<User> findById(String id);

}
