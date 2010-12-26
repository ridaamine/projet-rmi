package client;

import ihm.Fenetre;
import ihm.PanelAdministrateurBanque;
import ihm.PanelBanque;


public class BanqueClient 
{
	public static void main(String args[]) 
	{        
		Fenetre fen3 = new Fenetre(new PanelBanque(),"Banque");
		Fenetre fen4 = new Fenetre(new PanelAdministrateurBanque(),"Banque Administrator");
    }
}
