package DemandeEnlevement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class TestDmdEnl {
	
	public void AccessDmdEnl(WebDriver driver,String dossier) throws MalformedURLException, InterruptedException{		
		
		//select the file 
		JavascriptExecutor js =(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement linkFile= driver.findElement(By.xpath("//a[text()='"+dossier+"']"));
		js.executeScript("arguments[0].scrollIntoView()", linkFile);
		driver.findElement(By.xpath("//a[text()='"+dossier+"']")).click();
		
		Thread.sleep(2000);
		URL currentUrl = new URL(driver.getCurrentUrl());
		String caseId = (currentUrl.getPath().split("/").length > 4) ? currentUrl.getPath().split("/")[4] : "";
		String linkListMessage = DemandeEnlevement.baseUrl + "/lightning/r/" + caseId + "/related/Demandes_d_enlevement__r/view";
		//+"?ws=%2Flightning%2Fr%2FCase%2F"+caseId+"%2Fview"; // frame open as if an user open it
		
		driver.get(linkListMessage);
		
	}
	public void preparationDmd(WebDriver driver) throws InterruptedException {
		
		List <WebElement> list =driver.findElements(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[1]/div/div/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr"));		
		for(int i=1;i<=list.size();i++) {
			String elements="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[1]/div/div/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr[";			
			String status =driver.findElement(By.xpath(elements+i+"]/td[2]/span/span")).getText();
			switch(status) {
				default:
					break;
				case "Brouillon","En cours":
					driver.findElement(By.xpath(elements+i+"]/th/span/a")).click();
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//button[text()='Abandonner la demande']")).click();
					Thread.sleep(3000);
					driver.findElement(By.xpath("//button[@title='Fermer Enlèvement conservatoire']")).click();
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//button[@title='Actualiser']")).click();
					break;
				case "En anomalie":	
					driver.findElement(By.xpath(elements+i+"]/th/span/a")).click();
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//button[text()='Abandonner la demande']")).click();
					Thread.sleep(3000);
					driver.findElement(By.xpath("//button[@title='Fermer Enlèvement conservatoire']")).click();
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//button[@title='Actualiser']")).click();
					break;
				case"Envoyée":
					driver.findElement(By.xpath(elements+i+"]/th/span/a")).click();
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//button[text()='Envoyer annulation']")).click();
					Thread.sleep(3000);
					driver.findElement(By.xpath("//button[@title='Fermer Enlèvement conservatoire']")).click();
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//button[@title='Actualiser']")).click();
					break;				
			}
			
		}	
	}
	public void dmdEnlBstBidder(WebDriver driver, String finalize,String receipt) throws InterruptedException {	
		
		//New demande
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@title='Nouveau']")).click();
						
		//Access to the frame
		Thread.sleep(1000);
		WebElement iframe = driver.findElement(By.xpath("//iframe[@title='Demande Enlèvement ou Restitution']"));
		driver.switchTo().frame(iframe);		
		Thread.sleep(2000);
		
		//Best bidder
		String bidder ="/html/body/div[1]/article/div/div[3]/div[2]/div/div[3]/flowruntime-radio-button-input-lwc/fieldset/div/span";
		List<WebElement> offer=driver.findElements(By.xpath(bidder));
		for(int i=1;i<=offer.size();i++) {
			String	name= driver.findElement(By.xpath(bidder+"["+i+"]/label/span[2]/lightning-formatted-rich-text/span")).getText();
			if(name.equals("Meilleur Offrant")) {
				driver.findElement(By.xpath(bidder+"["+i+"]/label/span[1]")).click();
			}
		}		
		//Next step
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@title='Suivant']")).click();
		
		//Complete agreement and call again
		Select accordlése = new Select(driver.findElement(By.xpath("//select[@name='AccordLese']")));// Using select method due to the tag "Select"
		accordlése.selectByVisibleText("Oui");
		Select call = new Select(driver.findElement(By.xpath("//select[@name='Relance']")));
		call.selectByVisibleText("Manuelle");
				
		//Add a comment
		driver.findElement(By.xpath("//textarea")).sendKeys("Ceci est un test");
		
		//Next step
		driver.findElement(By.xpath("//button[@title='Suivant']")).click();
		
		//Go on the demand tab
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[2]/div/div/ul[2]/li[4]/a")).click();
		Thread.sleep(1000);
		
		if(finalize.equals("delete")) {
			
			//Delete the demande and close the tab
			driver.findElement(By.xpath("//button[text()='Abandonner la demande']")).click();
			
			//Check the status
			Thread.sleep(3000);
			String xpsts ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[3]/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-demande_-enlevement_ou_-restitution___-demande-enlevement__c___-v-i-e-w/forcegenerated-flexipage_demande_enlevement_ou_restitution_demandeenlevement__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/slot/flexipage-component2[1]/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_demandeenlevement__c___0125i000000hxftqaw___compact___view___recordlayout2/force-highlights2/div[1]/div[2]/slot/slot/force-highlights-details-item[2]/div/p[2]/slot/lightning-formatted-text";
			String status= driver.findElement(By.xpath(xpsts)).getText();
			System.out.println(status);
		}
		else {
			//Send the demande and close the tab
			driver.findElement(By.xpath("//button[text()='Envoyer la demande']")).click();
			Thread.sleep(6000);
			
			//Check the status
			String xpsts ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[3]/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-demande_-enlevement_ou_-restitution___-demande-enlevement__c___-v-i-e-w/forcegenerated-flexipage_demande_enlevement_ou_restitution_demandeenlevement__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/slot/flexipage-component2[1]/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_demandeenlevement__c___0125i000000hxftqaw___compact___view___recordlayout2/force-highlights2/div[1]/div[2]/slot/slot/force-highlights-details-item[2]/div/p[2]/slot/lightning-formatted-text";
			String status= driver.findElement(By.xpath(xpsts)).getText();
			System.out.println(status);
			
			if(receipt.equals("Attestation")) {
				//Certificate received
				driver.findElement(By.xpath("//button[text()='Attestation reçue']")).click();
				String date="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[3]/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-demande_-enlevement_ou_-restitution___-demande-enlevement__c___-v-i-e-w/forcegenerated-flexipage_demande_enlevement_ou_restitution_demandeenlevement__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/slot/flexipage-component2[2]/slot/c-lwc008_-actions-demande-enlevement/div[2]/c-lwc009_suivi-demande-enlevement/section/div/lightning-record-edit-form/form/slot/div/div[2]/lightning-input/lightning-datepicker/div[1]/div/input\r\n";
				String dayenl="1 juin 2021"; //day they take the car
				driver.findElement(By.xpath(date)).sendKeys(dayenl);
				driver.findElement(By.xpath(date)).sendKeys(Keys.RETURN);
			}
			else if(receipt.equals("Impossibilite")){
				driver.findElement(By.xpath("//button[text()='Impossibilité reçue']")).click();
				driver.findElement(By.xpath("//input[@name='MotifRefus__c']")).click();
				String reason ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[3]/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-demande_-enlevement_ou_-restitution___-demande-enlevement__c___-v-i-e-w/forcegenerated-flexipage_demande_enlevement_ou_restitution_demandeenlevement__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/slot/flexipage-component2[2]/slot/c-lwc008_-actions-demande-enlevement/div[2]/c-lwc009_suivi-demande-enlevement/section/div/lightning-record-edit-form/form/slot/div/lightning-input-field[1]/lightning-picklist/lightning-combobox/div[1]/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[2]";
				driver.findElement(By.xpath(reason)).click();
				driver.findElement(By.xpath("//textarea[@name='Commentaire__c']")).sendKeys("Ceci est un test");
				driver.findElement(By.xpath("//button[@type='submit']")).click();
			}
			else {
				driver.findElement(By.xpath("//button[text()='Envoyer annulation']")).click();				
			}
			Thread.sleep(6000);
			//check status
			status = driver.findElement(By.xpath(xpsts)).getText();
			System.out.println(status);
					
		}
	
	}
	public void dmdEnlNone(WebDriver driver, String departement, String finalize,String receipt) throws InterruptedException {
		
		//New demande
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@title='Nouveau']")).click();
						
		//Access to the frame
		Thread.sleep(1000);
		WebElement iframe = driver.findElement(By.xpath("//iframe[@title='Demande Enlèvement ou Restitution']"));
		driver.switchTo().frame(iframe);
		
		//None
		String bidder ="/html/body/div[1]/article/div/div[3]/div[2]/div/div[3]/flowruntime-radio-button-input-lwc/fieldset/div/span";
		List<WebElement> offer=driver.findElements(By.xpath(bidder));
		for(int i=1;i<=offer.size();i++) {
			String	name= driver.findElement(By.xpath(bidder+"["+i+"]/label/span[2]/lightning-formatted-rich-text/span")).getText();
			if(name.equals("Aucun")) {
				driver.findElement(By.xpath(bidder+"["+i+"]/label/span[1]")).click();
			}
		}				
		//Next step
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@title='Suivant']")).click();
		
		//Find a new repairer with deparment
		driver.findElement(By.xpath("//input[@placeholder='Entrer un département']")).sendKeys(departement);
		driver.findElement(By.xpath("//button[@title='Rechercher']")).click();
	
		//Choose the repairer
		Thread.sleep(6000);
		String xprepairer="/html/body/div[1]/article/div/div[3]/div[2]/div/div/c-lwc003_-recherche-epaviste-demande-enlevement/lightning-card/article/div[2]/slot/section/div/div/div/lightning-datatable/div[2]/div/div/table/tbody/tr[1]/td[2]/lightning-primitive-cell-checkbox/span/label/span[1]";
		driver.findElement(By.xpath(xprepairer)).click();	
		driver.findElement(By.xpath("//button[@title='Suivant']")).click();		
		
		//Complete agreement and call again
		Select accordlése = new Select(driver.findElement(By.xpath("//select[@name='AccordLese']")));// Using select method due to the tag "Select"
		accordlése.selectByVisibleText("Oui");
		Select call = new Select(driver.findElement(By.xpath("//select[@name='Relance']")));
		call.selectByVisibleText("Manuelle");
		
		//Add a comment
		driver.findElement(By.xpath("//textarea")).sendKeys("Ceci est un test");
		
		//Next step
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@title='Suivant']")).click();
		
		//Go on the demand tab
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[2]/div/div/ul[2]/li[4]/a")).click();
		Thread.sleep(1000);
				
		if(finalize.equals("delete")) {
			
			//Delete the demande and close the tab
			driver.findElement(By.xpath("//button[text()='Abandonner la demande']")).click();			
			
			//Check the status
			Thread.sleep(3000);
			String xpsts ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[3]/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-demande_-enlevement_ou_-restitution___-demande-enlevement__c___-v-i-e-w/forcegenerated-flexipage_demande_enlevement_ou_restitution_demandeenlevement__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/slot/flexipage-component2[1]/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_demandeenlevement__c___0125i000000hxftqaw___compact___view___recordlayout2/force-highlights2/div[1]/div[2]/slot/slot/force-highlights-details-item[2]/div/p[2]/slot/lightning-formatted-text";
			String status= driver.findElement(By.xpath(xpsts)).getText();
			System.out.println(status);
		}
		else {
			
			//Send the demande and close the tab
			driver.findElement(By.xpath("//button[text()='Envoyer la demande']")).click();
			Thread.sleep(6000);
			
			//Check the status
			String xpsts ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[3]/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-demande_-enlevement_ou_-restitution___-demande-enlevement__c___-v-i-e-w/forcegenerated-flexipage_demande_enlevement_ou_restitution_demandeenlevement__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/slot/flexipage-component2[1]/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_demandeenlevement__c___0123o000000dwuvqa0___compact___view___recordlayout2/force-highlights2/div[1]/div[2]/slot/slot/force-highlights-details-item[2]/div/p[2]/slot/lightning-formatted-text";							
			String status= driver.findElement(By.xpath(xpsts)).getText();
			System.out.println(status);
			
			if(receipt.equals("Attestation")) {
				
				//Certificate received
				driver.findElement(By.xpath("//button[text()='Attestation reçue']")).click();
				String date="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[3]/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-demande_-enlevement_ou_-restitution___-demande-enlevement__c___-v-i-e-w/forcegenerated-flexipage_demande_enlevement_ou_restitution_demandeenlevement__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/slot/flexipage-component2[2]/slot/c-lwc008_-actions-demande-enlevement/div[2]/c-lwc009_suivi-demande-enlevement/section/div/lightning-record-edit-form/form/slot/div/div[2]/lightning-input/lightning-datepicker/div[1]/div/input\r\n";
				String dayenl="1 juin 2021"; //day they take the car
				driver.findElement(By.xpath(date)).sendKeys(dayenl);
				driver.findElement(By.xpath(date)).sendKeys(Keys.RETURN);
			}
			else if(receipt.equals("Impossibilite")){
				
				driver.findElement(By.xpath("//button[text()='Impossibilité reçue']")).click();
				driver.findElement(By.xpath("//input[@name='MotifRefus__c']")).click();
				String reason ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[3]/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-demande_-enlevement_ou_-restitution___-demande-enlevement__c___-v-i-e-w/forcegenerated-flexipage_demande_enlevement_ou_restitution_demandeenlevement__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/slot/flexipage-component2[2]/slot/c-lwc008_-actions-demande-enlevement/div[2]/c-lwc009_suivi-demande-enlevement/section/div/lightning-record-edit-form/form/slot/div/lightning-input-field[1]/lightning-picklist/lightning-combobox/div[1]/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[2]";
				driver.findElement(By.xpath(reason)).click();
				driver.findElement(By.xpath("//textarea[@name='Commentaire__c']")).sendKeys("Ceci est un test");
				driver.findElement(By.xpath("//button[@type='submit']")).click();
			}
			else {
				
				driver.findElement(By.xpath("//button[text()='Envoyer annulation']")).click();				
			}
			Thread.sleep(5000);
			//check status
			status = driver.findElement(By.xpath(xpsts)).getText();
			System.out.println(status);
						
		}
	}	
}
