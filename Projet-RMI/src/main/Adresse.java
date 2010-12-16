package main;

/**
 * Cette class permet de définir une adresse.
 *
 */
public class Adresse 
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//
	
	private String rue; // nom de la rue
	private String ville; // nom de la ville
	private String numTel; //numéro de téléphone


//======================================================================//
//========================== Constructeurs =============================//
//======================================================================//

	/**
	 * Crée une nouvelle instance de <i>Adresse</i>.
	 *
	 * @param rue rue de la nouvelle adresse
	 * @param ville ville de la nouvelle adresse
	 * @param numTel numéro de téléphone de la nouvelle adresse
	 */
	public Adresse(String rue, String ville, String numTel)
	{
		this.rue = rue;
		this.ville = ville;
		this.numTel = numTel;
	}
	
	/**
	 * Crée une nouvelle instance de <i>Adresse</i>.
	 *
	 * @param adresse l'adresse que l'on veut copier
	 */
	public Adresse(Adresse adresse)
	{
		if(adresse != null)
		{
			this.rue = adresse.rue;
			this.ville = adresse.ville;
			this.numTel = adresse.numTel;
		}
	}

	
//======================================================================//
//====================== Méthodes Implémentées= ========================//
//======================================================================//
	
	@Override
	public String toString()
	{
		return "Telephone: "+this.numTel+"\nAdresse:\n"+this.rue+"\n"+this.ville;
	}
	

//======================================================================//
//============================= Méthodes ===============================//
//======================================================================//
	
	/**
	 * 
	 * @param ville nom de la ville
	 * @return vrai si les deux villes sont égale, sinon faux
	 */
	public boolean compareVilleTo(String ville)
	{
		return (this.ville == ville);
	}
}
