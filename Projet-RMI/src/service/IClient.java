package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import classe.Client;

/**
 * Cette interface permet de définir le comportement général que doit avoir un Client.
 *
 */
public interface IClient extends Remote
{
	/**
	 * Cette méthode permet de créer un nouveau compte pour le client.
	 * 
	 * @param soldeInitiale valeur initiale du solde du nouveau compte du client
	 * @throws RemoteException
	 */
	public void creerCompte(int soldeInitiale) throws RemoteException;

	/**
	 * Cette méthode permet de créer un nouveau livret pour le client.
	 * 
	 * @param soldeInitiale valeur initiale du solde du nouveau livret du client
	 * @param taux valeur du taux du nouveau livret
	 * @throws RemoteException
	 */
	public void creerLivret(int soldeInitiale, double taux) throws RemoteException;

	
	/**
	 * Cette méthode permet de retourner la liste des comptes ou livrets que possède le client.
	 *  
	 * @return la liste des comptes ou livrets que possède le client
	 * @throws RemoteException
	 */
	public ArrayList<ICompte> listeComptes() throws RemoteException;

	/**
	 * Cette méthode permet d'effectuer un virement du compte (<i>numCompte1</i>) au compte (<i>numCompte2</i>) d'un même client d'une valeur de <i>montant</i>.
	 * 
	 * @param numCompte1 numéro du compte à débiter
	 * @param numCompte2 numéro du compte à créditer
	 * @param montant valeur du virement
	 * @throws RemoteException
	 */
	public void virement(int numCompte1, int numCompte2, int montant) throws RemoteException;
	
	/**
	 * Cette méthode permet de créer un nouveau client d'une l'agence.
	 *  
	 * @param client client que l'on veut créer
	 * @throws RemoteException
	 */
	public void creerClient(Client client) throws RemoteException;
	
	/**
	 * Cette méthode permet de supprimer un client d'une agence.
	 * 
	 * @param numero numéro du client que l'on veut détruire
	 * @throws RemoteException
	 */
	public void detruireClient(int numero) throws RemoteException;
	
	/**
	 * Cette méthode permet de chercher dans l'agence un client grâce à son nom.
	 *  
	 * @param nom nom du client que l'on veut trouver
	 * @return le client si il existe, sinon null
	 * @throws RemoteException
	 */
	public Client rechercheClient(String nom) throws RemoteException;
	
	/**
	 * Cette méthode permet d'obtenir la liste des clients d'une agence.
	 *  
	 * @return tous les clients d'une agence
	 * @throws RemoteException
	 */
	public ArrayList<Client> listeClients() throws RemoteException;
}
