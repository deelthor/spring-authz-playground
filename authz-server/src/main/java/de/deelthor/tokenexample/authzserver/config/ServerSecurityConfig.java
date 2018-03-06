package de.deelthor.tokenexample.authzserver.config;

import de.deelthor.tokenexample.authzserver.service.TokenAuthenticationUserDetailsService;
import de.deelthor.tokenexample.authzserver.service.UsernamePasswordDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServerSecurityConfig {

    private UsernamePasswordDetailsService service;
    private PasswordEncoder passwordEncoder;

    public ServerSecurityConfig(UsernamePasswordDetailsService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @Configuration
    @Order(1)
    public class BasicAuthConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/token")
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .httpBasic()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(service).passwordEncoder(passwordEncoder);
        }

    }

    @Configuration
    @Order(2)
    public class TokenAuthConfig extends WebSecurityConfigurerAdapter {
        private TokenAuthenticationUserDetailsService service;

        public TokenAuthConfig(TokenAuthenticationUserDetailsService service) {
            this.service = service;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/user")
                    .authorizeRequests()
                    .mvcMatchers(HttpMethod.POST, "/api/user").anonymous()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(authFilter(), RequestHeaderAuthenticationFilter.class)
                    .authenticationProvider(preAuthProvider())
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .csrf().disable();
        }

        @Bean
        public TokenAuthenticationFilter authFilter() {
            return new TokenAuthenticationFilter();
        }

        @Bean
        public AuthenticationProvider preAuthProvider() {
            PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
            provider.setPreAuthenticatedUserDetailsService(service);
            return provider;
        }
    }

}
