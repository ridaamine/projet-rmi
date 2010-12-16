package implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.Adresse;
import service.Agence;
import service.Banque;
import service.Client;

/**
 * Cette classe implemente le comportement général que doit avoir une agence.
 *
 */
public class AgenceImpl extends UnicastRemoteObject implements Agence
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//
	
	private static int compteurNumero = 0; // compteur permettant de gérer les numéros d'agence
	private int numero;// numéro de l'agence
	private Adresse adresse; // adresse de l'agence
	private Banque banque; // banque à laquelle elle appartient
	private ArrayList<Client> clients; // liste des clients de l'agence
	
	
//======================================================================//
//========================== Constructeurs =============================//
//======================================================================//

	/**
	 * Crée une nouvelle instance de <i>AgenceImpl</i>.
	 *
	 * @param adresse adresse de la nouvelle agence
	 * @param banque banque a laquelle l'agence est affectée
	 * @throws RemoteException
	 */
	public AgenceImpl(Adresse adresse, Banque banque) throws RemoteException
	{
		super();
		this.numero = this.compteurNumero;
		this.adresse = new Adresse(adresse);
		this.banque = banque;
		this.clients = new ArrayList<Client>();
		this.compteurNumero++;
	}

	
//======================================================================//
//====================== Méthodes Implémentées =========================//
//======================================================================//
	
	@Override
	public int creerClient(String nom, String sexe, Adresse adresse) throws RemoteException
	{
		Client client = new ClientImpl(nom, sexe, adresse, this);
		this.clients.add(client);
		System.out.println("Un nouveau client n°"+client.getNumero()+" vient d'etre cree."); //TODO sysout
		
		return client.getNumero();
	}
	
	@Override
	public boolean detruireClient(int numero) throws RemoteException
	{
		Client client = this.rechercheClient(numero);
		
		if(this.clients.remove(client))
		{
			System.out.println("Le client n°"+client.getNumero()+" vient d'etre supprimee."); //TODO sysout
			
			return true;
		}
		else
			return false;
	}
	
	@Override
	public Client rechercheClient(String nom) throws RemoteException
	{
		boolean trouver = false;
		int i = 0;
		
		while(i < this.clients.size() && !trouver)
		{
			if(this.clients.get(i).getNom() == nom)
				trouver = true;

			i++;
		}
		
		return (trouver)? this.clients.get(i - 1) : null;
	}
	
	@Override
	public ArrayList<Client> listeClients() throws RemoteException
	{
		return this.clients;
	}
	
	@Override
	public String description() throws RemoteException
	{
		String description = "Numero: "+this.numero+"\n";
		description += this.adresse+"\n";
		
		return description;
	}
	
	
//======================================================================//
//============================= Méthodes ===============================//
//======================================================================//
	
	/**
	 * Permet de retrouver un client par son numero.
	 * 
	 * @param numero numero du client à retrouver
	 * @return le client rechercher si il est trouver, sinon null
	 * @throws RemoteException 
	 */
	private Client rechercheClient(int numero) throws RemoteException
	{
		boolean trouver = false;
		int i = 0;
		
		while(i < this.clients.size() && !trouver)
		{
			if(this.clients.get(i).getNumero() == numero)
				trouver = true;
				
			i++;
		}
		
		return (trouver)? this.clients.get(i - 1) : null;
	}
}
