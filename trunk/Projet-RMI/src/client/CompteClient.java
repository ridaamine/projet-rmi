package client;

import ihm.Fenetre;
import ihm.PanelGestionCompte;

/**
 * 
 * Cette classe permet de lancer un client de compte
 *
 */
public class CompteClient
{
	public static void main(String[] args)
	{
		Fenetre fen5 = new Fenetre(new PanelGestionCompte(),"Compte Administrator");
	}

}
