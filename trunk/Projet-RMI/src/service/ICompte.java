package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
	public void credit(int montant) throws RemoteException;
	
	/**
	 * Cette méthode permet de débit le compte courant du montant fourni en paramètre.
	 * 
	 * @param montant la somme d'argent à cébit du compte
	 * @throws RemoteException
	 */
	public void debit(int montant) throws RemoteException;
}
