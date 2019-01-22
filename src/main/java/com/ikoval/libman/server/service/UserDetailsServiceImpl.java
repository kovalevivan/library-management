package com.ikoval.libman.server.service;

import com.ikoval.libman.server.domain.MyUser;
import com.ikoval.libman.server.repository.MyUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MyUserRepository userRepository;

    public UserDetailsServiceImpl(MyUserRepository userRepository) {
        this.userRepository = userRepository;
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
