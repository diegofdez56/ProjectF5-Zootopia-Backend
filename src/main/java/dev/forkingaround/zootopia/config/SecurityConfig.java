package dev.forkingaround.zootopia.config;

import java.util.Arrays;

import dev.forkingaround.zootopia.services.JpaUserDetailsService;
import dev.forkingaround.zootopia.facades.encryptations.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Value("${api-endpoint}")
        String endpoint;

        JpaUserDetailsService jpaUserDetailsService;

        public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
                this.jpaUserDetailsService = jpaUserDetailsService;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                                .cors(Customizer.withDefaults())
                                .csrf(csrf -> csrf.disable())
                                .formLogin(form -> form
                                                .loginPage(endpoint + "/login")
                                                .defaultSuccessUrl("/dashboard", true)
                                                .permitAll())
                                .logout(out -> out
                                                .logoutUrl(endpoint + "/logout")
                                                .deleteCookies("JSESSIONID"))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
                                                .permitAll()
                                                .requestMatchers("/api/v1/login").permitAll()
                                                .requestMatchers(HttpMethod.POST, endpoint + "/register").permitAll()
                                                .requestMatchers(HttpMethod.GET, endpoint + "/login").hasAnyRole("USER", "ADMIN")
                                                .requestMatchers(HttpMethod.GET, endpoint + "/dashboard").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.GET, endpoint + "/forms").hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.GET, endpoint + "/animals/all").permitAll()
                                                .requestMatchers(HttpMethod.POST, endpoint + "/animals/add")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, endpoint + "/animals/update/{id}")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, endpoint + "/animals/delete/{id}")
                                                .hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .userDetailsService(jpaUserDetailsService)
                                .httpBasic(Customizer.withDefaults())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

                http.headers(header -> header.frameOptions(frame -> frame.sameOrigin()));

                return http.build();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowCredentials(true);
                configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173","https://f5-forking-around.github.io"));
                configuration.addAllowedMethod("*");
                configuration.addAllowedHeader("*");
                configuration.addExposedHeader("Authorization");

                // configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT",
                // "DELETE"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

        @Bean
        PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        Base64Encoder base64Encoder() {
                return new Base64Encoder();
        }

        @Bean
        public InMemoryUserDetailsManager userDetailsManager() {

                UserDetails admin = User.builder()
                                .username("admin")
                                .password("{bcrypt}$2a$12$zMUgGcYGCb2c/vwT9s12Q.380ORJIP0NgN9NmgX6pyEf.bm6fHTiK") // 1234

                                .roles("ADMIN")
                                .build();

                return new InMemoryUserDetailsManager(admin);
        }

}