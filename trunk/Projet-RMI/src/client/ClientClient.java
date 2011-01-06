package client;

import ihm.Fenetre;
import ihm.PanelClient;

/**
 * 
 * CEtte classe permet de lancer un client
 *
 */
public class ClientClient
{
	public static void main(String[] args)
	{
		Fenetre fen1 = new Fenetre(new PanelClient(),"Client");
	}
}
