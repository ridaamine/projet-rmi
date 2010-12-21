package implementation;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import main.Adresse;
import service.IAgence;
import service.IClient;
import classe.Agence;
import classe.Client;

/**
 * Cette classe implemente le comportement général que doit avoir une agence.
 *
 */
public class AgenceImpl extends UnicastRemoteObject implements IAgence
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//
	
	private HashMap<String, Agence> agences; // liste des agences

	
//======================================================================//
//========================== Constructeurs =============================//
//======================================================================//	
	
	/**
	 * Crée une nouvelle instance de <i>AgenceImpl</i>.
	 *
	 * @throws RemoteException
	 */
	public AgenceImpl() throws RemoteException
	{
		super();
		this.agences = new HashMap<String, Agence>();
	}

	
//======================================================================//
//====================== Méthodes Implémentées =========================//
//======================================================================//
	
	@Override
	public int creerClient(String nom, String sexe, Adresse adresse, Agence agence) throws RemoteException 
	{
		Client client = new Client(nom, sexe, adresse, agence);
		
		try 
		{
			IClient serveurClient;
			serveurClient = (IClient) Naming.lookup(agence.getAdresseServeurClient());
			serveurClient.creerClient(client);
		} 
		catch (MalformedURLException e) { System.err.println("Erreur d'URL du serveur d'agence lors de l'ajout d'une agence."); }
		catch (NotBoundException e) { System.err.println("Impossible de créer un lien avec le serveur d'agence"); }
		
		return client.getNumero();
	}

	@Override
	public void detruireClient(int numero, Agence agence) throws RemoteException 
	{
		try 
		{
			IClient serveurClient;
			serveurClient = (IClient) Naming.lookup(agence.getAdresseServeurClient());
			serveurClient.detruireClient(numero);
		} 
		catch (MalformedURLException e) { System.err.println("Erreur d'URL du serveur d'agence lors de l'ajout d'une agence."); }
		catch (NotBoundException e) { System.err.println("Impossible de créer un lien avec le serveur d'agence"); }
	}

	@Override
	public Client rechercheClient(String nom, Agence agence) throws RemoteException 
	{
		Client client = null;
		try 
		{
			IClient serveurClient;
			serveurClient = (IClient) Naming.lookup(agence.getAdresseServeurClient());
			client = serveurClient.rechercheClient(nom);
		} 
		catch (MalformedURLException e) { System.err.println("Erreur d'URL du serveur d'agence lors de l'ajout d'une agence."); }
		catch (NotBoundException e) { System.err.println("Impossible de créer un lien avec le serveur d'agence"); }
		
		return client;
	}

	@Override
	public ArrayList<Client> listeClients(Agence agence) throws RemoteException 
	{
		ArrayList<Client> clients = null;
		
		try 
		{
			IClient serveurClient;
			serveurClient = (IClient) Naming.lookup(agence.getAdresseServeurClient());
			clients = serveurClient.listeClients();
		} 
		catch (MalformedURLException e) { System.err.println("Erreur d'URL du serveur d'agence lors de l'ajout d'une agence."); }
		catch (NotBoundException e) { System.err.println("Impossible de créer un lien avec le serveur d'agence"); }
		
		return clients;
	}

	@Override
	public void insererAgence(String nomVille, Agence agence) throws RemoteException 
	{
		this.agences.put(nomVille, agence);
		System.out.println("Creation de l'agence: \n"+agence);
	}

	@Override
	public void retirerAgence(String nomVille) throws RemoteException 
	{
		Agence agence = this.agences.remove(nomVille);
		System.out.println("Suppression de l'agence: \n"+agence);
	}

	@Override
	public Agence rechercherAgence(String nomVille) throws RemoteException 
	{
		return this.agences.get(nomVille);
	}

	@Override
	public HashMap<String, Agence> listeAgences() throws RemoteException 
	{
		return this.agences;
	}
}