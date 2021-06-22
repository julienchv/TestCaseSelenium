import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TestMessage {
	
	public void AccessMsg(WebDriver driver,String dossier) throws MalformedURLException, InterruptedException{		
		//select the file 
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[text()='"+dossier+"']")).click();
		
		//click on message to open the window				
		Thread.sleep(2000);
		URL currentUrl = new URL(driver.getCurrentUrl());
		String caseId = (currentUrl.getPath().split("/").length > 4) ? currentUrl.getPath().split("/")[4] : "";
		String linkListMessage = ChromeTestSelenium.baseUrl + "/lightning/r/Case/" + caseId + "/related/MessagesDossier__r/view"
		+"?ws=%2Flightning%2Fr%2FCase%2F"+caseId+"%2Fview";
		driver.get(linkListMessage);
		
		// New message
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"brandBand_3\"]/div/div/div/div[1]/div[1]/div[2]/ul/li/a")).click();
	}
	public void newMail(WebDriver driver, String Mail, String Object) throws InterruptedException {
		// Mail
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).sendKeys(Keys.DOWN);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).sendKeys(Keys.ENTER);	
		
		// Complete recipient
		Thread.sleep(1000);	
		driver.findElement(By.xpath("//input[@name='comboboxDestinataire']")).sendKeys(Mail);
		driver.findElement(By.xpath("//input[@name='comboboxDestinataire']")).sendKeys(Keys.ENTER);			
		
		// Complete recipient Cc
		// driver.findElement(By.xpath("//input[@name='comboboxDestinataireCc']")).sendKeys("adresse");
		
		// Complete Object
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='objet']")).sendKeys(Object);
		
		/* Insert text
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//p[text()='gc@bca.fr']")).sendKeys("Ceci est un test");
		*/
		
		/*Add an attachment
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@title='Ajouter PJ']")).click();
		*/
	
		// Send the message
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.xpath("//button[@title='Envoyer']"));
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
		
		Thread.sleep(1000);
		String MAIL="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/div/div/div/ul[2]/li[4]/a";
		driver.findElement(By.xpath(MAIL)).click();
		String xpsts="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-message___-message__c___-v-i-e-w/forcegenerated-flexipage_message_message__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/slot/flexipage-component2/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_message__c___012000000000000aaa___compact___view___recordlayout2/force-highlights2/div[1]/div[2]/slot/slot/force-highlights-details-item[2]/div/p[2]/slot/lightning-formatted-text";
		String status=driver.findElement(By.xpath(xpsts)).getText();
		System.out.println(status);
		
	}
	public void darvaSD99Solaris(WebDriver driver) throws InterruptedException {
		// DARVA
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).sendKeys(Keys.ENTER);
		
		//SD99-Solaris
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un Type de message ']")).click();	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un Type de message ']")).sendKeys(Keys.ENTER);
	
		//Select a wording
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner une formule']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner une formule']")).sendKeys(Keys.ENTER);
		
		// Select the recipient(s)
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@title='Choisir destinataire(s)']")).click();		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String recippath ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/div/c-lwc007_-i-h-m-envoi-message-darva/section[2]/div/div/div/lightning-datatable/div[2]/div/div/table/tbody/tr[1]/td[2]/lightning-primitive-cell-checkbox/span/label/span[1]";	
		driver.findElement(By.xpath(recippath)).click(); // full path of the span
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@title='Valider Choix Acteur(s)']")).click();
		
		/* Add an attachment
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@title='Ajouter PJ']")).click();
		*/
		
		// Send
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@title='Envoyer']")).click();
		
		//Check if the message is visible on the open screen name messages
		driver.findElement(By.xpath("//button[@name='refreshButton']")).click();
	}
	
	public void darvaSD99Com (WebDriver driver, String bodymsg) {
		// DARVA
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).sendKeys(Keys.ENTER);
		
		//SD99- Commentaires
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un Type de message ']")).click();	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='4199']")).click();
	
		//Select the recipient(s)
		driver.findElement(By.xpath("//button[@title='Choisir destinataire(s)']")).click();
		String recipxpath ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/div/c-lwc007_-i-h-m-envoi-message-darva/section[2]/div/div/div/lightning-datatable/div[2]/div/div/table/tbody/tr[2]/td[2]/lightning-primitive-cell-checkbox/span/label/span[1]";
		driver.findElement(By.xpath(recipxpath)).click(); // full path of the span
		driver.findElement(By.xpath("//button[@title='Valider Choix Acteur(s)']")).click();
		
		// Add a comments
		driver.findElement(By.xpath("//textarea[@name='comments']")).sendKeys(bodymsg);
		
		/* Add an attachment
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@title='Ajouter PJ']")).click();
		*/
		
		// Send
		driver.findElement(By.xpath("//button[@title='Envoyer']")).click();
		
		//Check if the message is visible on the open screen name messages
		driver.findElement(By.xpath("//button[@name='refreshButton']")).click();
	}
	public void darvaSD04Cont(WebDriver driver) {
		// DARVA
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).sendKeys(Keys.ENTER);
		
		//SD04- Contestabilité de l'offre
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un Type de message ']")).click();	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='4104']")).click();
		
		// Send
		driver.findElement(By.xpath("//button[@title='Envoyer']")).click();
		
		//Check if the message is visible on the open screen name messages
		driver.findElement(By.xpath("//button[@name='refreshButton']")).click();
	}
	public void darvaSD09Rep(WebDriver driver, String bodymsg) {
		// DARVA
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).sendKeys(Keys.ENTER);
		
		//SD09- Réponse offre de cession
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un Type de message ']")).click();	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='4109']")).click();
		
		// Décision
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner une option']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner une option']")).sendKeys(Keys.ENTER);
		
		// Add a comments
		driver.findElement(By.xpath("//textarea[@name='comments']")).sendKeys(bodymsg);
		
		// Send
		driver.findElement(By.xpath("//button[@title='Envoyer']")).click();
		
		//Check if the message is visible on the open screen name messages
		driver.findElement(By.xpath("//button[@name='refreshButton']")).click();
	}
	public void darvaSD18Info(WebDriver driver, String bodymsg) {
		// DARVA
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).sendKeys(Keys.ENTER);
		
		//SD18- Information sur la procédure V.E
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un Type de message ']")).click();	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='4118']")).click();
		
		// Add a comments
		driver.findElement(By.xpath("//textarea[@name='comments']")).sendKeys(bodymsg);
		
		// Send
		driver.findElement(By.xpath("//button[@title='Envoyer']")).click();
		
		//answer to the question
		driver.findElement(By.xpath("//button[@title='OK']")).click();
		
		//Check if the message is visible on the open screen name messages
		driver.findElement(By.xpath("//button[@name='refreshButton']")).click();
	}
	public void darvaRapport(WebDriver driver) {
		// DARVA
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).sendKeys(Keys.ENTER);
		
		//Rapport (RE)
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un Type de message ']")).click();	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='1500']")).click();
			
		// Send
		driver.findElement(By.xpath("//button[@title='Envoyer']")).click();
		
		//Check if the message is visible on the open screen name messages
		driver.findElement(By.xpath("//button[@name='refreshButton']")).click();
	}
	public void darvaNoteHnr(WebDriver driver) {
		// DARVA
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).sendKeys(Keys.ENTER);
		
		//Rapport (RE)
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un Type de message ']")).click();	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='2000']")).click();
			
		// Send
		driver.findElement(By.xpath("//button[@title='Envoyer']")).click();	
		
		//Check if the message is visible on the open screen name messages
		driver.findElement(By.xpath("//button[@name='refreshButton']")).click();
	}
}
 
	

