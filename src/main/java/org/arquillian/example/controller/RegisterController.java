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
 * @author <a href="https://community.jboss.org/people/smikloso">Stefan Miklosovic</a>
 */
@Named
@SessionScoped
@Controller
public class RegisterController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String FAILURE_MESSAGE =
			"Both username and password have to be provided";

	//@Inject
	//private Credentials credentials;

	//@Inject
	//Authenticator authentizator;
	
	@RequestMapping(value= "/" , method = RequestMethod.GET)
	public String registerSimple() {
		System.out.println("inside home controller >>> navigating to index page");
		return "register";
	}
	
	@RequestMapping(value= "/register" , method = RequestMethod.GET)
	public String registerGet(@RequestParam String username, @RequestParam String password) { //  @Valid @ModelAttribute("credentials")Credentials credentials
		 
		System.out.println("inside the register controller >>> GET Method");
		
		String username1 = username ;//credentials.getUsername();
		String password1 = password; //credentials.getPassword();

		User user;
		System.out.println("inside the register controller");
		if (username1 != null && password1 != null) {
			user = new User();
			user.setUsername(username1);
			user.setPassword(password1);
			System.out.println("inside if block " +username1); 
			if(user.getUsername().equalsIgnoreCase("Prabhudatta"))
			{
				return "/login";
			}else {
				return "/register";
			}
			
			/*if (authentizator.register(user)) {
				return "login";
			} else {
				return "register";
			}*/
		} else {
			
			System.out.println("inside else block");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							FAILURE_MESSAGE, FAILURE_MESSAGE));
			return "register";
		}
	}
	
	@RequestMapping(value= "/register.do" , method = RequestMethod.POST)
	public String registerPost(@RequestParam String username, @RequestParam String password) { //  @Valid @ModelAttribute("credentials")Credentials credentials
		 
		System.out.println("inside the register controller >>> POST method");
		
		String username1 = username ;//credentials.getUsername();
		String password1 = password; //credentials.getPassword();

		User user;
		System.out.println("inside the register controller");
		if (username1 != null && password1 != null) {
			user = new User();
			user.setUsername(username1);
			user.setPassword(password1);
			System.out.println("inside if block " +username1); 
			if(user.getUsername().equalsIgnoreCase("Prabhudatta"))
			{
				return "/login";
			}else {
				return "/register";
			}
		} else {
			
			System.out.println("inside else block");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							FAILURE_MESSAGE, FAILURE_MESSAGE));
			return "register";
		}
	}
}
