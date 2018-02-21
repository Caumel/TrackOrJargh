package com.trackorjargh.configurers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;

import com.trackorjargh.component.UserComponent;
import com.trackorjargh.externallogin.FacebookConnectionSignup;
import com.trackorjargh.externallogin.FacebookSignInAdapter;
import com.trackorjargh.javarepository.UserRepository;


@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;
	
	@Autowired
	public CustomAuthFailureHandlerConfigurer customAuthFailureHandlerConfigurer;
	
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;
 
    @Autowired
    private UsersConnectionRepository usersConnectionRepository;
 
    @Autowired
    private FacebookConnectionSignup facebookConnectionSignup;
    
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComponent userComponent;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		//http.authorizeRequests().antMatchers("/**").permitAll();

		// Public Resources
		//http.authorizeRequests().antMatchers("/css/**", "/img/**", "/js/**", "/lib/**").permitAll();

		// Private pages (all other pages)
		//http.authorizeRequests().anyRequest().authenticated();
		
        //http.authorizeRequests().antMatchers("/tests-login/home").hasAnyRole("USER");
        //http.authorizeRequests().antMatchers("/tests-login/admin").hasAnyRole("ADMIN");
		
		// Login form
		http.formLogin().loginPage("/loginusuario");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureHandler(customAuthFailureHandlerConfigurer);

		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

		//Test with h2-database
		http.headers().frameOptions().disable();
		http.csrf().disable();
	}
	
    @Bean
    public ProviderSignInController providerSignInController() {
        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
          .setConnectionSignUp(facebookConnectionSignup);
        
        return new ProviderSignInController(
          connectionFactoryLocator, 
          usersConnectionRepository, 
          new FacebookSignInAdapter(userRepository, userComponent));
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Database authentication provider
		auth.authenticationProvider(authenticationProvider);
	}

}
