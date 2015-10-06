package org.internetbank.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.internetbank.service.config.ConfigService;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;



public class AppInitializer implements WebApplicationInitializer {
 
    public void onStartup(ServletContext container) throws ServletException {
 
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebConfig.class);
        ctx.register(ConfigService.class);
        ctx.register(SecurityConfig.class);
        container.addListener(new ContextLoaderListener(ctx)); 
        ctx.setServletContext(container);
 
        ServletRegistration.Dynamic servlet = container.addServlet(
                "dispatcher", new DispatcherServlet(ctx));
 
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
        
        
        /*
         * public class AppInitializer implements WebApplicationInitializer {
 @Override
    public void onStartup(ServletContext container) throws ServletException {
 
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebConfig.class);
        ctx.register(ConfigService.class); 
        ctx.register(SecurityConfig.class);
        container.addListener(new ContextLoaderListener(ctx)); 
        ctx.setServletContext(container);
        Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
 
      
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }


}*/
    }


}