package org.arquillian.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;


@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	 @Autowired
	  @Qualifier("jstlViewResolver")
	  private ViewResolver jstlViewResolver;

	  @Bean
	  @DependsOn({ "jstlViewResolver" })
	  public ViewResolver viewResolver() {
	    return jstlViewResolver;
	  }
	  
	  @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	      int cachePeriod = 3600 * 24 * 15;
	      registry.addResourceHandler("/webapp/**").addResourceLocations("/static/").setCachePeriod(cachePeriod);
	  }

	  @Bean(name = "jstlViewResolver")
	  public ViewResolver jstlViewResolver() {
	    UrlBasedViewResolver resolver = new UrlBasedViewResolver();
	    resolver.setPrefix("/webapp/");
	    resolver.setViewClass(JstlView.class);
	    resolver.setSuffix(".xhtml");
	    return resolver;
	  }

}
