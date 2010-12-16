package implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map.Entry;

import service.Agence;
import service.Banque;


/**
 * Cette classe implemente le comportement général que doit avoir une banque.
 *
 */
public class BanqueImpl extends UnicastRemoteObject implements Banque
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//
	
	private String nom; // nom de la banque
	private HashMap<String, Agence> agences; // liste des agences de la banque


//======================================================================//
//========================== Constructeurs =============================//
//======================================================================//

	/**
	 * Crée une nouvelle instance de <i>BanqueImpl</i>.
	 *
	 * @param nom nom de la banque
	 * @throws RemoteException
	 */
	public BanqueImpl(String nom) throws RemoteException
	{
		super();
		this.nom = nom;
		this.agences = new HashMap<String, Agence>();
	}
	

//======================================================================//
//====================== Méthodes Implémentées =========================//
//======================================================================//
	
	@Override
	public boolean insererAgence(String nomVille, Agence agence) throws RemoteException
	{
		if(this.rechercherAgence(nomVille) == null)
		{
			this.agences.put(nomVille, agence);
			System.out.println("Une nouvelle agence vient d'etre ajoutee dans la ville de "+nomVille+".");
			
			return true;
		}
		else
			return false;
	}
	
	@Override
	public boolean retirerAgence(String nomVille) throws RemoteException
	{	
		if(this.agences.remove(nomVille) != null)
		{
			System.out.println("L'agence de la ville de "+nomVille+" vient d'etre supprimee.");
			
			return true;
		}
		else
			return false;
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
	
	@Override
	public String description() throws RemoteException
	{
		String description = "Banque: "+this.nom+"\n------------------------------\n";	
		
		for (Entry<String, Agence> valeur : this.agences.entrySet()) 
	        description += valeur.getKey()+":\n  "+valeur.getValue().description()+"\n";
		
		return description;
	}
}
