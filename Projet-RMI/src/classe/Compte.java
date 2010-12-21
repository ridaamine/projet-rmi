package classe;

import java.io.Serializable;

public class Compte implements Serializable
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//
	
	private static int compteurNumero = 0; // compteur permettant de gérer les numéros de compte
	private int numero; // numéro du compte
	private int montant; // somme d'argent qu'il y a sur le compte 
	private Client proprietaire; // client propriétaire du compte
	private Agence agence; // agence où le compte a été créé
	
	//TODO A compléter...
}
