package project.timesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import project.timesheet.services.CustomUserDetailService;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http.csrf(csrf-> csrf.disable()).authorizeHttpRequests((auth) -> auth
                       .requestMatchers("/login").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/profile/**").authenticated()
                        .requestMatchers("/users/register").hasAuthority("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(login->login.loginPage("/login")
                        .loginProcessingUrl("/login")
                .usernameParameter("username")
                        .passwordParameter("password")
                                .defaultSuccessUrl("/",true))

                .logout(logout->logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login"))
         .sessionManagement(session -> session // Thêm dòng này
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
        return http.build();
    }
    @Bean
    WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers("/satic/**","/assets/**","/user/**");
    }


}
