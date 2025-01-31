package springbootacademy.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{ //csrf().disable() meka dnw spring wlin dena default security eka ain kra gnn
        http.csrf().disable()
                .authorizeRequests()
//                .requestMatchers("/api/v1/account/my-account").hasAuthority("admin") me krmeta krot databse eke data saave krnn oni admin,user this type
//                .requestMatchers("/api/v1/longs/my-long").hasAuthority("user")
                //below method we have to sava data in data base ROLE_ADMIN,ROLE_USER this type
                .requestMatchers("/api/v1/account/my-account").hasRole("ADMIN")
                .requestMatchers("/api/v1/longs/my-long").hasRole("USER")
                .requestMatchers("/api/v1/notices/my-notice","/api/v1/user/register").permitAll()
                .and().formLogin().and().httpBasic();
        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("12345")
//                .authorities("admin")
//                .build();
//        return new InMemoryUserDetailsManager(admin);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
