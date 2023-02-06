package com.bcorp.signup.domain.user;

import com.bcorp.signup.web.model.CreateUserDTO;

public interface UserService {
    UserDO registerUser(final CreateUserDTO createUserDTO);
}
