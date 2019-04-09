package com.example.cm.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.cm.entity.cmUserAccountEntity;

public class cmUserDetailsImpl implements UserDetails{

    private cmUserAccountEntity account;

    public cmUserDetailsImpl(final cmUserAccountEntity account) {
        this.account = account;
    }

    //public cmUserDetailsImpl() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> _grntdAuths = new HashSet<GrantedAuthority>();
        String _role = null;

        if(account != null) {
            _role = account.getRolename();
            _grntdAuths.add(new SimpleGrantedAuthority(_role));
        }
        return _grntdAuths;
    }

    @Override
    public String getPassword() {
        if(this.account == null) {
            return null;
        }
        return account.getUserpw();
    }

    @Override
    public String getUsername() {
        if(this.account == null) {
            return null;
        }
        return account.getUserid();
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
