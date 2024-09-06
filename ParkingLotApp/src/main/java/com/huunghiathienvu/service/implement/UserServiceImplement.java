/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.service.implement;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.huunghiathienvu.pojo.User;
import com.huunghiathienvu.repository.UserRepository;
import com.huunghiathienvu.service.MFATokenManagerService;
import com.huunghiathienvu.service.UserService;
import com.huunghiathienvu.transientpojo.MfaTokenData;
import dev.samstevens.totp.exceptions.QrGenerationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThienVu
 */
@Service("userDetailsService")
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private MFATokenManagerService mfaTokenManager;

    @Override
    public User getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid User!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getUserRole()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.userRepo.authUser(username, password);
    }

    @Override
    public User addUser(Map<String, String> params, MultipartFile avatar) {
        User u = new User();
        u.setFirstName(params.get("first_name"));
        u.setLastName(params.get("last_name"));
        u.setEmail(params.get("email"));
        u.setUsername(params.get("username"));
        u.setPassword(this.passEncoder.encode(params.get("password")));
        u.setPhoneNumber(params.get("phone_number"));
        u.setUserRole("ROLE_USER");
        // u.setSecret(mfaTokenManager.generateSecretKey());
        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.userRepo.addUser(u);
        return u;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepo.getAllUsers();
    }

    @Override
    public MfaTokenData mfaSetup(String username) throws UnknownError, QrGenerationException {
        User user = this.userRepo.getUserByUsername(username);
        if (user == null) {
            // we will ignore in case account is not verified or account does not exists
            throw new UnknownError("unable to find account or account is not active");
        }
        return new MfaTokenData(mfaTokenManager.getQRCode(user.getSecret()), user.getSecret());
    }

    @Override
    public void changeUser(Map<String, String> params, MultipartFile avatar) {
        User u = this.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        u.setFirstName(params.getOrDefault("firstName", u.getFirstName()));
        u.setLastName(params.getOrDefault("lastName", u.getLastName()));
        u.setEmail(params.getOrDefault("email", u.getEmail()));
        u.setPhoneNumber(params.getOrDefault("phoneNumber", u.getPhoneNumber()));
        // u.setSecret(mfaTokenManager.generateSecretKey());
        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.userRepo.updateUser(u);
    }
}
