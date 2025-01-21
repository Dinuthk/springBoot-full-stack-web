package springbootacademy.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .requestMatchers("/api/v1/account/my-account","/api/v1/longs/my-long").authenticated()
                .requestMatchers("/api/v1/notices/my-notice").permitAll()
                .and().formLogin().and().httpBasic();
        return http.build();
    }
}
