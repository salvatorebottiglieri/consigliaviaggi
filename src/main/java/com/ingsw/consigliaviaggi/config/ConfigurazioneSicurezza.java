package com.ingsw.consigliaviaggi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class ConfigurazioneSicurezza extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";


    @Autowired
    //@Qualifier("servizioDatiUtenteImpl")
    private UserDetailsService servizioDatiUtente;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(getPasswordEncoder().encode("admin")).roles(ADMIN);
        auth.userDetailsService(servizioDatiUtente);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, "/all/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole(ADMIN)
                .antMatchers("/user/**").hasRole(USER)
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/all/**").permitAll()
                .and()
                .formLogin();
        http.csrf()
                .ignoringAntMatchers("/h2-console/**");
        http.headers()
                .frameOptions()
                .sameOrigin();

        http.logout();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

