package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.Adresse;

/**
 * Cette interface permet de définir le comportement général que doit avoir une Agence.
 *
 */
public interface Agence extends Remote 
{	
	/**
	 * Cette méthode permet de créer une nouvelle instance de Client.
	 * 
	 * @param nom nom du client que l'on veut créer
	 * @param sexe sexe du client que l'on veut créer
	 * @param adresse adresse adresse du client que l'on veut créer
	 * @return le numéro du client que l'on vient de créer
	 * @throws RemoteException
	 */
	public int creerClient(String nom, String sexe, Adresse adresse) throws RemoteException;
	
	/**
	 * Cette méthode sert à détruire un client à l'aide de son numéro de client.
	 * 
	 * @param numero numéro du client à détruire
	 * @return vrai si le client a bien été détruit, sinon faux
	 * @throws RemoteException
	 */
	public boolean detruireClient(int numero) throws RemoteException;

	/**
	 * Cette méthode permet de retrouver un client par son nom.
	 *  
	 * @param nom nom du client à retrouver
	 * @return le client rechercher si il est trouvé, sinon null
	 * @throws RemoteException
	 */
	public Client rechercheClient(String nom) throws RemoteException;

	/**
	 * Cette méthode permet de retourner la liste des clients de l'agence.
	 * 
	 * @return la liste des clients de l'agence
	 * @throws RemoteException
	 */
	public ArrayList<Client> listeClients() throws RemoteException;
	
	/**
	 * Cette méthode permet de décrire une agence.
	 * 
	 * @return la description de l'agence
	 * @throws RemoteException
	 */
	public String description() throws RemoteException;
}
