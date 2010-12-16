package serveur;

import implementation.BanqueImpl;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Scanner;

public class BanqueServeur 
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
			
			System.out.println("Construction du serveur de banque...");
			
			System.out.print("Veuillez saisir le nom de la banque que vous voulez creer: ");
			nomBanque = sc.nextLine();

			BanqueImpl banque = new BanqueImpl(nomBanque);
			System.out.println("Liaison du serveur de banque avec les registres...");
			Naming.rebind("Banque", banque);
			System.out.println("Serveur de banque lance...");
		} 
		catch (Exception e) { System.err.println("server error: "+e); }
	}
}
