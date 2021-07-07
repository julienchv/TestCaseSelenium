package CorrespondanceSauvetageDecisionLese;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestCorrespondance {
	
	public void CorresDestSauv(WebDriver driver, String dossier) throws InterruptedException, MalformedURLException {
		
		//select the file 
		JavascriptExecutor js =(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement linkFile= driver.findElement(By.xpath("//a[text()='"+dossier+"']"));
		js.executeScript("arguments[0].scrollIntoView()", linkFile);
		driver.findElement(By.xpath("//a[text()='"+dossier+"']")).click();
		
		//Find Destination sauvetage field
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Perte totale']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[text()='Procédure et valeurs']")).click();
		
		// Get the value
		Thread.sleep(2000);
		WebElement dest= driver.findElement(By.xpath("//span[text()='Destination de sauvetage']"));
		js.executeScript("arguments[0].scrollIntoView()", dest);
		String fieldvalue ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[5]/slot/flexipage-component2[2]/slot/c-lwc015_-ecran-p-t1/div[1]/lightning-record-view-form/div/slot/div[11]/div/div/div/div";			
		String code="";
		do {
		String destinationsauvetage = driver.findElement(By.xpath(fieldvalue)).getText();
		code=destinationsauvetage.split("-")[0];
		}while(code=="");				

		//open Message and DARVA window						
		Thread.sleep(1000);
		URL currentUrl = new URL(driver.getCurrentUrl());
		String caseId = (currentUrl.getPath().split("/").length > 4) ? currentUrl.getPath().split("/")[4] : "";
		String linkListMessage = Correspondance.baseUrl + "/lightning/r/Case/" + caseId + "/related/MessagesDossier__r/view"
		+"?ws=%2Flightning%2Fr%2FCase%2F"+caseId+"%2Fview";
		driver.get(linkListMessage);
		
		// New message
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"brandBand_3\"]/div/div/div/div[1]/div[1]/div[2]/ul/li/a")).click();
		
		// DARVA
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).sendKeys(Keys.ENTER);
			
		//SD09- Réponse offre de cession
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un Type de message ']")).click();	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='4109']")).click();
		
		Thread.sleep(2000);
		String answer="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/div/c-lwc007_-i-h-m-envoi-message-darva/section/div/div/div[1]/form[2]/fieldset/div/lightning-combobox/div/lightning-base-combobox/div/div[1]/input";
		WebElement ans= driver.findElement(By.xpath(answer));
		String decision=ans.getAttribute("value");		

		//separate cases
		switch(code) {
		default:
			System.out.println("Erreur dans le processus ou pas de destination de sauvetage");
			break;		
		case "4","6","9","10","11","14","15","17","20","21": 			
			if("Cession acceptée".equals(decision)) {
				System.out.println("Correspondance entre la décision du lésé et la destination de sauvetage");		
			}
			else {
				System.out.println("Erreur:la décision du lésé ne correspond pas à la destination de sauvetage");
			}
			break;
		case "2","19":		
			if("Cession refusée par la compagnie".equals(decision)) {
				System.out.println("Correspondance entre la décision du lésé et la destination de sauvetage");		
			}
			else {
				System.out.println("Erreur:la décision du lésé ne correspond pas à la destination de sauvetage");
			}
			break;
		case"1","7","18":			
			if("Cession refusée par l'assuré".equals(decision)) {
				System.out.println("Correspondance entre la décision du lésé et la destination de sauvetage");		
			}
			else {
				System.out.println("Erreur:la décision du lésé ne correspond pas à la destination de sauvetage");
			}
			break;
		case "0","3","5","8","12","13","16":		
			if("Cession sans réponse".equals(decision)) {
				System.out.println("Correspondance entre la décision du lésé et la destination de sauvetage");		
			}
			else {
				System.out.println("Erreur:la décision du lésé ne correspond pas à la destination de sauvetage");
			}
			break;			
		}
		
	}
	public void CorresDocsCession(WebDriver driver,String dossier) throws InterruptedException, MalformedURLException {
		//select the file 
		JavascriptExecutor js =(JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement linkFile= driver.findElement(By.xpath("//a[text()='"+dossier+"']"));
		js.executeScript("arguments[0].scrollIntoView()", linkFile);
		driver.findElement(By.xpath("//a[text()='"+dossier+"']")).click();
		
		//Find Destination sauvetage field
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Perte totale']")).click();
		Thread.sleep(500);
		WebElement managetransfer= driver.findElement(By.xpath("//span[text()='Gestion de la cession']"));
		js.executeScript("arguments[0].scrollIntoView()", managetransfer);
		driver.findElement(By.xpath("//span[text()='Gestion de la cession']")).click();
		
		//Get date and compliance for each fields		
		
		List <WebElement> list =driver.findElements(By.xpath("/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[5]/slot/flexipage-component2[3]/slot/c-lwc011_-afficher-formulaire-pieces-cession-dossier/div/div/div[2]/lightning-datatable/div[2]/div/div/table/tbody/tr"));		
		ArrayList <String> transferPieces=new ArrayList<String>();
		for(int i=1;i<=list.size();i++) {
			String elements="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2/section/slot/one-active-scope-influencer/slot/slot/slot/div/flexipage-record-home-scrollable-column/slot/slot/slot/flexipage-component2/slot/flexipage-tabset2/div/lightning-tabset/div/slot/slot/slot/flexipage-tab2[5]/slot/flexipage-component2[3]/slot/c-lwc011_-afficher-formulaire-pieces-cession-dossier/div/div/div[2]/lightning-datatable/div[2]/div/div/table/tbody/tr[";			
			String nom =driver.findElement(By.xpath(elements+i+"]/th/lightning-primitive-cell-factory/span/div/lightning-base-formatted-text")).getText();
			String date=driver.findElement(By.xpath(elements+i+"]/td[2]/lightning-primitive-cell-factory/span/div/lightning-formatted-date-time")).getText();
			if(date.equals("")) {
				
			}
			
			else {
				String compliance=driver.findElement(By.xpath(elements+i+"]/td[3]/lightning-primitive-cell-factory/span/div/span")).getText();
				if(compliance.equals("Faux"))	{
					transferPieces.add(nom);
				}
			}
			
		}		
		//click on message to open the window				
		Thread.sleep(4000);
		URL currentUrl = new URL(driver.getCurrentUrl());
		String caseId = (currentUrl.getPath().split("/").length > 4) ? currentUrl.getPath().split("/")[4] : "";
		String linkListMessage = Correspondance.baseUrl + "/lightning/r/Case/" + caseId + "/related/MessagesDossier__r/view"
				+"?ws=%2Flightning%2Fr%2FCase%2F"+caseId+"%2Fview";	
		driver.get(linkListMessage);
	
		// New message
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"brandBand_3\"]/div/div/div/div[1]/div[1]/div[2]/ul/li/a")).click();
			
		// DARVA
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un canal']")).sendKeys(Keys.ENTER);
			
		//SD09- Réponse offre de cession
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Sélectionner un Type de message ']")).click();	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='4109']")).click();
		String allpiece ="/html/body/div[4]/div[1]/section/div[1]/div/div[2]/div[2]/section/div/div/section/div/div[2]/div/div/div/one-record-home-flexipage2/forcegenerated-adg-rollup_component___force-generated__flexipage_-record-page___-dossier-en-cours___-case___-v-i-e-w/forcegenerated-flexipage_dossierencours_case__view_js/record_flexipage-record-page-decorator/div[1]/records-record-layout-event-broker/slot/slot/flexipage-record-home-pinned-header-left-sidebar-two-col-template-desktop2/div/div/div/one-template-workspace/navex-console-tabset2/div/navex-console-tab2[3]/section/div/div[2]/div/div/div/div/c-lwc007_-i-h-m-envoi-message-darva/section/div/div/div[1]/form[2]/fieldset/div/lightning-checkbox-group/fieldset/div/span";
		List <WebElement> allpieces = driver.findElements(By.xpath(allpiece));
		
		
		 String script = "return window.getComputedStyle(document.querySelector('Enter root class name here '),':after').getPropertyValue('content')";
         Thread.sleep(3000);
         String content = (String) js.executeScript(script);
         System.out.println(content);
		
	}
}
