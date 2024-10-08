package org.example.sivillage.global.config;

import lombok.RequiredArgsConstructor;
import org.example.sivillage.auth.application.CustomUserDetailsService;
import org.example.sivillage.global.common.BaseExceptionHandlerFilter;
import org.example.sivillage.global.common.jwt.JwtAuthenticationFilter;
import org.example.sivillage.global.common.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setExposedHeaders(List.of("Authorization"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, BaseExceptionHandlerFilter baseExceptionHandlerFilter, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        .requestMatchers("/api/category/admin/**", "/api/product/admin/**",
                                "/api/purchase/admin/**", "/api/brand/admin/**",
                                "/api/size/admin/**", "/api/color/admin/**").hasAuthority("ADMIN")

                        .requestMatchers("/api/vendor/**", "/api/event/vendor/**",
                                "/api/product/vendor/**", "/api/product/answer/vendor/**",
                                "/api/product/image/vendor/**",
                                "/api/product/policy/vendor/**",
                                "/api/product/option/vendor/**").hasAnyAuthority("VENDOR", "ADMIN")

                        .requestMatchers("/api/brand-like/member/**", "/api/cart/member/**",
                                "/api/product-like/member/**", "/api/pay/member/**",
                                "/api/purchase/member/**", "/api/product/question/member/**",
                                "/api/review/member/**", "/api/review-like/member/**",
                                "/api/shipping-address/member", "/api/size-info/member")
                        .hasAnyAuthority("MEMBER", "ADMIN")

                        .anyRequest().permitAll()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(baseExceptionHandlerFilter, JwtAuthenticationFilter.class)
                .addFilter(corsFilter());

        return http.build();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService);
//    }
}