package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;


/**
 * Cette interface permet de définir le comportement général que doit avoir une Banque.
 *
 */
public interface Banque extends Remote
{
	/**
	 * Cette méthode permet d'insérer une nouvelle instance de Agence dans la liste des agences de la banque.
	 * Nous supposerons qu’il existe une agence par ville au maximum.
	 *  
	 * @param nomVille le nom de la ville de l'agence à insérer
	 * @param agence l'instance Agence
	 * @return vrai si l'insertion a bien été effectuée, faux sinon
	 * @throws RemoteException
	 */
	public boolean insererAgence(String nomVille, Agence agence) throws RemoteException;

	/**
	 * Cette méthode sert à retirer une agence à l'aide du nom de sa ville passé en paramètre.
	 * 
	 * @param nomVille le nom de la ville de l'agence à retirer
	 * @return vrai si la suppression c'est bien passée, sinon faux
	 * @throws RemoteException
	 */
	public boolean retirerAgence(String nomVille) throws RemoteException;

	/**
	 * Cette méthode permet de retourner une agence qui se trouve dans la ville <i>nomVille</i>.
	 *  
	 * @param nomVille le nom de la ville où se trouve l'agence que l'on cherche
	 * @return l'agence recherchée si elle exite, sinon null 
	 * @throws RemoteException
	 */
	public Agence rechercherAgence(String nomVille) throws RemoteException;

	/**
	 * Cette méthode permet de retourner la liste des agences de la banque par ville. 
	 * La ville de chaque agence doit être également retournée.
	 * 
	 * @return la liste des agences de la banque par ville. La ville de chaque agence doit être également retournée.
	 * @throws RemoteException
	 */
	public HashMap<String, Agence> listeAgences() throws RemoteException;
	
	/**
	 * Cette méthode permet de décrire une Banque.
	 * 
	 * @return la description de la Banque
	 * @throws RemoteException
	 */
	public String description() throws RemoteException;
}
