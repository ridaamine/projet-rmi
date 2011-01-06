package implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.ThreadVirement;

import classe.Client;
import classe.Compte;
import classe.Livret;

import service.IAgence;
import service.IClient;
import service.ICompte;

public class CompteImpl extends UnicastRemoteObject implements ICompte
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//
	
	ArrayList<Compte> comptes; // Liste des comptes du serveur	

//======================================================================//
//========================== Constructeurs =============================//
//======================================================================//
	
	public CompteImpl() throws RemoteException
	{
		super();
		this.comptes = new ArrayList<Compte>();
	}


//======================================================================//
//====================== Méthodes Implémentées= ========================//
//======================================================================//
	
	@Override
	public void credit(int montant, int numCompte) throws RemoteException 
	{
		Compte compte = rechercheCompte(numCompte);
		compte.crediter(montant);
		System.out.println("Compte numero : "+numCompte+ "credité de "+montant);
	}


	@Override
	public void debit(int montant, int numCompte) throws RemoteException 
	{
		Compte compte = rechercheCompte(numCompte);
		compte.debiter(montant);
		System.out.println("Compte numero : "+numCompte+ "debité de "+montant);
	}


	@Override
	public void creerCompte(int solde, Client proprietaire) throws RemoteException 
	{
		Compte compte = new Compte(solde,proprietaire);
		this.comptes.add(compte);
		System.out.println("Creation du compte : "+compte);
		
	}
	


	@Override
	public void creerLivret(int solde, double taux, Client proprietaire) throws RemoteException 
	{
		Livret livret = new Livret(solde,taux,proprietaire);
		this.comptes.add(livret);
		System.out.println("Creation du Livret : "+livret);		
	}


	@Override
	public void detruireCompte(int numero) throws RemoteException 
	{
		
		boolean trouve = false;
		int i=0;
		while(!trouve && i< this.comptes.size())
		{
			if(this.comptes.get(i).getNumero() == numero)
			{
				trouve = true;
				this.comptes.remove(i);
				System.out.println("Destruction du compte");
			}
			i++;
		}
		if(!trouve)
		System.err.println("Erreur destruction compte : numero de compte inconnu");		
	}


	@Override
	public ArrayList<Compte> listeComptes() throws RemoteException {
		return this.comptes;
	}


	@Override
	public Compte rechercheCompte(int numero) throws RemoteException {
		boolean trouve = false;
		int i=0;
		Compte compte = null;
		while(!trouve)
		{
			if(this.comptes.get(i).getNumero() == numero)
			{
				trouve = true;
				compte = this.comptes.get(i);
			}
			i++;
		}
		return compte;
	}


	@Override
	public void virement(int numCompte1, int numCompte2, int montant)throws RemoteException 
	{
		boolean trouve1 = false, trouve2 = false;
		int i=0;
		Compte compte1 = null;
		Compte compte2 = null;
		while (!trouve1 || !trouve2)
		{
		
			if(this.comptes.get(i).getNumero() == numCompte1)
			{
				compte1 = this.comptes.get(i);
				trouve1 = true;
			}
			
			if(this.comptes.get(i).getNumero() == numCompte2)
			{
				compte2 = this.comptes.get(i);
				trouve2 = true;
			}
			i++;
		}
		if(trouve1 && trouve2 && numCompte1 != numCompte2 && compte1.getMontant() >= montant)
		{
			compte1.debiter(montant);
			compte2.crediter(montant);
			System.out.println("Transaction reussie du compte : "+numCompte1+" au compte : "+numCompte2+" de "+montant);
		}
		else
			System.err.println("Erreur dans la transaction du compte : "+numCompte1+" au compte : "+numCompte2+" de "+montant);
		
	}
	
	@Override
	public void genereVirementAleatoire(Client client) throws RemoteException 
	{
		ArrayList<Compte> comptes = rechercheToutCompte(client);
		int numC1, numC2, montant;
		if (comptes.size() >= 2)
		{
			numC1 = (int)(Math.random() * (comptes.size()));
			do
			{
				numC2 = (int)(Math.random() * (comptes.size()));
			}
			while (numC1 == numC2);
			
			montant = (int)(Math.random() * (rechercheCompte(numC1).getMontant()));
			
			ThreadVirement virementT = new ThreadVirement(client,comptes.get(numC1), comptes.get(numC2),montant);
		}
	}


	@Override
	public ArrayList<Compte> rechercheCompte(Client client) throws RemoteException 
	{
		ArrayList<Compte> listeComptes = new ArrayList<Compte>();
		for(int i=0; i< this.comptes.size();i++)
		{
			if(this.comptes.get(i).getProprietaire().equals(client) && this.comptes.get(i).getClass().toString().contains("Compte"))
			{
				listeComptes.add(this.comptes.get(i));
			}
		}
		return listeComptes;
	}


	@Override
	public ArrayList<Compte> rechercheLivret(Client client) throws RemoteException 
	{
		ArrayList<Compte> listeComptes = new ArrayList<Compte>();
		for(int i=0; i< this.comptes.size();i++)
		{
			if(this.comptes.get(i).getProprietaire().equals(client) && this.comptes.get(i).getClass().toString().contains("Livret"))
			{
				listeComptes.add(this.comptes.get(i));
			}
		}
		return listeComptes;
	}
	
	@Override
	public ArrayList<Compte> rechercheToutCompte(Client client) throws RemoteException 
	{
		ArrayList<Compte> listeComptes = new ArrayList<Compte>();
		for(int i=0; i< this.comptes.size();i++)
		{
			if(this.comptes.get(i).getProprietaire().equals(client))
			{
				listeComptes.add(this.comptes.get(i));
			}
		}
		return listeComptes;
	}

}
