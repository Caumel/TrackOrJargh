package com.trackorjargh.configurers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.trackorjargh.component.UserComponent;

@Configuration
public class UserHandlerConfigurer extends WebMvcConfigurerAdapter {
	@Autowired
	private UserComponent userComponent;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserHandlerInterceptor(userComponent));
	}
}

class UserHandlerInterceptor extends HandlerInterceptorAdapter {
	private UserComponent userComponent;
	
	public UserHandlerInterceptor(UserComponent userComponent) {
		this.userComponent = userComponent;
	}
	
	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
			
			if(userComponent.isLoggedUser()) {
				modelAndView.addObject("userLogged", userComponent.getLoggedUser());
			}		
	}
}
