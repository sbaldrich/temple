package com.bcorp.signup.domain.user;

import com.bcorp.signup.domain.user.dal.UserRepository;
import com.bcorp.signup.web.model.CreateUserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDO registerUser(final CreateUserDTO createUserDTO) {
        final var user = userRepository.createUser(createUserDTO);
        return UserDO.create(user);
    }
}
