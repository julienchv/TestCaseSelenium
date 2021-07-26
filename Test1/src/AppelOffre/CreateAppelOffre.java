package AppelOffre;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CreateAppelOffre {
	public static String caseId;	

	public void RequestProposals(WebDriver driver, String act, String platform,String dossier) throws InterruptedException, MalformedURLException {
		
		//select the file 
		JavascriptExecutor js =(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement linkFile= driver.findElement(By.xpath("//a[text()='"+dossier+"']"));
		js.executeScript("arguments[0].scrollIntoView()", linkFile);
		driver.findElement(By.xpath("//a[text()='"+dossier+"']")).click();
				
		Thread.sleep(2000);
		URL currentUrl = new URL(driver.getCurrentUrl());
		caseId = (currentUrl.getPath().split("/").length > 4) ? currentUrl.getPath().split("/")[4] : "";
		String linkListMessage = TestAppelOffre.baseUrl + "/lightning/r/" + caseId + "/related/AppelsOffre__r/view"
		+"?ws=%2Flightning%2Fr%2FCase%2F"+caseId+"%2Fview"; // frame open as if an user open it		
		driver.get(linkListMessage);
		
		//Scroll to load all the element
		Thread.sleep(2000);
		js.executeScript("document.body.style.zoom = '75%'");
		WebElement scrollBar= driver.findElement(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]"));	
		for(int i=0;i<=50;i++) {
			scrollBar.sendKeys(Keys.PAGE_DOWN);
		}
		
		List <WebElement> list =driver.findElements(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr"));		

		for(int i=1;i<=list.size();i++) {
			String elements="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/div/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div/table/tbody/tr[";			
			String status =driver.findElement(By.xpath(elements+i+"]/td[3]/span/span")).getText();
			
			switch(status) {
				default:
					break;
				case "Publié":
					js.executeScript("document.body.style.zoom = '100%'");
					driver.findElement(By.xpath(elements+i+"]/th/span/a")).click();					
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					WebElement cancel=driver.findElement(By.xpath("//button[text()='Annuler']"));
					js.executeScript("arguments[0].scrollIntoView()", cancel);
					driver.findElement(By.xpath("//button[text()='Annuler']")).click();
					driver.findElement(By.xpath("//button[@title='OK']")).click();
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//input[@name='MotifAnnulation__c']")).click();
					String reason="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-appel_d_offres_-page_d_enregistrement2___-appel-offre__c___-v-i-e-w/forcegenerated-flexipage_appel_d_offres_page_d_enregistrement2_appeloffre__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-two-col-equal-header-template-desktop2/div/div[1]/slot/slot/flexipage-component2[2]/slot/c-lwc012_-actions-appel-offre/section/div/lightning-record-edit-form/form/slot/div/lightning-input-field/lightning-picklist/lightning-combobox/div[1]/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[2]";
					driver.findElement(By.xpath(reason)).click();
					driver.findElement(By.xpath("//button[text()='Valider']")).click();
					Thread.sleep(5000);
					String close="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/div/div/div/ul[2]/li[4]/div[2]/button";
					driver.findElement(By.xpath(close)).click();
					driver.findElement(By.xpath("//button[@title='Actualiser']")).click();
					Thread.sleep(500);
					break;	
				case "En attente":
					js.executeScript("document.body.style.zoom = '100%'");
					driver.findElement(By.xpath(elements+i+"]/th/span/a")).click();
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
					WebElement abd=driver.findElement(By.xpath("//button[text()='Abandonner']"));
					js.executeScript("arguments[0].scrollIntoView()", abd);
					driver.findElement(By.xpath("//button[text()='Abandonner']")).click();
					driver.findElement(By.xpath("//button[@title='OK']")).click();
					String xclose="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/div/div/div/ul[2]/li[4]/div[2]/button";
					Thread.sleep(1000);
					driver.findElement(By.xpath(xclose)).click();
					driver.findElement(By.xpath("//button[@title='Actualiser']")).click();
					Thread.sleep(500);
					break;
			}		
		}
		js.executeScript("document.body.style.zoom = '100%'");
		//New demande
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@title='Nouveau']")).click();
		
		// complete the different fields
		driver.findElement(By.xpath("//textarea[@name='DegatsApparents__c']")).sendKeys("Dégat-Test");
		driver.findElement(By.xpath("//textarea[@name='Commentaire__c']")).sendKeys("Ceci est un test");
		
		//Select the code of the car registration document
		Thread.sleep(1000);
		WebElement cardreg= driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']"));
		Actions action=new Actions(driver);
		action.moveToElement(cardreg).click().perform();		
		
		driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']")).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath("//input[@name='CodeCarteGrise__c']")).sendKeys(Keys.RETURN);
		
		//save
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Go to the frame request for proposals
		Thread.sleep(5000);
		String goframe="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/div/div/div/ul[2]/li[4]/a";
		driver.findElement(By.xpath(goframe)).click();
		
		if(act.equals("leave")) {
			
			//leave the request
			WebElement leave=driver.findElement(By.xpath("//button[@name='B']"));
			action.moveToElement(leave).click().perform();
			driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
			driver.findElement(By.xpath("//button[@title='OK']")).click();
			
			//checking the status
			Thread.sleep(1000);
			js.executeScript("document.body.style.zoom = '40%'");
			String xpsts="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-appel_d_offres_-page_d_enregistrement2___-appel-offre__c___-v-i-e-w/forcegenerated-flexipage_appel_d_offres_page_d_enregistrement2_appeloffre__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-two-col-equal-header-template-desktop2/div/div[2]/div[1]/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2/slot/flexipage-component2[1]/slot/flexipage-field-section2/div/div/div/laf-progressive-container/slot/div/slot/slot/flexipage-column2[1]/div/slot/flexipage-field[16]/slot/record_flexipage-record-field/div/div/div[2]/span/slot[1]/slot/lightning-formatted-text";
			String status=driver.findElement(By.xpath(xpsts)).getText();
			System.out.println(status);					
		}
		else {
			if(platform.equals("BCA")) {
			
			//Publish the request
			Thread.sleep(1000);					
			WebElement publish= driver.findElement(By.xpath("//button[@name='E']"));
			js.executeScript("arguments[0].scrollIntoView()", publish);
			driver.findElement(By.xpath("//button[@name='E']")).click();
							
			//Choose BCA
			driver.findElement(By.xpath("//input[@name='EnvoiPlateformeBCA__c']")).click();
			String ouiBCA="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-appel_d_offres_-page_d_enregistrement2___-appel-offre__c___-v-i-e-w/forcegenerated-flexipage_appel_d_offres_page_d_enregistrement2_appeloffre__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-two-col-equal-header-template-desktop2/div/div[1]/slot/slot/flexipage-component2[2]/slot/c-lwc012_-actions-appel-offre/section/div/lightning-record-edit-form/form/slot/div/lightning-input-field[1]/lightning-picklist/lightning-combobox/div[1]/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[2]";
			driver.findElement(By.xpath(ouiBCA)).click();
			
			driver.findElement(By.xpath("//input[@name='EnvoiACCIAUTO__c']")).click();
			String nonACC="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-appel_d_offres_-page_d_enregistrement2___-appel-offre__c___-v-i-e-w/forcegenerated-flexipage_appel_d_offres_page_d_enregistrement2_appeloffre__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-two-col-equal-header-template-desktop2/div/div[1]/slot/slot/flexipage-component2[2]/slot/c-lwc012_-actions-appel-offre/section/div/lightning-record-edit-form/form/slot/div/lightning-input-field[2]/lightning-picklist/lightning-combobox/div[1]/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[3]";
			driver.findElement(By.xpath(nonACC)).click();
			
			//Submit button
			driver.findElement(By.xpath("//button[@type='submit']")).click();		
			}
			else {
			
			//Publish the request
			Thread.sleep(1000);		
			WebElement publish= driver.findElement(By.xpath("//button[@name='E']"));
			js.executeScript("arguments[0].scrollIntoView()", publish);
			driver.findElement(By.xpath("//button[@name='E']")).click();
			
			//Choose ACCIAUTO
			driver.findElement(By.xpath("//input[@name='EnvoiPlateformeBCA__c']")).click();
			String nonBCA="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-appel_d_offres_-page_d_enregistrement2___-appel-offre__c___-v-i-e-w/forcegenerated-flexipage_appel_d_offres_page_d_enregistrement2_appeloffre__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-two-col-equal-header-template-desktop2/div/div[1]/slot/slot/flexipage-component2[2]/slot/c-lwc012_-actions-appel-offre/section/div/lightning-record-edit-form/form/slot/div/lightning-input-field[1]/lightning-picklist/lightning-combobox/div[1]/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[3]";
			driver.findElement(By.xpath(nonBCA)).click();
			
			driver.findElement(By.xpath("//input[@name='EnvoiACCIAUTO__c']")).click();
			String ouiACC="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-appel_d_offres_-page_d_enregistrement2___-appel-offre__c___-v-i-e-w/forcegenerated-flexipage_appel_d_offres_page_d_enregistrement2_appeloffre__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-two-col-equal-header-template-desktop2/div/div[1]/slot/slot/flexipage-component2[2]/slot/c-lwc012_-actions-appel-offre/section/div/lightning-record-edit-form/form/slot/div/lightning-input-field[2]/lightning-picklist/lightning-combobox/div[1]/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[2]";
			driver.findElement(By.xpath(ouiACC)).click();
			
			//Submit button
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			// Send the message
			Thread.sleep(5000);
			WebElement element = driver.findElement(By.xpath("//button[@title='Envoyer']"));
			action.moveToElement(element).click().perform();
			
			//Go to the frame request for proposals
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath(goframe)).click();
			
			//checking the status
			Thread.sleep(1000);
			js.executeScript("document.body.style.zoom = '40%'");
			String xpsts="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-appel_d_offres_-page_d_enregistrement2___-appel-offre__c___-v-i-e-w/forcegenerated-flexipage_appel_d_offres_page_d_enregistrement2_appeloffre__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-two-col-equal-header-template-desktop2/div/div[2]/div[1]/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2/slot/flexipage-component2[1]/slot/flexipage-field-section2/div/div/div/laf-progressive-container/slot/div/slot/slot/flexipage-column2[1]/div/slot/flexipage-field[16]/slot/record_flexipage-record-field/div/div/div[2]/span/slot[1]/slot/lightning-formatted-text";
			String status=driver.findElement(By.xpath(xpsts)).getText();
			System.out.println(status);	
			}			
		}
	}
	public void PropOffre(WebDriver driver) throws InterruptedException, MalformedURLException {
	
		String UrlAO=driver.getCurrentUrl();
		String AOid = (UrlAO.split("/").length > 4) ? UrlAO.split("/")[6] : "";
		String linkListAO = TestAppelOffre.baseUrl + "/lightning/r/AppelOffre__c/" + AOid + "/view"
		+"?ws=%2Flightning%2Fr%2FCase%2F"+caseId+"%2Fview"; // frame open as if an user open it		
			
		//Create a new offer proposition
		Thread.sleep(3000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement btnnew= driver.findElement(By.xpath("//button[text()='Nouveau']"));
		Actions action=new Actions(driver);
		action.moveToElement(btnnew).click().perform();
		
		//Complete amount
		driver.findElement(By.xpath("//input[@name='MontantPropositionAO__c']")).sendKeys("1500");
		
		//Complete bidder
		Thread.sleep(250);
		driver.findElement(By.xpath("//input[@placeholder='Recherchez dans les Comptes...']")).sendKeys("CDMA");
		String CDMA ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[4]/section/div/div[2]/div/div/div/div/one-record-action-flexipage/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-proposition_appel_d_offre_-page_d_enregistrement___-proposition-appel-offre__c___create/forcegenerated-flexipage_proposition_appel_d_offre_page_d_enregistrement_propositionappeloffre__c__create_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-single-col-no-header-template-desktop2/div/div/div/slot/slot/flexipage-component2[1]/slot/flexipage-field-section2/div/div/div/laf-progressive-container/slot/div/slot/slot/flexipage-column2[1]/div/slot/flexipage-field[2]/slot/record_flexipage-record-field/div/span/slot/slot/force-record-layout-lookup/lightning-lookup/lightning-lookup-desktop/lightning-grouped-combobox/div[1]/div/lightning-base-combobox/div/div[2]/ul/li[2]/lightning-base-combobox-item";
		WebElement bder =driver.findElement(By.xpath(CDMA));
		js.executeScript("arguments[0].scrollIntoView()",bder );							
		driver.findElement(By.xpath(CDMA)).click();
		
		//Choose TVA position
		String TVA ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[4]/section/div/div[2]/div/div/div/div/one-record-action-flexipage/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-proposition_appel_d_offre_-page_d_enregistrement___-proposition-appel-offre__c___create/forcegenerated-flexipage_proposition_appel_d_offre_page_d_enregistrement_propositionappeloffre__c__create_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-single-col-no-header-template-desktop2/div/div/div/slot/slot/flexipage-component2[1]/slot/flexipage-field-section2/div/div/div/laf-progressive-container/slot/div/slot/slot/flexipage-column2[2]/div/slot/flexipage-field[3]/slot/record_flexipage-record-field/div/span/slot/slot/force-record-picklist/force-form-picklist/lightning-picklist/lightning-combobox/div[1]/lightning-base-combobox/div/div[1]/input";
		WebElement LocateTVA=driver.findElement(By.xpath(TVA));
		js.executeScript("arguments[0].scrollIntoView()", LocateTVA);
		driver.findElement(By.xpath(TVA)).click();
		Thread.sleep(250);
		String Net="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[4]/section/div/div[2]/div/div/div/div/one-record-action-flexipage/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-proposition_appel_d_offre_-page_d_enregistrement___-proposition-appel-offre__c___create/forcegenerated-flexipage_proposition_appel_d_offre_page_d_enregistrement_propositionappeloffre__c__create_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-single-col-no-header-template-desktop2/div/div/div/slot/slot/flexipage-component2[1]/slot/flexipage-field-section2/div/div/div/laf-progressive-container/slot/div/slot/slot/flexipage-column2[2]/div/slot/flexipage-field[3]/slot/record_flexipage-record-field/div/span/slot/slot/force-record-picklist/force-form-picklist/lightning-picklist/lightning-combobox/div[1]/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[2]";
		driver.findElement(By.xpath(Net)).click();

		//Choose destination rescue
		String destrsc="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[4]/section/div/div[2]/div/div/div/div/one-record-action-flexipage/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-proposition_appel_d_offre_-page_d_enregistrement___-proposition-appel-offre__c___create/forcegenerated-flexipage_proposition_appel_d_offre_page_d_enregistrement_propositionappeloffre__c__create_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-single-col-no-header-template-desktop2/div/div/div/slot/slot/flexipage-component2[1]/slot/flexipage-field-section2/div/div/div/laf-progressive-container/slot/div/slot/slot/flexipage-column2[2]/div/slot/flexipage-field[4]/slot/record_flexipage-record-field/div/span/slot/slot/force-record-picklist/force-form-picklist/lightning-picklist/lightning-combobox/div/lightning-base-combobox/div/div[1]/input";
		driver.findElement(By.xpath(destrsc)).click();
		Thread.sleep(250);
		String destruct="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[4]/section/div/div[2]/div/div/div/div/one-record-action-flexipage/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-proposition_appel_d_offre_-page_d_enregistrement___-proposition-appel-offre__c___create/forcegenerated-flexipage_proposition_appel_d_offre_page_d_enregistrement_propositionappeloffre__c__create_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-single-col-no-header-template-desktop2/div/div/div/slot/slot/flexipage-component2[1]/slot/flexipage-field-section2/div/div/div/laf-progressive-container/slot/div/slot/slot/flexipage-column2[2]/div/slot/flexipage-field[4]/slot/record_flexipage-record-field/div/span/slot/slot/force-record-picklist/force-form-picklist/lightning-picklist/lightning-combobox/div/lightning-base-combobox/div/div[2]/lightning-base-combobox-item[2]";
		driver.findElement(By.xpath(destruct)).click();
		
		//Choose date and hour
		String date="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[4]/section/div/div[2]/div/div/div/div/one-record-action-flexipage/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-proposition_appel_d_offre_-page_d_enregistrement___-proposition-appel-offre__c___create/forcegenerated-flexipage_proposition_appel_d_offre_page_d_enregistrement_propositionappeloffre__c__create_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-single-col-no-header-template-desktop2/div/div/div/slot/slot/flexipage-component2[1]/slot/flexipage-field-section2/div/div/div/laf-progressive-container/slot/div/slot/slot/flexipage-column2[1]/div/slot/flexipage-field[4]/slot/record_flexipage-record-field/div/span/slot/slot/force-record-layout-input-date-time/lightning-input/lightning-datetimepicker/div/fieldset/div/div/div/lightning-datepicker/div/div/input";
		driver.findElement(By.xpath(date)).sendKeys("26/06/2021");
		
		Thread.sleep(250);
		String hour="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[4]/section/div/div[2]/div/div/div/div/one-record-action-flexipage/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-proposition_appel_d_offre_-page_d_enregistrement___-proposition-appel-offre__c___create/forcegenerated-flexipage_proposition_appel_d_offre_page_d_enregistrement_propositionappeloffre__c__create_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-single-col-no-header-template-desktop2/div/div/div/slot/slot/flexipage-component2[1]/slot/flexipage-field-section2/div/div/div/laf-progressive-container/slot/div/slot/slot/flexipage-column2[1]/div/slot/flexipage-field[4]/slot/record_flexipage-record-field/div/span/slot/slot/force-record-layout-input-date-time/lightning-input/lightning-datetimepicker/div/fieldset/div/div/div/lightning-timepicker/div/lightning-base-combobox/div/div[1]/input";
		driver.findElement(By.xpath(hour)).click();
		driver.findElement(By.xpath(destrsc)).click();
			
		//Save
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		
		//Return to the AO frame
		Thread.sleep(2000);
		driver.get(linkListAO);
		
		//Select the proposition
		Thread.sleep(3000);
		String propOfferCheck="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-appel_d_offres_-page_d_enregistrement2___-appel-offre__c___-v-i-e-w/forcegenerated-flexipage_appel_d_offres_page_d_enregistrement2_appeloffre__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-two-col-equal-header-template-desktop2/div/div[2]/div[2]/slot/slot/flexipage-component2/slot/flexipage-aura-wrapper/div/c-lwc005_-choix-meilleur-offrant/div[2]/lightning-datatable/div[2]/div/div/table/tbody/tr/td[1]/lightning-primitive-cell-checkbox/span/label/span[1]";
		WebElement check=driver.findElement(By.xpath(propOfferCheck));
		js.executeScript("arguments[0].scrollIntoView()",check);
		driver.findElement(By.xpath(propOfferCheck)).click();
		WebElement selectMO=driver.findElement(By.xpath("//button[text()='Sélectionner MO']"));
		js.executeScript("arguments[0].scrollIntoView()",selectMO);
		driver.findElement(By.xpath("//button[text()='Sélectionner MO']")).click();	
				
		//Status
		Thread.sleep(2000);
		String status="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-appel_d_offres_-page_d_enregistrement2___-appel-offre__c___-v-i-e-w/forcegenerated-flexipage_appel_d_offres_page_d_enregistrement2_appeloffre__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-two-col-equal-header-template-desktop2/div/div[2]/div[1]/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2/slot/flexipage-component2[1]/slot/flexipage-field-section2/div/div/div/laf-progressive-container/slot/div/slot/slot/flexipage-column2[1]/div/slot/flexipage-field[16]/slot/record_flexipage-record-field/div/div/div[1]/span[1]";
		WebElement sts=driver.findElement(By.xpath(status));
		js.executeScript("arguments[0].scrollIntoView()",sts);
		String textsts="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[2]/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-appel_d_offres_-page_d_enregistrement2___-appel-offre__c___-v-i-e-w/forcegenerated-flexipage_appel_d_offres_page_d_enregistrement2_appeloffre__c__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-two-col-equal-header-template-desktop2/div/div[2]/div[1]/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2/slot/flexipage-component2[1]/slot/flexipage-field-section2/div/div/div/laf-progressive-container/slot/div/slot/slot/flexipage-column2[1]/div/slot/flexipage-field[16]/slot/record_flexipage-record-field/div/div/div[2]/span/slot[1]/slot/lightning-formatted-text";
		System.out.println(driver.findElement(By.xpath(textsts)).getText());				
	}
	
}
