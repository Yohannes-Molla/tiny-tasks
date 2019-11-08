package com.coyoapp.tinytask.auth;

import com.coyoapp.tinytask.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

  private User user;

  public CustomUserDetails(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if(null == user.getRoles()) {
      return Collections.emptySet();
    }
    Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
    user.getRoles().forEach(role -> {
      grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole().toUpperCase()));
    });

    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return this.user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getUsername();
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
