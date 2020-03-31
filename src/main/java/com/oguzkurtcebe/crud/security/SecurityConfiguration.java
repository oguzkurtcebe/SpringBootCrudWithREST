package com.oguzkurtcebe.crud.security;

import static com.oguzkurtcebe.crud.security.SecurityConstants.SIGN_UP_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
   

	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public SecurityConfiguration(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
				.anyRequest().authenticated().and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
				
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	    @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	        auth.inMemoryAuthentication()
	                .withUser("oguz")
	                .password("pass1905")
	                .roles("ADMIN");
	    }

	    @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	        return source;
	    }	
	
	
}
	
	
	
	
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//
//		http.httpBasic().and().authorizeRequests()
//        .antMatchers("/rest/person/*").hasRole("ADMIN")
//        .antMatchers("/rest/persons").hasRole("EDITOR")
//        .and()
//        .csrf().disable();
//		
//		
//	}
//
//	@Autowired
//    public void configureAuthGlobal(AuthenticationManagerBuilder auth){
//        try {
//            auth.inMemoryAuthentication()
//                    .withUser("admin").password(passwordEncoder().encode("password")).roles("ADMIN")
//                    .and()
//                    .withUser("editor").password(passwordEncoder().encode("password")).roles( "EDITOR");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//  }	

