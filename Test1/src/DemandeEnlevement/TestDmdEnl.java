package DemandeEnlevement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestDmdEnl {
	
	public void AccessDmdEnl(WebDriver driver) throws MalformedURLException, InterruptedException{		
		
		//select the file 
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[text()='00001819']")).click();
		
		Thread.sleep(2000);
		URL currentUrl = new URL(driver.getCurrentUrl());
		String caseId = (currentUrl.getPath().split("/").length > 4) ? currentUrl.getPath().split("/")[4] : "";
		String linkListMessage = DemandeEnlevement.baseUrl + "/lightning/r/" + caseId + "/related/Demandes_d_enlevement__r/view";
		//+"?ws=%2Flightning%2Fr%2FCase%2F"+caseId+"%2Fview"; // frame open as if an user open it
		
		driver.get(linkListMessage);
		
		//New demande
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@title='Nouveau']")).click();
				
		//Access to the frame
		Thread.sleep(1000);
		WebElement iframe = driver.findElement(By.xpath("//iframe[@title='Demande Enlèvement ou Restitution']"));
		driver.switchTo().frame(iframe);
		
	}
	public void dmdEnlBstBidder(WebDriver driver, String finalize,String receipt) throws InterruptedException {	
		
		//Best bidder
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/article/div/div[3]/div[2]/div/div[3]/flowruntime-radio-button-input-lwc/fieldset/div/span[1]/label/span[1]")).click();
		
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
		
		if(finalize=="delete") {
			
			//Delete the demande and close the tab
			driver.findElement(By.xpath("//button[text()='Abandonner la demande']")).click();
			driver.findElement(By.xpath("//button[@title='Fermer Enlèvement conservatoire']")).click();	
			
			//Check the status
			String xpsts ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[3]/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-demande_-enlevement_ou_-restitution___-demande-enlevement__c___-v-i-e-w/forcegenerated-flexipage_demande_enlevement_ou_restitution_demandeenlevement__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/slot/flexipage-component2[1]/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_demandeenlevement__c___0123o000000dwuvqa0___compact___view___recordlayout2/force-highlights2/div[1]/div[2]/slot/slot/force-highlights-details-item[2]/div/p[2]/slot/lightning-formatted-text";
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
			
			if(receipt=="Attestation") {
				//Certificate received
				driver.findElement(By.xpath("//button[text()='Attestation reçue']")).click();
				String date="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[3]/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-demande_-enlevement_ou_-restitution___-demande-enlevement__c___-v-i-e-w/forcegenerated-flexipage_demande_enlevement_ou_restitution_demandeenlevement__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/slot/flexipage-component2[2]/slot/c-lwc008_-actions-demande-enlevement/div[2]/c-lwc009_suivi-demande-enlevement/section/div/lightning-record-edit-form/form/slot/div/div[2]/lightning-input/lightning-datepicker/div[1]/div/input\r\n";
				String dayenl="1 juin 2021"; //day they take the car
				driver.findElement(By.xpath(date)).sendKeys(dayenl);
				driver.findElement(By.xpath(date)).sendKeys(Keys.RETURN);
			}
			else if(receipt=="Impossibilite"){
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
			
			/*
			//Refresh and Check on demande enlèvement if the demand is good
			Thread.sleep(10000);
			driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[2]/div/div/ul[2]/li[3]/a")).click();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//button[@name='refreshButton']")).click();
			*/
		}
	
	}
	public void dmdEnlNone(WebDriver driver, String departement, String finalize,String receipt) throws InterruptedException {
		
		//None 
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/article/div/div[3]/div[2]/div/div[3]/flowruntime-radio-button-input-lwc/fieldset/div/span[2]/label/span[1]")).click();
				
		//Next step
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@title='Suivant']")).click();
		
		//Find a new repairer with deparment
		driver.findElement(By.xpath("//input[@placeholder='Entrer un département']")).sendKeys(departement);
		driver.findElement(By.xpath("//button[@title='Rechercher']")).click();
	
		//Choose the repairer
		Thread.sleep(3000);
		String xprepairer="/html/body/div[1]/article/div/div[3]/div[2]/div/div[2]/c-lwc003_-recherche-epaviste-demande-enlevement/lightning-card/article/div[2]/slot/section/div/div/div/lightning-datatable/div[2]/div/div/table/tbody/tr[1]/td[2]/lightning-primitive-cell-checkbox/span/label/span[1]";
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
				
		if(finalize=="delete") {
			
			//Delete the demande and close the tab
			driver.findElement(By.xpath("//button[text()='Abandonner la demande']")).click();
			driver.findElement(By.xpath("//button[@title='Fermer Enlèvement conservatoire']")).click();	
			
			//Check the status
			String xpsts ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[3]/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-demande_-enlevement_ou_-restitution___-demande-enlevement__c___-v-i-e-w/forcegenerated-flexipage_demande_enlevement_ou_restitution_demandeenlevement__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/slot/flexipage-component2[1]/slot/records-lwc-highlights-panel/records-lwc-record-layout/forcegenerated-highlightspanel_demandeenlevement__c___0123o000000dwuvqa0___compact___view___recordlayout2/force-highlights2/div[1]/div[2]/slot/slot/force-highlights-details-item[2]/div/p[2]/slot/lightning-formatted-text";
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
			
			if(receipt=="Attestation") {
				
				//Certificate received
				driver.findElement(By.xpath("//button[text()='Attestation reçue']")).click();
				String date="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section[3]/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-demande_-enlevement_ou_-restitution___-demande-enlevement__c___-v-i-e-w/forcegenerated-flexipage_demande_enlevement_ou_restitution_demandeenlevement__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-template-desktop2/div/div[1]/slot/slot/flexipage-component2[2]/slot/c-lwc008_-actions-demande-enlevement/div[2]/c-lwc009_suivi-demande-enlevement/section/div/lightning-record-edit-form/form/slot/div/div[2]/lightning-input/lightning-datepicker/div[1]/div/input\r\n";
				String dayenl="1 juin 2021"; //day they take the car
				driver.findElement(By.xpath(date)).sendKeys(dayenl);
				driver.findElement(By.xpath(date)).sendKeys(Keys.RETURN);
			}
			else if(receipt=="Impossibilite"){
				
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
			
			/*
			//Refresh and Check on demande enlèvement if the demand is good
			Thread.sleep(10000);
			driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[1]/div[2]/div/div/ul[2]/li[3]/a")).click();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//button[@name='refreshButton']")).click();
			*/
		}
	}	
}
