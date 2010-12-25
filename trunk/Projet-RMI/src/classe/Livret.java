package classe;

import java.io.Serializable;

public class Livret extends Compte implements Serializable 
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//

	private double taux; //taux d'interet du livret

	public Livret(int montant, double taux, Client proprietaire)
	{
		super(montant,proprietaire);
		this.taux= taux;

	}
	
	public String toString()
	{
		String desc ="";
		/*desc +="\n Numero de compte : "+numero;
		desc +="\n Montant : "+montant;
		desc +="\n Proprietaire: "+proprietaire;*/
		desc+="Livret numero : "+this.getNumero()+" Taux : "+taux;
		return desc;
	}
	
}