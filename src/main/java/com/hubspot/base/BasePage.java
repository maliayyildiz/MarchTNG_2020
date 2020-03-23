package com.hubspot.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
public WebDriver driver;
public Properties prop;
public static String flash;

public WebDriver initialize_driver(Properties prop){
	//String browser="chrome";
	String browser=prop.getProperty("browser"); //get property keylerle calisir
	String headless=prop.getProperty("headless");//headles
	flash=prop.getProperty("elementflash");
	
	if(browser.equalsIgnoreCase("chrome")){
		WebDriverManager.chromedriver().setup();//bu 80 i kullaniyor sorun olursa
		System.setProperty("webdriver.chrome.driver", "/Users/zekeriyaiyimaya/Documents/Drivers/chromedriver");
		if(headless.equalsIgnoreCase("yes")){    //setProperty i kullaniriz
			ChromeOptions co=new ChromeOptions();
			co.addArguments("--Headless");
			driver=new ChromeDriver(co);
		}else{
			
		
		driver=new ChromeDriver();
		}
	}
	else if(browser.equalsIgnoreCase("firefox")){
		WebDriverManager.firefoxdriver().setup();
		if(headless.equalsIgnoreCase("yes")){
			FirefoxOptions fo=new FirefoxOptions();
			fo.addArguments("--Headless");
			driver=new FirefoxDriver(fo);
		}else{
		driver=new FirefoxDriver();
		}
	}
	driver.manage().window().fullscreen();
	driver.manage().deleteAllCookies();
    driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
	
    driver.get(prop.getProperty("url"));
    try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return driver;
	}
    public Properties initialize_properties(){
    	    prop=new Properties();
    	    try {
			FileInputStream ip=new FileInputStream("/Users/zekeriyaiyimaya/Documents/workspace/MarchTNG_2020/"
					+ "src/main/java/config/hubspot/config/config.properties");
			prop.load(ip);
			} catch (IOException e){   //handle exception
				e.printStackTrace(); //bir hata olusunca logtan hatayi takip etmemizi saglar
			}
    	        return prop;
    }
    public void quitBrowser(){
    	   try {
    		   driver.quit();
			
		} catch (Exception e) {
			System.out.println("Some exception occured while quitting browser");
		}
    }
    public void closeBrowser(){
 	   try {
 		   driver.close();
			
		} catch (Exception e) {
			System.out.println("Some exception occured while closing browser");
		}
 }
  
    
}