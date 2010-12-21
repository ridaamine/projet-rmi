package implementation;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.Adresse;
import service.IAgence;
import service.IClient;
import service.ICompte;
import classe.Client;
import classe.Compte;


/**
 * Cette classe implemente le comportement général que doit avoir un client.
 *
 */
public class ClientImpl extends UnicastRemoteObject implements IClient
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//

	private ArrayList<Compte> comptes; // liste des comptes que possède le client

	
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
		//TODO A compléter...
	}
	

//======================================================================//
//====================== Méthodes Implémentées= ========================//
//======================================================================//
	
	@Override
	public void creerCompte(int soldeInitiale) throws RemoteException
	{
		//TODO A compléter...
	}
	
	@Override
	public void creerLivret(int soldeInitiale, double taux) throws RemoteException
	{
		//TODO A compléter...
	}
	
	@Override
	public ArrayList<ICompte> listeComptes() throws RemoteException
	{
		//TODO A compléter...
		
		return null;
	}
	
	@Override
	public void virement(int numCompte1, int numCompte2, int montant) throws RemoteException
	{
		//TODO A compléter...
	}

	@Override
	public void creerClient(Client client) throws RemoteException
	{
		//TODO A compléter...
	}


	@Override
	public void detruireClient(int numero) throws RemoteException
	{
		//TODO A compléter...
	}


	@Override
	public Client rechercheClient(String nom) throws RemoteException
	{
		//TODO A compléter...
		
		return null;
	}


	@Override
	public ArrayList<Client> listeClients() throws RemoteException
	{
		//TODO A compléter...
		
		return null;
	}
}
