package iam.phomenko.clothes.domain.users;

import iam.phomenko.clothes.domain.clothes.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    private Long id;

    private String username;
    private String password;
    private boolean isEnabled;

    @OneToMany
    private List<Collection> collections;

    @ManyToOne
    private Role role;

    private BigDecimal balance;

    @Override
    public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
