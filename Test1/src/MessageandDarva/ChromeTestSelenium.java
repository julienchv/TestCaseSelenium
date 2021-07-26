package MessageandDarva;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;

public class ChromeTestSelenium {
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
		
		if(connexion.equals("maison")) {			
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
		// Create a new message
		TestMessage testmsg= new TestMessage();
		
		// Open the messaging service
		String numDossier =bundle.getString("numéroDossier");
		testmsg.AccessMsg(driver,numDossier);
		Thread.sleep(2000);
		
		//MAIL
		String Mel =bundle.getString("email");
		String Object =bundle.getString("Objet");
		//testmsg.newMail(driver,Mel,Object);
		
		//DARVA
		String bodyMsg= "Ceci est un test";
		//testmsg.darvaSD99Solaris(driver);		
		//Thread.sleep(3000);
		//testmsg.darvaSD99Com(driver, bodyMsg);
		//Thread.sleep(3000);
		//testmsg.darvaSD04Cont(driver);
		//Thread.sleep(3000);
		//testmsg.darvaSD09Rep(driver,bodyMsg);
		//Thread.sleep(3000);
		//testmsg.darvaSD18Info(driver,bodyMsg);
		//Thread.sleep(3000);
		//testmsg.darvaRapport(driver);
		//Thread.sleep(10000);
		//testmsg.darvaNoteHnr(driver);
		
		
	}
}
