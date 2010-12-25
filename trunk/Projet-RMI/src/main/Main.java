package main;

import ihm.Fenetre;
import ihm.FenetreClient;
import ihm.FenetreConnexion;
import ihm.PanelAdministrateurBanque;
import ihm.PanelAgence;
import ihm.PanelBanque;
import ihm.PanelClient;
import ihm.PanelGestionCompte;

public class Main 
{
	public static void main(String args[])
	{
		Fenetre fen1 = new Fenetre(new PanelClient(),"Client");
		Fenetre fen2 = new Fenetre(new PanelAgence(),"Agence");
		Fenetre fen3 = new Fenetre(new PanelBanque(),"Banque");
		Fenetre fen4 = new Fenetre(new PanelAdministrateurBanque(),"Banque Administrator");
		Fenetre fen5 = new Fenetre(new PanelGestionCompte(),"Compte Administrator");

	}
}
