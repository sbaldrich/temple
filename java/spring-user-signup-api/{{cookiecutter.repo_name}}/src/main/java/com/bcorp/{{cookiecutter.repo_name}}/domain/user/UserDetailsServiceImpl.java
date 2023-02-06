package com.bcorp.signup.domain.user;

import com.bcorp.signup.domain.user.dal.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username " + username);
        }
        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
