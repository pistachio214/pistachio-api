package com.pistachio.framework.dto;

import com.google.gson.annotations.Expose;
import com.pistachio.system.entity.SysUserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @date: 2023/10/17 16:26
 * @author: Pengsy
 */
@Data
@NoArgsConstructor
public class LoginUserDto implements UserDetails, Serializable {
    private SysUserEntity user;

    //存储权限信息
    private List<String> permissions;

    public LoginUserDto(SysUserEntity user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    // transient关键字,会在Gson序列化过滤掉这个字段
    private transient List<GrantedAuthority> grantedAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (grantedAuthorities != null) {
            return grantedAuthorities;
        }

        //把permissions中字符串类型的权限信息转换成GrantedAuthority对象存入authorities中
        grantedAuthorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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
