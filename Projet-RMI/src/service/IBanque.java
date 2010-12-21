package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

import classe.Agence;
import classe.Banque;


/**
 * Cette interface permet de définir le comportement général que doit avoir une Banque.
 *
 */
public interface IBanque extends Remote
{
	/**
	 * Cette méthode permet d'insérer une nouvelle instance de Agence dans la liste des agences de la banque.
	 * Nous supposerons qu’il existe une agence par ville au maximum.
	 *  
	 * @param nomVille le nom de la ville de l'agence à insérer
	 * @param agence l'instance de la nouvelle agence à inserer
	 * @throws RemoteException
	 */
	public void insererAgence(String nomVille, Agence agence) throws RemoteException;

	/**
	 * Cette méthode sert à retirer une agence à l'aide du nom de sa ville passé en paramètre.
	 * 
	 * @param nomVille le nom de la ville de l'agence à retirer
	 * @param banque banque à laquelle on veut retirer l'agence
	 * @throws RemoteException
	 */
	public void retirerAgence(String nomVille, Banque banque) throws RemoteException;

	/**
	 * Cette méthode permet de retourner une agence qui se trouve dans la ville <i>nomVille</i>.
	 *  
	 * @param nomVille le nom de la ville où se trouve l'agence que l'on cherche
	 * @param banque banque où l'on veut rechercher l'agence
	 * @return l'agence recherchée si elle exite, sinon null 
	 * @throws RemoteException
	 */
	public Agence rechercherAgence(String nomVille, Banque banque) throws RemoteException;

	/**
	 * Cette méthode permet de retourner la liste des agences de la banque par ville. 
	 * La ville de chaque agence doit être également retournée.
	 * 
	 * @param banque banque qu'on veut voir toutes ses agences
	 * @return la liste des agences de la banque par ville. La ville de chaque agence doit être également retournée.
	 * @throws RemoteException
	 */
	public HashMap<String, Agence> listeAgences(Banque banque) throws RemoteException;
	
	/**
	 * Cette méthode permet de créer une nouvelle banque sur le serveur de banque.
	 *  
	 * @param nom de la banque à créer
	 * @throws RemoteException
	 */
	public void creerBanque(String nom) throws RemoteException;
	
	/**
	 * Cette méthode permet de créer une nouvelle banque sur le serveur de banque.
	 *  
	 * @param nom de la banque à créer
	 * @param adresseServeurAgence l'adresse du serveur qui gèrera les agences de la banque
	 * @throws RemoteException
	 */
	public void creerBanque(String nom, String adresseServeurAgence) throws RemoteException;
}
