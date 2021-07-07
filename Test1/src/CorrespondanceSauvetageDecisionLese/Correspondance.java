package CorrespondanceSauvetageDecisionLese;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Correspondance {
	
	public static WebDriver driver;
	public static String baseUrl;
	public static String caseId;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		// connection to salesforce
		System.setProperty("webdriver.chrome.driver","D:\\TestSelenium\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		ResourceBundle bundle = ResourceBundle.getBundle("properties.config");		
		String environnement = bundle.getString("environnement");
		driver.get("https://bcaexpertise"+environnement+".my.salesforce.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[@class='button mb24 secondary wide']")).click();
		String connexion=bundle.getString("connexion");
		
		if(connexion.equals(connexion)) {				
			//house connection
			String username =bundle.getString("username");
			String password =bundle.getString("password");
			driver.findElement(By.xpath("//input[@name='UserName']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='Password']")).sendKeys(password);
			driver.findElement(By.xpath("//span[@id='submitButton']")).click();
		}
				
		// Set Base URL
		URL currentUrl = new URL(driver.getCurrentUrl());
		baseUrl = currentUrl.getProtocol() + "://" + currentUrl.getHost();
		
		// 
		String numDossier =bundle.getString("numéroDossier");
		TestCorrespondance testcorres = new TestCorrespondance();
		//testcorres.CorresDestSauv(driver, numDossier);
		testcorres.CorresDocsCession(driver, numDossier);
	}

}
