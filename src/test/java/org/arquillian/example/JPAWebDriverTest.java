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
package org.arquillian.example;

import java.io.File;
import java.net.URL;

import org.arquillian.example.controller.LoginController;
import org.arquillian.example.controller.RegisterController;
import org.arquillian.example.dao.UserDAO;
import org.arquillian.example.dao.UserDAOException;
import org.arquillian.example.dao.impl.UserDAOImpl;
import org.arquillian.example.model.Credentials;
import org.arquillian.example.model.User;
import org.arquillian.example.security.Authenticator;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author <a href="https://community.jboss.org/people/smikloso">Stefan Miklosovic</a>
 */
public class JPAWebDriverTest extends Arquillian{

    private static final String WEBAPP_SRC = "src/main/webapp/WEB-INF/";

    private static final String USERNAME = "Prabhudatta";

    private static final String PASSWORD = "PASSWORD";

    @FindBy(id = "reg_username")
    WebElement registerUserNameField;

    @FindBy(id = "reg_password")
    WebElement registerPasswordField;

    @FindBy(id = "Register")
    WebElement submitRegistration;

    @FindBy(id = "log_username")
    WebElement loginUserNameField;

    @FindBy(id = "log_password")
    WebElement loginPasswordField;

    @FindBy(xpath = ".//*[contains(text(), 'Log in')]")
    WebElement loginHeader;

    @FindBy(id = "Login")
    WebElement submitLogin;

    // TODO: replace with css selectors
    @FindBy(xpath = ".//*[contains(text(), 'You are signed in as.')]")
    WebElement welcomeMessage;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "arquillian-jpa-drone.war")
                .addClasses(User.class,
                        UserDAO.class,
                        UserDAOImpl.class,
                        UserDAOException.class,
                        Authenticator.class,
                        Credentials.class,
                        LoginController.class,
                        RegisterController.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("jbossas-ds.xml")
                .addAsWebResource(new File(WEBAPP_SRC, "views/login.jsp"))
                .addAsWebResource(new File(WEBAPP_SRC, "views/home.jsp"))
                .addAsWebResource(new File(WEBAPP_SRC, "views/register.jsp"))
                .addAsWebResource(new File(WEBAPP_SRC, "spring-servlet.xml"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(
                        new StringAsset("<faces-config version=\"2.0\"/>"),
                        "faces-config.xml")
              ;
    }

    
   @Drone
    ChromeDriver driver;

    @ArquillianResource
    URL deploymentUrl;
    
    
    @Test
    public void register() {
        // Register
    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\chromedriver.exe");
    	 try {
    		 driver.get("http://localhost:8080/arquillian-jpa-drone/register.jsp");
        System.out.println("Login url >>> " +"http://localhost:8080/arquillian-jpa-drone/register.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
        registerUserNameField.sendKeys(USERNAME);
        registerPasswordField.sendKeys(PASSWORD);

        // ensure that HTTP request is fired and wait for the response to be delivered
        submitRegistration.click();
        // And try to log in
        Assert.assertTrue(loginUserNameField.isDisplayed(), "User should be registered and redirected to login page!");
       
        loginUserNameField.clear();
        loginUserNameField.sendKeys(USERNAME);
        loginPasswordField.clear();
        loginPasswordField.sendKeys(PASSWORD);

        // ensure that HTTP request is fired and wait for the response to be delivered
        submitLogin.click();
      //  driver.get(deploymentUrl + "home.xhtml");
        Assert.assertTrue( welcomeMessage.isDisplayed(),"User should be at welcome page!");
    }
}
