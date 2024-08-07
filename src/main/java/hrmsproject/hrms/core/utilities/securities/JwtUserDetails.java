package hrmsproject.hrms.core.utilities.securities;

import hrmsproject.hrms.entities.concretes.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {
    private int id;
    private String email;
    private String password;
    public Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(int id, String email,String password,Collection<? extends GrantedAuthority> authorities)
    {
        this.id = id;
        this.email=email;
        this.password = password;
        this.authorities = authorities;
    }

    public static JwtUserDetails create(User user)
    {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("user"));
        return new JwtUserDetails(user.getId(),user.getEmail(), user.getPassword(),authorityList);
    }


    @Override
    public String getUsername() {
        return email;
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
