package client;

import ihm.Fenetre;
import ihm.PanelGestionCompte;

public class CompteClient
{

	/**
	 * Cette méthode permet de 
	 * @param args
	 */
	public static void main(String[] args)
	{
		Fenetre fen5 = new Fenetre(new PanelGestionCompte(),"Compte Administrator");
	}

}
