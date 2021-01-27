package ru.veqveq.lesson11.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.veqveq.lesson11.services.UserService;

@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private Logger logger = LoggerFactory.getLogger(SecurityConfig.class.getName());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Dao Authentication Provider");
        http.authorizeRequests()
                .antMatchers("/score/get/current").authenticated()
                .antMatchers("/score/get/{\\d+}").hasRole("ADMIN") // ROLE_ADMIN
                .antMatchers("/score/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}