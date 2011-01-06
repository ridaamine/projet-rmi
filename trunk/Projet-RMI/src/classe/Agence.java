package classe;

import java.io.Serializable;

import main.Adresse;

/**
 * Cette classe permet de définir une Agence.
 *
 */
public class Agence implements Serializable
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//
	
	private static int compteurNumero = 0; // compteur permettant de gérer les numéros d'agence
	private int numero; // numéro de l'agence
	private Adresse adresse; // adresse de l'agence
	private Banque banque; // banque à laquelle elle appartient
	private String adresseServeurClient; // adresse du serveur qui gère les clients (ex: "//127.0.0.1/Client")
	
	
//======================================================================//
//========================== Constructeurs =============================//
//======================================================================//
	
	/**
	 * Crée une nouvelle instance de <i>Agence</i>.
	 *
	 * @param adresse adresse de l'agence
	 * @param banque banque à laquelle elle est appartient
	 */
	public Agence(Adresse adresse, Banque banque)
	{
		this.numero = Agence.compteurNumero;
		this.adresse = adresse;
		this.banque = banque;
		this.adresseServeurClient = "//169.254.241.250/Client";
		
		Agence.compteurNumero++;
	}
	
	/**
	 * Crée une nouvelle instance de <i>Agence</i>.
	 *
	 * @param adresse adresse de l'agence
	 * @param banque banque à laquelle elle est appartient
	 * @param adresseServeurClient adresse du serveur qui gère les clients (ex: "//127.0.0.1/Client")
	 */
	public Agence(Adresse adresse, Banque banque, String adresseServeurClient)
	{
		this.numero = Agence.compteurNumero;
		this.adresse = adresse;
		this.banque = banque;
		this.setAdresseServeurClient(adresseServeurClient);
		
		Agence.compteurNumero++;
	}


//======================================================================//
//============================= Méthodes ===============================//
//======================================================================//
	
	@Override
	public String toString()
	{
		/*String description = "Numero: "+this.numero+"\n";
		description += this.adresse+"\n";
		description += this.banque+"\n";*/
		
		String description =" Agence "+banque.getNom()+" "+adresse.getVille();
		
		return description;
	}
	
	public boolean equals(Agence agence)
	{
		if (this.numero == ((Agence)agence).getNumero())
			return true;
		else 
			return false;
	}
	
	public int getNumero() 
	{
		return numero;
	}

	public Adresse getAdresse() 
	{
		return adresse;
	}

	public void setAdresse(Adresse adresse) 
	{
		this.adresse = adresse;
	}

	public Banque getBanque() 
	{
		return banque;
	}

	public void setBanque(Banque banque) 
	{
		this.banque = banque;
	}

	public void setAdresseServeurClient(String adresseServeurClient)
	{
		this.adresseServeurClient = adresseServeurClient;
	}

	public String getAdresseServeurClient()
	{
		return adresseServeurClient;
	}
}
