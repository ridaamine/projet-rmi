package serveur;

import implementation.AgenceImpl;
import implementation.BanqueImpl;
import implementation.ClientImpl;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import main.Adresse;
import classe.Agence;
import classe.Banque;
import classe.Client;

public class ClientServeur 
{
	public static void main(String args[]) 
	{
		if(System.getSecurityManager() == null) 
		{  
			System.setSecurityManager(new RMISecurityManager());  
		} 

		try 
		{
			System.out.println("Construction du serveur de client...");
			
			ClientImpl client = new ClientImpl();
			
			System.out.println("Liaison du serveur de client avec les registres...");
			Naming.rebind("Client", client);
			System.out.println("Serveur de client lance...");	
			
//			Client toto = new Client("Toto", "masculin", new Adresse("35 rue des lilas","Perpignan","0468508384"), new Agence(new Adresse("35 rue tressere","perpignan","0468508384"),new Banque("Banque Courtois")));
//			client.creerCompte(50, toto);
//			client.creerCompte(0, toto);
			
		} 
		catch (Exception e) { System.err.println("server error: "+e); }
	}
}
