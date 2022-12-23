package nl.belastingdienst.autogarage.security;

import nl.belastingdienst.autogarage.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/part").hasAnyRole("BACKOFFICE", "MECHANIC", "ADMIN")
                .antMatchers("/repair").hasAnyRole("BACKOFFICE", "MECHANIC", "ADMIN")
                .antMatchers("/appointment").hasAnyRole("MECHANIC", "ADMIN")
                .antMatchers("/car").hasAnyRole("MECHANIC", "ADMIN")
                .antMatchers("/customer").hasAnyRole("MECHANIC", "ADMIN")
                .antMatchers("/file").hasAnyRole("MECHANIC", "ADMIN")
                .antMatchers("/user").hasAnyRole("MECHANIC", "ADMIN")
                .antMatchers("/**").hasRole("ADMIN")
                .and().httpBasic();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
