package com.bcorp.signup.domain.user.dal;


import com.bcorp.signup.web.model.CreateUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryUserRepository implements UserRepository {

  private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
  private final Map<String, User> storage;
  private final PasswordEncoder passwordEncoder;

  public InMemoryUserRepository(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
    this.storage = new ConcurrentHashMap<>();
  }

  @Override
  public User createUser(CreateUserDTO dto) {
    User user =
        new User(
            UUID.randomUUID().toString(),
            dto.email(),
            dto.username(),
            passwordEncoder.encode(dto.password()));

    storage.put(user.getUsername(), user);

    return user;
  }

  @Override
  public User findByUsername(String username) {
    return storage.get(username);
  }
}
