package implementation;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.Adresse;
import service.Agence;
import service.Client;
import service.Compte;


/**
 * Cette classe implemente le comportement général que doit avoir un client.
 *
 */
public class ClientImpl extends UnicastRemoteObject implements Client
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//
	
	private static int compteurNumero = 0; // compteur permettant de gérer les numéros client
	private int numero;// numéro du client
	private String nom; // nom du client
	private String sexe; // sexe du client
	private Adresse adresse; // adresse du client
	private Agence agence; // l'agence gérant les comptes du client
	private ArrayList<Compte> compte; // liste des comptes que possède le client

	
//======================================================================//
//========================== Constructeurs =============================//
//======================================================================//
	
	/**
	 * Crée une nouvelle instance de <i>ClientImpl</i>.
	 *
	 * @param nom nom du client
	 * @param sexe sexe du client
	 * @param adresse adresse du client
	 * @param agence l'agence gérant les comptes du client
	 * @throws RemoteException
	 */
	public ClientImpl(String nom, String sexe, Adresse adresse, Agence agence) throws RemoteException
	{
		super();
		this.numero = this.compteurNumero;
		this.nom = nom;
		this.sexe = sexe;
		this.adresse = new Adresse(adresse);
		this.agence = agence;
		this.compteurNumero++;
	}
	

//======================================================================//
//====================== Méthodes Implémentées= ========================//
//======================================================================//
	
	@Override
	public void creerCompte(int soldeInitiale) throws RemoteException
	{
		Compte compte = new CompteImpl(soldeInitiale, this, this.agence);
		System.out.println("Un nouveau compte n°"+compte.getNumero()+" vient d'etre cree.");
		this.compte.add(compte);
	}
	
	@Override
	public void creerLivret(int soldeInitiale, double taux) throws RemoteException
	{
		Compte livret = new LivretImpl(soldeInitiale, this, this.agence, taux);
		System.out.println("Un nouveau livret n°"+livret.getNumero()+" vient d'etre cree.");
		this.compte.add(livret);
	}
	
	@Override
	public ArrayList<Compte> listeComptes() throws RemoteException
	{
		return this.compte;
	}
	
	@Override
	public boolean virement(int numCompte1, int numCompte2, int montant) throws RemoteException
	{
		Compte compte1 = this.rechercheCompte(numCompte1);
		Compte compte2 = this.rechercheCompte(numCompte2);
		
		if(compte1 != null && compte2 != null)
		{
			compte1.debit(montant);
			compte2.credit(montant);
			
			return true;
		}
		else
			return false;
	}
	
	@Override
	public int getNumero() throws RemoteException
	{
		return this.numero;
	}
	
	@Override
	public String getNom() throws RemoteException
	{
		return this.nom;
	}

	
//======================================================================//
//============================= Méthodes ===============================//
//======================================================================//
		
		/**
		 * Permet de retrouver un compte par son numero.
		 * 
		 * @param numero numero du compte à retrouver
		 * @return le compte rechercher si il est trouver, sinon null
		 * @throws RemoteException 
		 */
		private Compte rechercheCompte(int numero) throws RemoteException
		{
			boolean trouver = false;
			int i = 0;
			
			while(i < this.compte.size() && !trouver)
			{
				if(this.compte.get(i).getNumero() == numero)
					trouver = true;
					
				i++;
			}
			
			return (trouver)? this.compte.get(i - 1) : null;
		}
}
