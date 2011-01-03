package classe;

import java.io.Serializable;

/**
 * Cette classe permet de d�finir un compte
 *
 */
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
	
	public Compte(int montant, Client proprietaire)
	{
		this.numero = Compte.compteurNumero;
		this.montant = montant;
		this.proprietaire = proprietaire;
		this.agence = proprietaire.getAgence();
		Compte.compteurNumero++;

	}
	
	public String toString()
	{
		String desc ="";
		/*desc +="\n Numero de compte : "+numero;
		desc +="\n Montant : "+montant;
		desc +="\n Proprietaire: "+proprietaire;*/
		desc+="Compte numero : "+numero;
		return desc;
	}
	
	public void crediter(int montant)
	{
		this.montant += montant;
	}
	
	public void debiter(int montant)
	{
		this.montant -= montant;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Client getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Client proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}
}
