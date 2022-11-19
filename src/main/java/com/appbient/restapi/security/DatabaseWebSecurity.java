package com.appbient.restapi.security;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select * from ( select email username, password, 1 as status from uservolunteer  UNION select email username, password , 1 as status from userong ) as u where username = ?")
        .authoritiesByUsernameQuery("select * from (select email username, IF(role=0, 'ong','volunteer') as role from uservolunteer  UNION select email username, IF(role=0, 'ong','volunteer') as role from userong) as u where username=?");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic(withDefaults());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
//        // Las vistas públicas no requieren autenticación
//        .antMatchers("/",
//        "/volunteer/signup","/ong/signup", "/swagger-ui.html").permitAll()
//        // Todas las demás URLs de la Aplicación requieren autenticación
        .antMatchers(HttpMethod.POST).permitAll()
        .antMatchers(HttpMethod.PUT).permitAll()
        .antMatchers(HttpMethod.DELETE).permitAll()
//        .anyRequest().authenticated();
        .anyRequest().permitAll();
        
        //.and().formLogin().permitAll();
    }
}