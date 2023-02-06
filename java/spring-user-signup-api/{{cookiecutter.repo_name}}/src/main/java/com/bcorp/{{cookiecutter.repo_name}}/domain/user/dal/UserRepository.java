package com.bcorp.signup.domain.user.dal;


import com.bcorp.signup.web.model.CreateUserDTO;

public interface UserRepository {
  User createUser(final CreateUserDTO user);

  User findByUsername(final String username);
}
