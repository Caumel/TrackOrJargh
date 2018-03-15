package com.trackorjargh.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@Order(1)
public class SecurityConfigurerApiRest extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/api/**");	
		
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/borrarserie/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/borrarpelicula/**").hasAnyRole("ADMIN");	
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/borrarlibro/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/borrarusuario/**").hasAnyRole("ADMIN");
		
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/serie/borrarcomentario/**").hasAnyRole("MODERATOR", "ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/pelicula/borrarcomentario/**").hasAnyRole("MODERATOR", "ADMIN");	
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/libro/borrarcomentario/**").hasAnyRole("MODERATOR", "ADMIN");
		
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/editarserie").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/editarpelicula").hasAnyRole("ADMIN");	
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/editarlibro").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/editarusuario").hasAnyRole("ADMIN");
		
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/agregarserie").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/agregarpelicula").hasAnyRole("ADMIN");	
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/agregarlibro").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/agregarusuario").hasAnyRole("ADMIN");
		
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/serie/agregarcomentario/**").hasRole("USER");	
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/pelicula/agregarcomentario/**").hasRole("USER");	
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/libro/agregarcomentario/**").hasRole("USER");	
		
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/serie/agregarpuntos/**").hasRole("USER");	
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/pelicula/agregarpuntos/**").hasRole("USER");	
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/libro/agregarpuntos/**").hasRole("USER");	
		
		// Other URLs can be accessed without authentication
		http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement with ng2)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Database authentication provider
		auth.authenticationProvider(authenticationProvider);
	}
}
