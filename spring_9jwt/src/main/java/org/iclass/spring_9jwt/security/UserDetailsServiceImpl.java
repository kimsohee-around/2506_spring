package org.iclass.spring_9jwt.security;

import org.iclass.spring_9jwt.entity.UsersEntity;
import org.iclass.spring_9jwt.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity entity = userRepository.findByUsername(username);
        if (entity == null)
            new UsernameNotFoundException("User not found: " + username);

        return UsersPrincipal.create(entity);
    }
}