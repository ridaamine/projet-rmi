package classe;

import java.io.Serializable;

import main.Adresse;

/**
 * Cette classe permet de définir un Client.
 *
 */
public class Client implements Serializable
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//
	
	private static int compteurNumero = 0; // compteur permettant de gérer les numéros client
	private int numero; // numéro du client
	private String nom; // nom du client
	private String sexe; // sexe du client
	private Adresse adresse; // adresse du client
	private Agence agence; // l'agence gérant les comptes du client
	private String adresseServeurCompte;
	private String adresseServeurLivret;

	
	
//======================================================================//
//========================== Constructeurs =============================//
//======================================================================//
	
	/**
	 * Crée une nouvelle instance de <i>Client</i>.
	 *
	 * @param nom nom du nouveau client
	 * @param sexe sexe du nouveau client
	 * @param adresse adresse du nouveau client
	 * @param agence agence du nouveau client
	 */
	public Client(String nom, String sexe, Adresse adresse, Agence agence)
	{
		this.numero = Client.compteurNumero;
		this.nom = nom;
		this.sexe = sexe;
		this.adresse = adresse;
		this.agence = agence;
		
		this.adresseServeurCompte = "//169.254.241.250/Compte";
		this.adresseServeurLivret = "//169.254.241.250/Livret";

		
		Client.compteurNumero++;
	}


//======================================================================//
//============================= Méthodes ===============================//
//======================================================================//
	
	public String toString()
	{
		String desc ="";
		/*desc +="\n Numero de client : "+numero;
		desc +="\n Nom : "+nom;
		desc +="\n Sexe : "+sexe;
		desc+="\n Adresse : "+adresse;
		desc+="\n Agence : "+agence.getBanque().getNom()+" "+agence.getAdresse().getVille();*/
		desc +="Client : "+numero+" Nom : "+nom;
		return desc;
	}
	
	public boolean equals(Client client)
	{
		if(this.numero == client.numero && this.nom.equals(client.nom))
			return true;
		else
			return false;
	}
	
	public int getNumero()
	{
		return numero;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getSexe()
	{
		return sexe;
	}

	public void setSexe(String sexe)
	{
		this.sexe = sexe;
	}

	public Adresse getAdresse()
	{
		return adresse;
	}

	public void setAdresse(Adresse adresse)
	{
		this.adresse = adresse;
	}

	public Agence getAgence()
	{
		return agence;
	}

	public void setAgence(Agence agence)
	{
		this.agence = agence;
	}


	public String getAdresseServeurCompte() 
	{
		return this.adresseServeurCompte;
	}


	public String getAdresseServeurLivret() 
	{
		return this.adresseServeurLivret;
	}
}
