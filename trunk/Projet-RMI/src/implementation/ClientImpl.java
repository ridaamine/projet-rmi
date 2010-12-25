package implementation;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.Adresse;
import service.IAgence;
import service.IClient;
import service.ICompte;
import classe.Agence;
import classe.Client;
import classe.Compte;
import classe.Livret;


/**
 * Cette classe implemente le comportement général que doit avoir un client.
 *
 */
public class ClientImpl extends UnicastRemoteObject implements IClient
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//

	private ArrayList<Client> clients; // liste des comptes que possède le client

	
//======================================================================//
//========================== Constructeurs =============================//
//======================================================================//

	/**
	 * Crée une nouvelle instance de <i>ClientImpl</i>
	 *
	 */
	public ClientImpl() throws RemoteException
	{
		super();
		this.clients = new ArrayList<Client>();
		
	}
	

//======================================================================//
//====================== Méthodes Implémentées= ========================//
//======================================================================//
	
	@Override
	public void creerCompte( int soldeInitiale, Client proprietaire) throws RemoteException
	{	
			Compte compte = new Compte(soldeInitiale,proprietaire);
			
			try 
			{
				ICompte serveurCompte;
				serveurCompte = (ICompte) Naming.lookup("Compte");
				serveurCompte.creerCompte(soldeInitiale,proprietaire);
			} 
			catch (MalformedURLException e) { System.err.println("Erreur d'URL du serveur de compte lors de l'ajout d'un compte."); }
			catch (NotBoundException e) { System.err.println("Impossible de créer un lien avec le serveur de compte"); }
			
	}
	
	@Override
	public void creerLivret(int soldeInitiale, double taux, Client proprietaire) throws RemoteException
	{
		Livret livret = new Livret(soldeInitiale,taux,proprietaire);
		
		try 
		{
			ICompte serveurCompte;
			serveurCompte = (ICompte) Naming.lookup("Compte");
			serveurCompte.creerLivret(soldeInitiale,taux,proprietaire);
		} 
		catch (MalformedURLException e) { System.err.println("Erreur d'URL du serveur de compte lors de l'ajout d'un compte."); }
		catch (NotBoundException e) { System.err.println("Impossible de créer un lien avec le serveur de compte"); }	}
	
	@Override
	public ArrayList<Compte> listeComptes() throws RemoteException
	{
		ArrayList<Compte> listeComptes = null;
		try 
		{
			ICompte serveurCompte;
			serveurCompte = (ICompte) Naming.lookup("Compte");
			listeComptes = serveurCompte.listeComptes();
		} 
		catch (MalformedURLException e) { System.err.println("Erreur d'URL du serveur de compte lors de l'ajout d'un compte."); }
		catch (NotBoundException e) { System.err.println("Impossible de créer un lien avec le serveur de compte"); }	
		
		return listeComptes;
	}
	
	@Override
	public void virement(int numCompte1, int numCompte2, int montant) throws RemoteException
	{
		try 
		{
			ICompte serveurCompte = (ICompte)Naming.lookup("Compte");
			serveurCompte.virement(numCompte1,numCompte2,montant);
			
		}
		catch (MalformedURLException e) {e.printStackTrace();}
		catch (NotBoundException e) {e.printStackTrace();}
	}

	@Override
	public void creerClient(Client client) throws RemoteException
	{
		this.clients.add(client);
		System.out.println("Création du client : "+client);
	}


	@Override
	public void detruireClient(int numero) throws RemoteException
	{
		boolean trouve = false;
		int i=0;
		while(!trouve)
		{
			if(this.clients.get(i).getNumero() == numero)
			{
				trouve = true;
				this.clients.remove(i);
				System.out.println("Destruction du client");
			}
			i++;
		}
		if(!trouve)
		System.err.println("Erreur destruction client : numero inconnu");
	}

	@Override
	public Client rechercheClient(String nom, Agence agence) throws RemoteException
	{
		boolean trouve = false;
		int i=0;
		Client client = null;
		while(!trouve && i< this.clients.size())
		{
			if(this.clients.get(i).getNom().equals(nom) && this.clients.get(i).getAgence().equals(agence))
			{
				trouve = true;
				client = this.clients.get(i);
			}
			i++;
		}
		return client;
	}


	@Override
	public ArrayList<Client> listeClients() throws RemoteException
	{		
		return this.clients;
	}
	
	@Override
	public ArrayList<Client> listeClients(Agence agence) throws RemoteException
	{	
		ArrayList<Client> listeClients = new ArrayList<Client>();
		for(int i=0; i < this.clients.size();i++)
		{
			if(this.clients.get(i).getAgence().equals(agence))
				listeClients.add(this.clients.get(i));
		}
		return listeClients;
	}
}
