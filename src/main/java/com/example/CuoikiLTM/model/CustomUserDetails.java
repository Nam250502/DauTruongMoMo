package com.example.CuoikiLTM.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
@Getter
@Setter

public class CustomUserDetails implements UserDetails {

    public boolean isVerified; // Thêm trường isVerified

    // Các phương thức và trường khác của UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }


    @Override
    public boolean isAccountNonLocked() {
        return isVerified; // Giữ nguyên kiểm tra trạng thái isVerified
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    // Getter và setter cho isVerified
}
