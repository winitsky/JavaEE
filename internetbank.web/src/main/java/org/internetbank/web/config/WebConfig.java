package org.internetbank.web.config;

import org.internetbank.service.impl.UserDetailsServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
 
@Configuration
@EnableWebMvc
@ComponentScan("org.internetbank.web")
public class WebConfig extends WebMvcConfigurerAdapter  {
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("/bootstrap/");
    }
	
	/*@Bean
	public UserDetailsService getUserDetailsService(){
	    return new UserDetailsServiceImpl();
	}*/
     
    @Bean
    public ViewResolver viewResolver() {
        //InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    	UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
    	viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
  
     
   /* @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }*/
}    