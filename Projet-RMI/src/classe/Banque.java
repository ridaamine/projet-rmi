package classe;

import java.io.Serializable;

/**
 * Cette classe permet de définir une Banque.
 *
 */
public class Banque implements Serializable
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//
		
	private String nom; // nom de la banque
	private String adresseServeurAgence; // adresse du serveur qui gère les agences (ex: "//127.0.0.1/Agence")
	

//======================================================================//
//========================== Constructeurs =============================//
//======================================================================//
	
	/**
	 * Crée une nouvelle instance de <i>Banque</i>.
	 *
	 * @param nom nom de la banque (ex: "Crédit Agricole")
	 */
	public Banque(String nom)
	{
		this.nom = nom;
		this.adresseServeurAgence = "//127.0.0.1/Agence";
	}
	
	/**
	 * Crée une nouvelle instance de <i>Banque</i>.
	 *
	 * @param nom nom de la banque (ex: "Crédit Agicole")
	 * @param adresseServeur l'adresse du serveur qui gère les agences
	 */
	public Banque(String nom, String adresseServeur)
	{
		this.nom = nom;
		this.adresseServeurAgence = adresseServeur;
	}

	
//======================================================================//
//============================= Méthodes ===============================//
//======================================================================//
	
	@Override
	public String toString()
	{
		return "Banque: "+this.nom+"\n";
	}
	
	public String getNom() 
	{
		return nom;
	}

	public void setNom(String nom) 
	{
		this.nom = nom;
	}

	public String getAdresseServeurAgence() 
	{
		return adresseServeurAgence;
	}

	public void setAdresseServeurAgence(String adresseServeurAgence) 
	{
		this.adresseServeurAgence = adresseServeurAgence;
	}	
}
