package com.mozochek.entity;

import com.mozochek.utils.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.mozochek.utils.LengthConstants.PASSWORD_LENGTH;
import static com.mozochek.utils.LengthConstants.USERNAME_LENGTH;

@Entity
@Table(name = "users", schema = "webdb")
public class User implements UserDetails, IrremovableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = USERNAME_LENGTH)
    private String username;

    @Column(nullable = false, length = PASSWORD_LENGTH)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id", nullable = false)})
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /*
     * Getters and setters
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /*
     * Helper methods
     */
    public void setRole(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }

    public void assignUserRole() {
        setRole(Role.USER);
    }

    public void assignModeratorRole() {
        assignUserRole();
        setRole(Role.MODERATOR);
    }

    public void assignAdminRole() {
        assignModeratorRole();
        setRole(Role.ADMIN);
    }

    public void assignDeveloperRole() {
        assignAdminRole();
        setRole(Role.DEVELOPER);
    }

    public boolean isModerator() {
        return roles.contains(Role.MODERATOR);
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public boolean isDeveloper() {
        return roles.contains(Role.DEVELOPER);
    }

    /*
     * Inherited methods
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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

    /*
     * toString, equals, hashCode
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
