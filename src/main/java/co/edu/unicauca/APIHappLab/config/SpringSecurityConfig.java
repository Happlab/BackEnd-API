package co.edu.unicauca.APIHappLab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import co.edu.unicauca.APIHappLab.config.filter.JwtRequestFilter;
import co.edu.unicauca.APIHappLab.service.persona_service;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
	private persona_service usuarioService;
    
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.usuarioService);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/**").permitAll()
		/*		.antMatchers(HttpMethod.GET, "/persona/Login/**","/persona/auth/**", "/persona/registro").permitAll()
		.antMatchers(HttpMethod.GET, "/persona/", "/persona/{Email}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.PUT, "/persona/update").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.DELETE, "/persona/desactivar/{Email}", "/persona/delete/{Email}").hasRole("ADMIN")
		.antMatchers("/persona/**").hasRole("ADMIN")*/
		.anyRequest().authenticated()
		.and().cors()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}


}