package com.vyatsu.task14.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
   private DataSource dataSource;

   @Autowired
   public void setDataSource(DataSource dataSource) {
       this.dataSource = dataSource;
   }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?");
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // (2)
//        auth.inMemoryAuthentication()
//                .withUser("test").password("{noop}1234").roles("USER", "ADMIN");
//    }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .anyRequest().permitAll()
               .antMatchers("/secured/**").hasAnyRole("ADMIN")
               .and()
               .formLogin()
               .loginPage("/login")
               .loginProcessingUrl("/authenticateTheUser")
               .defaultSuccessUrl("/products", true)
               .permitAll()
               .and()
               .logout()
               .logoutUrl("/logout")
               .logoutSuccessUrl("/products")
               .permitAll();
   }
}

