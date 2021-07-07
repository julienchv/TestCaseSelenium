package scanner;
import java.util.Scanner;

public class scanner {
	
	public static Scanner sc= new Scanner(System.in);
	
	public static String saisirDossier() {
		System.out.println("Saisir le numéro de dossier à 8 chiffres");		
		return sc.next();		
	}
	
	public static String saisirEnvironnement(){
		System.out.println("Saisir le type d'environnement :--recette ou --int ou --preprod ou ne rien mettre pour la prod");
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		return sc.next();
		
	}
	public static void main(String[] args) {			

	}

}
