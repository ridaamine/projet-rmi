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
			System.out.println("Construction du serveur d'agence...");
			
			AgenceImpl agence = new AgenceImpl();
			
			System.out.println("Liaison du serveur d'agence avec les registres...");
			Naming.rebind("Agence", agence);
			System.out.println("Serveur d'agence lance...");
			
//			agence.creerClient("Toto", "masculin", new Adresse("35 rue des lilas","Perpignan","0468508384"), agence.rechercherAgence("perpignan"));
			
		} 
		catch (Exception e) { System.err.println("server error t boooo: "+e); }
	}

}
