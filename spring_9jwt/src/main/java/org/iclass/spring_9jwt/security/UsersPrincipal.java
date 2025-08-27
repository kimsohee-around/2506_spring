package org.iclass.spring_9jwt.security;

import java.util.Collection;
import java.util.List;

import org.iclass.spring_9jwt.entity.UsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.lang.Collections;
import lombok.AllArgsConstructor;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
public class UsersPrincipal implements UserDetails {
    // UserDetails 인터페이스를 구현한 특별한 DTO
    // authorities 설정하기 위해서 사용함.
    private Long id;
    private String username;
    private String name;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UsersPrincipal create(UsersEntity user) {
        List<GrantedAuthority> authorities = Collections.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        return new UsersPrincipal(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getPassword(),
                authorities);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}