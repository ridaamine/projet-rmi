package serveur;

import implementation.BanqueImpl;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import main.Adresse;
import classe.Agence;
import classe.Banque;

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
//			Banque creditAgricole = new Banque("Credit Agricole");
			
			System.out.println("Construction du serveur de banque...");
			BanqueImpl banque = new BanqueImpl();
			System.out.println("Liaison du serveur de banque avec les registres...");
			Naming.rebind("Banque", banque);
			System.out.println("Serveur de banque lance...");
			
			
			
		} 
		catch (Exception e) { System.err.println("server error: "+e); }
	}
}
