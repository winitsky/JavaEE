package org.internetbank.web.config;

import javax.inject.Inject;

import org.internetbank.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/* @Inject
    private UserDetailsService userDetailsService;*/
	@Autowired
	@Qualifier("userDetailsServiceImpl")
	private UserDetailsService userDetailsService;
    
    
    
    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/resources/**", "/**").permitAll()
                .anyRequest().permitAll()
                .and();

        http.formLogin()
                .loginPage("/userlogin")
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/userlogin?error")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll();

        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/userlogin?logout")
                .invalidateHttpSession(true);

        http.authorizeRequests()
                .and()
                .formLogin()
                .defaultSuccessUrl("/userpage", false);
        http.authorizeRequests()
        .antMatchers("/", "/userlogin","/regestration").permitAll()
        .antMatchers("/main/**","/payment/**").access("hasRole('internal payment')|| hasRole('foreign payments')");
        
        
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/Access_Denied");
 

    }


}
