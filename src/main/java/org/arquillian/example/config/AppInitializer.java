package org.arquillian.example.config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext context = new MyWebApplicationContext();
	    servletContext.addListener(new ContextLoaderListener(context));
	    addSpringDispatcherServlet(servletContext, context);
	}
	
	private void addSpringDispatcherServlet(ServletContext servletContext, WebApplicationContext context) {
	    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet",
	      new DispatcherServlet(context));
	    dispatcher.setLoadOnStartup(1);
	    dispatcher.addMapping("/");
	    dispatcher.addMapping("*.do");
	    dispatcher.addMapping("/arquillian-jpa-drone/*");
	    dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	    
	  }
}
