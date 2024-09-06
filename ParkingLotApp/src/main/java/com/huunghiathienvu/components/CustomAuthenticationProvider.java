/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.components;

import com.huunghiathienvu.pojo.User;
import com.huunghiathienvu.repository.UserRepository;
import com.huunghiathienvu.transientpojo.CustomWebAuthenticationDetails;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author ThienVu
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepo;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        String password = auth.getCredentials().toString();
        String token = ((CustomWebAuthenticationDetails) auth.getDetails()).getToken();
        User user = this.userRepo.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password))
            throw new BadCredentialsException("Invalid username or password");
        
        return new UsernamePasswordAuthenticationToken(user, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
