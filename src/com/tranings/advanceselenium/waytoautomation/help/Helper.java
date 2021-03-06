package com.tranings.advanceselenium.waytoautomation.help;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.tranings.advanceselenium.waytoautomation.pages.DemoSite;
import com.tranings.advanceselenium.waytoautomation.pages.HomePage;
import com.tranings.advanceselenium.waytoautomation.pages.LoginPage;

public class Helper {

	public void navigateToDemoSite(WebDriver driver,String name, String password,Logger logger){
		//direct to Demo Page
		try{
			HomePage home = new HomePage();
			home.directToDemoSite(driver,logger);
			ArrayList<String> windows = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(windows.get(1));
			//click on element to navigate to login page
			DemoSite demo = new DemoSite();
			demo.clickTestingElement(driver,demo.Selectable,logger );
			windows = new ArrayList<String> (driver.getWindowHandles());
			System.out.println(driver.getTitle());
			for(String w:windows){
			if(driver.switchTo().window(w).getTitle().contains("Welcome to the Test Site")){
				System.out.println("on 2nd window");
//				driver.switchTo().window(windows.get(2));
				driver.manage().window().maximize();
				}
			else{
				driver.switchTo().window(driver.getWindowHandle());
			
			}
	
			}
			
			//Perform login functionality
			LoginPage login = new LoginPage();
			login.SignUp(driver, name, password,logger);
		}catch(NoSuchElementException|ElementNotVisibleException|TimeoutException e){
			logger.error(e.getMessage());
		}
		catch(WebDriverException wde){
			logger.error(wde.getMessage());
		}

	}


}