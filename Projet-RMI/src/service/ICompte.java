package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import classe.Client;
import classe.Compte;

/**
 * Cette interface permet de définir le comportement général que doit avoir un Compte.
 *
 */
public interface ICompte extends Remote
{
	/**
	 * Cette méthode permet de crédit le compte courant du montant fourni en paramètre.
	 *  
	 * @param montant la somme d'argent à crédit du compte
	 * @throws RemoteException
	 */
	public void credit(int montant, Compte compte) throws RemoteException;
	
	/**
	 * Cette méthode permet de débit le compte courant du montant fourni en paramètre.
	 * 
	 * @param montant la somme d'argent à cébit du compte
	 * @throws RemoteException
	 */
	public void debit(int montant, Compte compte) throws RemoteException;
	
	
	/**
	 * Cette methode permet de faire un virement d'un compte à un autre.
	 * @param numCompte1 compte à debiter
	 * @param numCompte2 compte à crediter
	 * @param montant montant à debiter/crediter
	 * @throws RemoteException
	 */
	public void virement(int numCompte1, int numCompte2, int montant)throws RemoteException;
	
	/**
	 * Cette méthode permet de créer un nouveau Compte.
	 * @param solde du compte.
	 * @param proprietaire du compte
	 * @throws RemoteException
	 */
	public void creerCompte(int solde, Client proprietaire) throws RemoteException;
	
	/**
	 * Cette méthode permet de créer un nouveau Livret.
	 * @param solde du livret.
	 * @param taux d'interet du livret
	 * @param proprietaire du livret
	 * @throws RemoteException
	 */
	public void creerLivret(int solde,double taux, Client proprietaire) throws RemoteException;
	
	/**
	 * Cette méthode permet de supprimer un compte.
	 * 
	 * @param compte compte que l'on veut détruire
	 * @throws RemoteException
	 */
	public void detruireCompte(int numero) throws RemoteException;
	
	/**
	 * Cette méthode permet de chercher un compte.
	 *  
	 * @param numero du compte que l'on veut trouver
	 * @return le compte si il existe, sinon null
	 * @throws RemoteException
	 */
	public Compte rechercheCompte(int numero) throws RemoteException;
	
	/**
	 * Cette méthode permet d'obtenir un compte en fonction de son numero.
	 *  
	 * @return tous les compte d'un client
	 * @throws RemoteException
	 */
	public ArrayList<Compte> listeComptes() throws RemoteException;

	/**
	 * Cette méthode permet d'obtenir la liste des comptes d'un client.
	 * @param client client dont on veut obtenir la liste de compte
	 * @return tous les compte d'un client
	 * @throws RemoteException
	 */
	public ArrayList<Compte> rechercheCompte(Client client)throws RemoteException ;

	
	/**
	 * Cette méthode permet d'obtenir la liste des livrets d'un client
	 * @param client client dont on veut obtenir la liste de livret
	 * @return tous les livrets du client
	 * @throws RemoteException
	 */
	public ArrayList<Compte> rechercheLivret(Client client) throws RemoteException ;

	
	/**
	 * Cette méthode permet d'obtenir la liste de tous les comptes d'un client(livret inclu)
	 * @param client client dont on veut obtenir la liste de comptes
	 * @return tous les comptess du client
	 * @throws RemoteException
	 */
	public ArrayList<Compte> rechercheToutCompte(Client client) throws RemoteException;



}
