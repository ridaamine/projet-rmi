package implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.Agence;
import service.Client;
import service.Compte;

public class CompteImpl extends UnicastRemoteObject implements Compte
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//
	
	private static int compteurNumero = 0; // compteur permettant de gérer les numéros de compte
	private int numero; // numéro de l'agence
	private int montant; // somme d'argent qu'il y a sur le compte 
	private Client proprietaire; // client propriétaire du compte
	private Agence agence; // agence où le compte a été créé
	

//======================================================================//
//========================== Constructeurs =============================//
//======================================================================//
	
	public CompteImpl(int montant, Client proprietaire, Agence agence) throws RemoteException
	{
		super();
		this.numero = this.compteurNumero;
		this.montant = montant;
		this.proprietaire = proprietaire;
		this.agence = agence;
	}


//======================================================================//
//====================== Méthodes Implémentées= ========================//
//======================================================================//
	
	@Override
	public void credit(int montant) throws RemoteException 
	{
		this.montant += montant;
	}


	@Override
	public void debit(int montant) throws RemoteException 
	{
		this.montant -= montant;
	}
	
	@Override
	public int getNumero()
	{
		return this.numero;
	}
}
