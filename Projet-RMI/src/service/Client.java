package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Cette interface permet de définir le comportement général que doit avoir un Client.
 *
 */
public interface Client extends Remote
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
	public ArrayList<Compte> listeComptes() throws RemoteException;

	/**
	 * Cette méthode permet d'effectuer un virement du compte (<i>numCompte1</i>) au compte (<i>numCompte2</i>) d'un même client d'une valeur de <i>montant</i>.
	 * 
	 * @param numCompte1 numéro du compte à débiter
	 * @param numCompte2 numéro du compte à créditer
	 * @param montant valeur du virement
	 * @return vrai si le virement c'est bien effectuer, sinon faux
	 * @throws RemoteException
	 */
	public boolean virement(int numCompte1, int numCompte2, int montant) throws RemoteException;
	
	/**
	 * Cette méthode permet de retourner le numéro du client.
	 * 
	 * @return le numéro du client
	 * @throws RemoteException
	 */
	public int getNumero() throws RemoteException;
	
	/**
	 * Cette méthode permet de le nom du client.
	 * 
	 * @return le nom du client
	 * @throws RemoteException
	 */
	public String getNom() throws RemoteException;
}
