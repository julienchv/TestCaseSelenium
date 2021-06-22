package DemandeEnlevement;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemandeEnlevement {
	public static WebDriver driver;
	public static String baseUrl;
	public static String caseId;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		// connection to salesforce
		System.setProperty("webdriver.chrome.driver","D:\\TestSelenium\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://bcaexpertise--int.my.salesforce.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[@class='button mb24 secondary wide']")).click();
				
		//house connection
		String username = "julien.chauvette@bca.fr";
		String password = "JCGEBCA";
		driver.findElement(By.xpath("//input[@name='UserName']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//span[@id='submitButton']")).click();
				
				
		// Set Base URL
		URL currentUrl = new URL(driver.getCurrentUrl());
		baseUrl = currentUrl.getProtocol() + "://" + currentUrl.getHost();
				
		//Open the Demande d'enlèvement window
		TestDmdEnl demande= new TestDmdEnl();
		demande.AccessDmdEnl(driver);
		
		//Choose which best bidder or none
		String endTheDmd="Send"; // You have to choose between "Send" or "Delete"
		String receipt ="Attestation"; //You have to choose between "Attestation", "Impossibilite" or "Cancel"
		demande.dmdEnlBstBidder(driver, endTheDmd,receipt);
		String departement= "93";	
		//demande.dmdEnlNone(driver,departement,endTheDmd,receipt);
	}

}
