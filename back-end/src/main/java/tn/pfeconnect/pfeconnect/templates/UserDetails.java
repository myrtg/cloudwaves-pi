package tn.pfeconnect.pfeconnect.templates;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface UserDetails {
    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();

    Collection<? extends GrantedAuthority> getAuthorities();
}
