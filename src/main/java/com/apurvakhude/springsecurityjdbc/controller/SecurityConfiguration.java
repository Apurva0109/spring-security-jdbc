package com.apurvakhude.springsecurityjdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //When you use same schema structure as that of the spring security users which is specified in schema.sql and data.sql
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("Select userName, password, enabled from user_table where userName=?")
                .authoritiesByUsernameQuery("Select userName, authority from authorities_table where userName=?");

        //When you use different schema

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncode(){
        return NoOpPasswordEncoder.getInstance();
    }
}
