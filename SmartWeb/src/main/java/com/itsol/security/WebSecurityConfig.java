package com.itsol.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Disable CSRF (cross site request forgery)
		http.csrf().disable();

		// No session will be created or used by spring security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Entry points
		http.authorizeRequests()//
				.antMatchers(HttpMethod.POST, "/login").permitAll()//
				.antMatchers(HttpMethod.POST, "/signup").permitAll()//
				.antMatchers(HttpMethod.POST, "/account").permitAll()//
				.antMatchers(HttpMethod.GET, "/category-job/**").permitAll()//
				.antMatchers(HttpMethod.GET, "/category-news/**").permitAll()//
				.antMatchers(HttpMethod.GET, "/city/**").permitAll()//
				.antMatchers(HttpMethod.GET, "/job/**").permitAll()//
				.antMatchers(HttpMethod.GET, "/news/**").permitAll()//
				.antMatchers(HttpMethod.GET, "/slider/**").permitAll()//
				.antMatchers(HttpMethod.GET, "/images/**").permitAll()//
				.antMatchers(HttpMethod.GET, "/imagesSlider/**").permitAll()//
				.antMatchers(HttpMethod.GET, "/account/**").permitAll()//
				.antMatchers(HttpMethod.GET, "/apply/**").permitAll()//
				.antMatchers(HttpMethod.GET, "/setting/**").permitAll()//
				.antMatchers(HttpMethod.GET, "/sendMail/**").permitAll()//
				.antMatchers(HttpMethod.GET, "/setting/**").permitAll()//
				.antMatchers(HttpMethod.POST, "/user/**").permitAll()//
				.antMatchers("/cv/**").permitAll();//

				// Disallow everything else..
//				.anyRequest().authenticated();

		// If a user try to access a resource without having enough permissions
		//http.exceptionHandling().accessDeniedPage("/login");

		// Apply JWT
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

		// Optional, if you want to test the API from a browser
		// http.httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Allow swagger to be accessed without authentication
		web.ignoring().antMatchers("/v2/api-docs")//
				.antMatchers("/swagger-resources/**")//
				.antMatchers("/swagger-ui.html")//
				.antMatchers("/configuration/**")
				.antMatchers("/webjars/**")//
				.antMatchers("/public");//
	}

}