package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.MyUser;
import com.ikoval.libman.server.repository.MyUserRepository;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MyUserRepository userRepository;

    public UserDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> opt = userRepository.findByName(username);
        if(opt.isPresent()) {
            MyUser user = opt.get();
            Set<GrantedAuthority> roles = new HashSet<>();
            roles.add(new SimpleGrantedAuthority(user.getRole()));
            return new User(username, user.getPassword(), roles);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public void save(MyUser user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
