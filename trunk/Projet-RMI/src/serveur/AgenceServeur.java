package serveur;

import implementation.AgenceImpl;
import implementation.BanqueImpl;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Scanner;

import main.Adresse;


public class AgenceServeur 
{
	public static void main(String args[]) 
	{
		if(System.getSecurityManager() == null) 
		{  
			System.setSecurityManager(new RMISecurityManager());  
		} 

		try 
		{
			Scanner sc = new Scanner(System.in);
			String nomBanque;
			
			System.out.println("Construction du serveur d'agence...");
			
			System.out.print("Veuillez saisir le nom de la banque de l'agence: ");
			nomBanque = sc.nextLine();
			
			AgenceImpl agence = new AgenceImpl(new Adresse("Rond-point de la Lyre 202\nAvenue des Moulins", "Montpellier", "0 892 892 000"), new BanqueImpl(nomBanque));
			
			System.out.println("Liaison du serveur de banque avec les registres...");
			Naming.rebind("Agence", agence);
			System.out.println("Serveur de banque lancé...");
		} 
		catch (Exception e) { System.err.println("server error: "+e); }
	}

}
