package com.example.mobile_store.auth;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private Map<String, UserDetails> users = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        UserDetails user = users.get(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        System.out.println("User loaded successfully: " + user);
        System.out.println("Stored password (encoded): " + user.getPassword());
        return user;
    }


    public CustomUserDetailsService() {

        users.put("user1", User.builder()
                .username("user1")
                .password("{noop}123456")
                .roles("USER")
                .build());

        users.put("user2", User.builder()
                .username("user2")
                .password("{noop}123456")
                .roles("USER")
                .build());

        users.put("user3", User.builder()
                .username("user3")
                .password("{noop}123456")
                .roles("USER")
                .build());

        users.put("user4", User.builder()
                .username("user4")
                .password("{noop}123456")
                .roles("USER")
                .build());

        users.put("admin", User.builder()
                .username("admin" )
                .password("{noop}123456")
                .roles("ADMIN")
                .build());

    }
}
