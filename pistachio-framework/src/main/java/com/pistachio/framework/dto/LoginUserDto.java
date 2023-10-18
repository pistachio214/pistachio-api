package com.pistachio.framework.dto;

import com.google.gson.annotations.Expose;
import com.pistachio.system.entity.SysUserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @date: 2023/10/17 16:26
 * @author: Pengsy
 */
@Data
@NoArgsConstructor
public class LoginUserDto implements UserDetails, Serializable {
    private SysUserEntity user;

    @Expose(serialize = false)
    private List<GrantedAuthority> grantedAuthorities;

    public LoginUserDto(SysUserEntity user, List<GrantedAuthority> grantedAuthorities) {
        this.user = user;
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return conversionBoolean(user.getAccountNonExpired());
    }

    @Override
    public boolean isAccountNonLocked() {
        return conversionBoolean(user.getAccountNonLocked());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return conversionBoolean(user.getCredentialsNonExpired());
    }

    @Override
    public boolean isEnabled() {
        return conversionBoolean(user.getEnabled());
    }

    private boolean conversionBoolean(Integer i) {
        return i == 1;
    }
}
