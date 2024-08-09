package dev.forkingaround.zootopia.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import dev.forkingaround.zootopia.services.JpaUserDetailsService;
import dev.forkingaround.zootopia.facades.encryptations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                                .cors(withDefaults())
                                .csrf(csrf -> csrf.disable())
                                .formLogin(form -> form.disable())
                                .logout(out -> out
                                                .logoutUrl(endpoint + "/logout")
                                                .deleteCookies("JSESSIONID"))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**"))
                                                .permitAll()
                                                .requestMatchers(HttpMethod.POST, endpoint + "/register").permitAll()
                                                .requestMatchers(HttpMethod.GET, endpoint + "/login")
                                                .hasAnyRole("USER", "ADMIN")

                                                .requestMatchers(HttpMethod.GET, endpoint + "/animals/all").permitAll()
                                                .requestMatchers(HttpMethod.POST, endpoint + "/animals/add")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, endpoint + "/animals/update/{id}")
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, endpoint + "/animals/delete/{id}")
                                                .hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .userDetailsService(jpaUserDetailsService)
                                .httpBasic(withDefaults())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));

                http.headers(header -> header.frameOptions(frame -> frame.sameOrigin()));

                return http.build();
        }

        // @Bean
        // CorsConfigurationSource corsConfiguration() {
        // CorsConfiguration configuration = new CorsConfiguration();
        // configuration.setAllowCredentials(true);
        // configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        // configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT",
        // "DELETE"));
        // configuration.setAllowedHeaders(Arrays.asList("*"));
        // UrlBasedCorsConfigurationSource source = new
        // UrlBasedCorsConfigurationSource();
        // source.registerCorsConfiguration("/**", configuration);
        // return source;
        // }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowCredentials(true);
                configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // Asegúrate de que este origen
                                                                                         // es el correcto
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
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

        /*
         * @Bean
         * public InMemoryUserDetailsManager userDetailsManager() {
         * 
         * UserDetails mickey = User.builder()
         * .username("mickey")
         * .password(
         * "{bcrypt}$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO") //
         * password
         * .roles("USER")
         * .build();
         * 
         * UserDetails minnie = User.builder()
         * .username("minnie")
         * .password(
         * "{bcrypt}$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO") //
         * password
         * .roles("ADMIN")
         * .build();
         * 
         * Collection<UserDetails> users = new ArrayList<>();
         * users.add(mickey);
         * users.add(minnie);
         * 
         * return new InMemoryUserDetailsManager(users);
         * }
         */

}