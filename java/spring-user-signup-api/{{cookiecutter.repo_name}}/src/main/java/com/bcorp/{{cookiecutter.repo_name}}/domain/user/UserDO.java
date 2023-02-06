package com.bcorp.signup.domain.user;

import com.bcorp.signup.domain.user.dal.User;

import java.time.Instant;
public record UserDO(String username, String email, Instant createdAt) {
  public static UserDO create(final User user) {
    return new UserDO(user.getUsername(), user.getEmail(), user.getCreatedAt());
  }
}
