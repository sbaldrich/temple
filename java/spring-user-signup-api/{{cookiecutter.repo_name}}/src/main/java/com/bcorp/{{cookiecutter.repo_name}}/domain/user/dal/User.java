package com.bcorp.signup.domain.user.dal;

import java.time.Instant;
import java.util.Objects;

public class User {
    private final String id;
    private final String email;
    private final String username;

    private final String password;
    private final Instant createdAt;

    public User(String id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        String sb = "User{" + "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                '}';
        return sb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id)
                && email.equals(user.email)
                && username.equals(user.username)
                && createdAt.equals(user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, createdAt);
    }
}
