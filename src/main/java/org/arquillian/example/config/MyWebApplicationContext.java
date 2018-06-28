package org.arquillian.example.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class MyWebApplicationContext extends AnnotationConfigWebApplicationContext {

	public MyWebApplicationContext() {
	    super();
	    setConfigLocation("");
	  }
}
