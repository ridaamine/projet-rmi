package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import service.ICompte;
import classe.Client;
import classe.Compte;

public class ThreadVirement extends Thread
{
	private Client client;
	private Compte compte1, compte2;
	private int montant;
	
	public ThreadVirement (Client client, Compte compte1, Compte compte2, int montant)
	{
		this.client = client;
		this.compte1 = compte1;
		this.compte2 = compte2;
		this.montant = montant;
		this.start();
	}
	
	public void run()
	{
		try 
		{
			ICompte serveurCompte = (ICompte) Naming.lookup(client.getAdresseServeurCompte());
			if(compte1 != null && compte2 != null)
			serveurCompte.virement(compte1.getNumero(), compte2.getNumero(), montant);
		}
		catch (MalformedURLException e) {e.printStackTrace();}
		catch (RemoteException e) {e.printStackTrace();} 
		catch (NotBoundException e) {e.printStackTrace();}
		
	}

}
