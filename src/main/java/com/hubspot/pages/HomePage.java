package com.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hubspot.base.BasePage;
import com.hubspot.util.Constants;
import com.hubspot.util.ElementUtil;



public class HomePage extends BasePage{
	WebDriver driver;
	ElementUtil elementUtil;
	
	By header=By.xpath("//h1[@class='private-page__title']");
	By accountName =By.xpath("//div[contains(text(),'SiliconLabs ')]");
    By accountMenu=By.id("account-menu-container");
    By contactMainTab = By.id("nav-primary-contacts-branch");
	By contatcChildTab = By.id("nav-secondary-contacts");

  public HomePage(WebDriver driver){
	  this.driver=driver;
	  elementUtil=new ElementUtil(driver);//constructorin altinda objeyi initialize yapiyoruz
	
  }
  
  public String getHomePageTitle(){
	  return elementUtil.waitForGetPageTitle(Constants.HOME_PAGE_TITLE);
	  //return driver.getTitle(); 
  }
  public String getHomePageHeader(){
	  return elementUtil.doGetText(header);
	 // return driver.findElement(header).getText(); 
  }
  public String verifyLoggedInAccountName() throws InterruptedException{
	   driver.findElement(accountMenu).click();
	   Thread.sleep(5000);
	   String accountN=elementUtil.doGetText(accountName);
	//  String accountN= driver.findElement(accountName).getText();
	   accountN=accountN.trim();
	   return accountN;
  }
	   
	   private void clickOnContacts(){
			elementUtil.doClick(contactMainTab);
			elementUtil.doClick(contatcChildTab);
		}
		public ContactsPage gotoContactsPage(){
			clickOnContacts();
			return new ContactsPage(driver);
		}		
	   
  }
