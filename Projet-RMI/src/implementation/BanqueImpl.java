package implementation;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import service.IAgence;
import service.IBanque;
import classe.Agence;
import classe.Banque;


/**
 * Cette classe implemente le comportement général que doit avoir une banque.
 *
 */
public class BanqueImpl extends UnicastRemoteObject implements IBanque
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//
	
	private ArrayList<Banque> banques;

//======================================================================//
//========================== Constructeurs =============================//
//======================================================================//

	/**
	 * Crée une nouvelle instance de <i>BanqueImpl</i>.
	 *
	 * @param banque banque qui sera créée lors de la création du serveur
	 * @throws RemoteException
	 */
	public BanqueImpl() throws RemoteException
	{
		super();
		this.banques = new ArrayList<Banque>();
//		this.banques.add(banque);
//		this.banques.add(new Banque ("Banque Courtois"));
	}
	

//======================================================================//
//====================== Méthodes Implémentées =========================//
//======================================================================//
	
	@Override
	public void insererAgence(String nomVille, Agence agence) throws RemoteException
	{
		try 
		{
			IAgence serveurAgence = (IAgence) Naming.lookup(agence.getBanque().getAdresseServeurAgence());
			serveurAgence.insererAgence(nomVille, agence);
		} 
		catch (MalformedURLException e) { System.err.println("Erreur d'URL du serveur d'agence lors de l'ajout d'une agence."); }
		catch (NotBoundException e) { System.err.println("Impossible de créer un lien avec le serveur d'agence"); }
		
	}
	
	@Override
	public void retirerAgence(String nomVille, Banque banque) throws RemoteException
	{	
		try 
		{
			IAgence serveurAgence = (IAgence) Naming.lookup(banque.getAdresseServeurAgence());
			serveurAgence.retirerAgence(nomVille);
		} 
		catch (MalformedURLException e) { System.err.println("Erreur d'URL du serveur d'agence lors de l'ajout d'une agence."); }
		catch (NotBoundException e) { System.err.println("Impossible de créer un lien avec le serveur d'agence"); }
	}
		
	@Override
	public Agence rechercherAgence(String nomVille, Banque banque) throws RemoteException
	{
		Agence agence = null;
		
		try 
		{
			IAgence serveurAgence = (IAgence) Naming.lookup(banque.getAdresseServeurAgence());
			agence = serveurAgence.rechercherAgence(nomVille);
		} 
		catch (MalformedURLException e) { System.err.println("Erreur d'URL du serveur d'agence lors de l'ajout d'une agence."); }
		catch (NotBoundException e) { System.err.println("Impossible de créer un lien avec le serveur d'agence"); }
		
		return agence;
	}
	
	@Override
	public HashMap<String, Agence> listeAgences(Banque banque) throws RemoteException
	{
		HashMap<String, Agence> agences = null;
		try 
		{
			if(banque != null)
			{
				IAgence serveurAgence = (IAgence) Naming.lookup(banque.getAdresseServeurAgence());
				agences = serveurAgence.listeAgences(banque);
			}
		} 
		catch (MalformedURLException e) { System.err.println("Erreur d'URL du serveur d'agence lors de l'ajout d'une agence."); }
		catch (NotBoundException e) { System.err.println("Impossible de créer un lien avec le serveur d'agence"); }
		
		return agences;
	}

	@Override
	public void creerBanque(String nom) throws RemoteException
	{
		this.banques.add(new Banque(nom));
		System.out.println("Creation banque : "+nom);
	}
	
	@Override
	public void creerBanque(String nom, String adresseServeurAgence) throws RemoteException
	{
		this.banques.add(new Banque(nom, adresseServeurAgence));
		System.out.println("Creation banque : "+nom);
	}


	@Override
	public ArrayList<Banque> listeBanques() throws RemoteException
	{

		return this.banques;
	}


	@Override
	public HashMap<String, Agence> listeAgences() throws RemoteException 
	{
		HashMap<String, Agence> agences = null;
		try 
		{
				IAgence serveurAgence = (IAgence) Naming.lookup("Agence");
				agences = serveurAgence.listeAgences();
		} 
		catch (MalformedURLException e) { System.err.println("Erreur d'URL du serveur d'agence lors de l'ajout d'une agence."); }
		catch (NotBoundException e) { System.err.println("Impossible de créer un lien avec le serveur d'agence"); }
		
		return agences;
	}


	@Override
	public Banque rechercherBanque(String nom) throws RemoteException 
	{
		boolean trouve = false;
		int i =0;
		Banque banque = null;
		while(!trouve && i < this.banques.size())
		{
			if(this.banques.get(i).getNom().equals(nom))
			{
				trouve = true;
				banque = this.banques.get(i);
			}
			i++;
		}
		return banque;
	}


	@Override
	public void supprimerBanque(String nom) throws RemoteException 
	{
		boolean trouve = false;
		int i =0;
		while(!trouve && i < this.banques.size())
		{
			if(this.banques.get(i).getNom().equals(nom))
			{
				trouve = true;
				this.banques.remove(i);
				System.out.println("Banque : "+ nom+" supprimé");
			}
			i++;
		}
		if(!trouve)
			System.err.println("Banque : "+nom+" n'existe pas : impossible a supprimer");
	}
}
