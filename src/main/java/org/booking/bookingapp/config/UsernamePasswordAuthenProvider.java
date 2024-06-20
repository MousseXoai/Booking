package org.booking.bookingapp.config;

import org.booking.bookingapp.exception.ApiRequestException;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.exception.UnauthorizeException;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UsernamePasswordAuthenProvider implements AuthenticationProvider {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        List<Users> users = usersRepository.findByEmail(username);
        if (users.size() > 0) {
            if(users.get(0).getActive().equals(false)){
                throw new UnauthorizeException("This user has been banned");
            }
            if (passwordEncoder.matches(pwd, users.get(0).getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(users.get(0).getRoleId().getRoleName()));
            } else {
                throw new UnauthorizeException("Invalid password!");
            }
        }else {
            throw new NotFoundException("No user registered with this details!");
        }
    }

    private Set<GrantedAuthority> getGrantedAuthorities(String authorities) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + authorities.toUpperCase()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
