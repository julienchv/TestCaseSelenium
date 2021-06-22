package AppelOffre;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAppelOffre {
	public static WebDriver driver;
	public static String baseUrl;

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
		
		
		// Set Base URL then use it for the access
		URL currentUrl = new URL(driver.getCurrentUrl());
		
		baseUrl = currentUrl.getProtocol() + "://" + currentUrl.getHost();
		CreateAppelOffre aploffre=new CreateAppelOffre();
		
		//Access request for proposals
		String act ="publish";// 2 choice : "leave" or "publish"
		String platform="BCA"; //Select between "BCA" or "ACCIAUTO"
		aploffre.RequestProposals(driver,act, platform);
		Thread.sleep(3000);
		aploffre.PropOffre(driver);
				
	}

}
