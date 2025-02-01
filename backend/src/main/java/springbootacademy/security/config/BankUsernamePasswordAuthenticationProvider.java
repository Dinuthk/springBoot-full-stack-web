package springbootacademy.security.config;

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
import springbootacademy.security.model.User;
import springbootacademy.security.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;


@Component
public class BankUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        List<User> users = userRepo.findByEmail(username);

        if(users.size()>0){ //user kenek innwd nadd blnw
            if(passwordEncoder.matches(pwd,users.get(0).getPassword())){ // adala useslge pwd ek check
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(users.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(username,pwd,authorities);
            }else {
                throw new BadCredentialsException("Password is invalid");
            }

        }else {
            throw new BadCredentialsException("No user Registered");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
