package com.profiles.candiadate.service;

import com.profiles.candiadate.entity.User;
import com.profiles.candiadate.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =repository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return null;
    }
}
