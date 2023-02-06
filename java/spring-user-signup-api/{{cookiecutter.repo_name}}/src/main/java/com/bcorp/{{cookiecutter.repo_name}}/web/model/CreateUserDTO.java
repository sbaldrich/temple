package com.bcorp.signup.web.model;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(@NotBlank String username, @NotBlank String email, @NotBlank String password) {}
