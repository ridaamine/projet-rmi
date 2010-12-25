package serveur;

import implementation.AgenceImpl;
import implementation.BanqueImpl;
import implementation.ClientImpl;
import implementation.CompteImpl;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import main.Adresse;
import classe.Agence;
import classe.Banque;

public class CompteServeur 
{
	public static void main(String args[]) 
	{
		if(System.getSecurityManager() == null) 
		{  
			System.setSecurityManager(new RMISecurityManager());  
		} 

		try 
		{
			System.out.println("Construction du serveur de compte...");
			
			CompteImpl compte = new CompteImpl();
			
			System.out.println("Liaison du serveur de compte avec les registres...");
			Naming.rebind("//127.0.0.1/Compte", compte);
			System.out.println("Serveur de compte lance...");
			
		} 
		catch (Exception e) { System.err.println("server error: "+e); }
	}
}
