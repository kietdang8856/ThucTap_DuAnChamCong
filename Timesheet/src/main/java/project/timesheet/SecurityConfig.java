package project.timesheet;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/error", "/403").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/profile/**").authenticated()
                        .requestMatchers("/users/register").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .failureHandler((request, response, exception) -> {
                            HttpSession session = request.getSession(true); // Create session if it does not exist
                            Integer failedAttempts = (Integer) session.getAttribute("failedAttempts");
                            failedAttempts = (failedAttempts != null) ? failedAttempts + 1 : 1;
                            session.setAttribute("failedAttempts", failedAttempts);

                            if (failedAttempts >= 3) {
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
                                response.getWriter().write("Too many failed attempts. Contact administrator.");
                            } else {
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                                response.getWriter().write("Invalid username or password.");
                            }
                        })
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession(false);
                            if (session != null) {
                                session.removeAttribute("failedAttempts");
                            }
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write("success");
                        }))
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login"))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedPage("/403"));

        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers("/satic/**","/assets/**","/user/**");
    }


}
