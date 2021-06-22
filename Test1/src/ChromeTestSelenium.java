
import java.net.MalformedURLException;
import java.net.URL;
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
		// Create a new message
		TestMessage testmsg= new TestMessage();
		
		// Open the messaging service
		testmsg.AccessMsg(driver);
		Thread.sleep(2000);
		
		//MAIL
		String Mel ="julien.chauvette@bca.fr";
		String Object ="Test";		
		testmsg.newMail(driver,Mel,Object);
		
		//DARVA
		String bodyMsg= "Ceci est un test";
		//testmsg.darvaSD99Solaris(driver);
		//testmsg.darvaSD99Com(driver, bodyMsg);
		//testmsg.darvaSD04Cont(driver);
		//testmsg.darvaSD09Rep(driver,bodyMsg);
		//testmsg.darvaSD18Info(driver,bodyMsg);
		//testmsg.darvaRapport(driver);
		//testmsg.darvaNoteHnr(driver);
		
	}
}
