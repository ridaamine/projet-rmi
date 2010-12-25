package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import main.Adresse;
import classe.Agence;
import classe.Banque;
import classe.Client;

/**
 * Cette interface permet de définir le comportement général que doit avoir une Agence.
 *
 */
public interface IAgence extends Remote 
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
	public int creerClient(String nom, String sexe, Adresse adresse, Agence agence) throws RemoteException;
	
	/**
	 * Cette méthode sert à détruire un client à l'aide de son numéro de client.
	 * 
	 * @param numero numéro du client à détruire
	 * @throws RemoteException
	 */
	public void detruireClient(int numero, Agence agence) throws RemoteException;

	/**
	 * Cette méthode permet de retrouver un client par son nom.
	 *  
	 * @param nom nom du client à retrouver
	 * @return le client rechercher si il est trouvé, sinon null
	 * @throws RemoteException
	 */
	public Client rechercheClient(String nom, Agence agence) throws RemoteException;

	/**
	 * Cette méthode permet de retourner la liste des clients de l'agence.
	 * 
	 * @return la liste des clients de l'agence
	 * @throws RemoteException
	 */
	public ArrayList<Client> listeClients(Agence agence) throws RemoteException;
	
	/**
	 * Cette méthode permet d'ajouter une nouvelle agence.
	 *  
	 * @param agence la nouvelle agence à ajouter
	 * @throws RemoteException 
	 */
	public void insererAgence(String nomVille, Agence agence) throws RemoteException;
	
	/**
	 * Cette méthode permet de retirer une agence grâce au nom de la ville où est cituée l'agence.
	 * 
	 * @param nomVille nom de la ville où se trouve l'agence
	 * @throws RemoteException
	 */
	public void retirerAgence(String nomVille) throws RemoteException;
	
	/**
	 * Cette méthode permet de chercher une agence gâce au nom de la ville où elle cituée.
	 *  
	 * @param nomVille nom de la ville où se trouve l'agence
	 * @return l'agence de la ville indiquée, sinon null
	 * @throws RemoteException
	 */
	public Agence rechercherAgence(String nomVille) throws RemoteException;
	
	/**
	 * Cette méthode permet de retourner toutes les agences du serveur.
	 * 
	 * @return les agences du serveur
	 * @throws RemoteException
	 */
	public HashMap<String, Agence> listeAgences() throws RemoteException;
	
	/**
	 * Cette méthode permet de retourner toutes les agences d'une banque du serveur.
	 * 
	 * @return les agences d'une banque du serveur
	 * @throws RemoteException
	 */
	public HashMap<String, Agence> listeAgences(Banque banque) throws RemoteException;
}
