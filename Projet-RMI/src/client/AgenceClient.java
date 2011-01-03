package client;

import ihm.Fenetre;
import ihm.PanelAgence;

/**
 * 
 * Cette classe permet de lancer un client d'agence
 *
 */
public class AgenceClient
{
	public static void main(String args[]) 
	{        
		Fenetre fen2 = new Fenetre(new PanelAgence(),"Agence");
	}
}
