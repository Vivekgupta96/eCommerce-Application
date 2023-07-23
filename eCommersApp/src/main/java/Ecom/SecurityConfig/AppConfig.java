package Ecom.SecurityConfig;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class AppConfig {
	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {
       System.out.println("1");
		http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			
		.cors(cors ->{
			
			cors.configurationSource(new CorsConfigurationSource() {
				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
					
				CorsConfiguration cfg= new CorsConfiguration();
				
				cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList("Authorization"));
				return cfg;				
						
				}
			});
		})
		.authorizeHttpRequests(auth ->{
			auth
				.requestMatchers(HttpMethod.POST,"/ecom/customers").permitAll()
				.requestMatchers(HttpMethod.POST,"/ecom/admin").permitAll()
				.requestMatchers(HttpMethod.GET,"/ecom/signIn").permitAll()
				.requestMatchers(HttpMethod.DELETE, "/ecom/customers/**","/ecom/products/**","/ecom/product-review/**","ecom/cart/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET,"/ecom/products/**").hasAnyRole("ADMIN","USER")
				.requestMatchers(HttpMethod.PUT,"/ecom/products/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST,"/ecom/products/**").hasRole("ADMIN")
				.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
				.anyRequest().authenticated();
				})
			.csrf(csrf -> csrf.disable())
			.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
			.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
			.formLogin(Customizer.withDefaults())
			.httpBasic(Customizer.withDefaults());
		
		
		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("2");
		return new BCryptPasswordEncoder();

	}
	

}
