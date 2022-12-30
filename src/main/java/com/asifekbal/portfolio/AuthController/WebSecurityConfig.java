package com.asifekbal.portfolio.AuthController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  protected UserDetailsService userDetailsService() {

    return new UserDetailServiceImpl();

  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {

    DaoAuthenticationProvider authprovider = new DaoAuthenticationProvider();
    authprovider.setPasswordEncoder(passwordEncoder());
    authprovider.setUserDetailsService(userDetailsService());

    return authprovider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
    .antMatchers( "/").permitAll()
    .antMatchers( "/blogpage").permitAll()
    .antMatchers( "/projectpage").permitAll()
    .antMatchers( "/sendmail").permitAll()
    .antMatchers("admin").hasAuthority("admin")
    .antMatchers("/register/**").permitAll()
  
    
    
    .anyRequest().authenticated()
    .and()
      .formLogin()
      .loginPage("/admin_login")
      .permitAll()
    .and()
      .logout().logoutSuccessUrl("/").invalidateHttpSession(true)
      .permitAll()
    .and()
      .exceptionHandling().accessDeniedPage("/403")
    .and()
      .csrf().disable()
      .headers().frameOptions().disable();

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/h2/**")
        .antMatchers("/resources/**", "/static/**", "/lib/**","/pcss/**","/pjs/**","/pfonts/**",
         "/contactform/**", "/css/**", "/js/**", "/img/**","/dataTables/**","/flot/**",
            "/images/**", "/blog/**","/project/**", "/icon/**");
  }

}