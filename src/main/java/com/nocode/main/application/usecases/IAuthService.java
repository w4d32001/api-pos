package com.nocode.main.application.usecases;

import com.nocode.main.domain.model.dto.AuthDto;
import com.nocode.main.domain.model.dto.UserDto;
import com.nocode.main.domain.model.dto.request.auth.Login;
import com.nocode.main.domain.model.dto.request.user.CreateUser;

public interface IAuthService {

    void registerUser(CreateUser request);

    AuthDto login(Login request);

    UserDto findById(String id);

}
