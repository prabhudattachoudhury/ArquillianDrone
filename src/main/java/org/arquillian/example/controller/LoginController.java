/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.arquillian.example.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.arquillian.example.model.Credentials;
import org.arquillian.example.model.User;
import org.arquillian.example.security.Authenticator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 
 */
@Named
@SessionScoped
@Controller
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SUCCESS_MESSAGE = "Welcome";
	private static final String FAILURE_MESSAGE = 
			"Incorrect username and password combination";

	private User currentUser;
	
	@Inject
	private Credentials credentials;
	
	@Inject
	Authenticator authentizator;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password) {
		
		System.out.println("inside login controller >> method post");
		//String username = credentials.getUsername();
		//String password = credentials.getPassword();
		
		if (username == null || password == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							FAILURE_MESSAGE, FAILURE_MESSAGE));
			return null;
		}
		
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		
		if(username.equalsIgnoreCase(user.getUsername()))
		{
			return "/home";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							FAILURE_MESSAGE, FAILURE_MESSAGE));
			return null;			
		}
		/*if (authentizator.login(user)) {
			currentUser = new User(username);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(SUCCESS_MESSAGE));
			return "home.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							FAILURE_MESSAGE, FAILURE_MESSAGE));
			return null;			
		}*/
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(@RequestParam String username, @RequestParam String password) {
		
		System.out.println("inside login controller >> method get");
		//String username = credentials.getUsername();
		//String password = credentials.getPassword();
		
		if (username == null || password == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							FAILURE_MESSAGE, FAILURE_MESSAGE));
			return null;
		}
		
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		
		if(username.equalsIgnoreCase(user.getUsername()))
		{
			return "/home";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							FAILURE_MESSAGE, FAILURE_MESSAGE));
			return null;			
		}
	}
	
	public boolean isLoggedIn() {
		return currentUser != null;
	}
	
	@Produces
	@Named
	public User getCurrentUser() {
		return currentUser;
	}
	
}
