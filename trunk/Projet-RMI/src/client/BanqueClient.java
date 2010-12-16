package client;

import implementation.AgenceImpl;

import java.rmi.Naming;

import main.Adresse;

import service.Banque;

public class BanqueClient 
{
	public static void main(String args[]) 
	{        
        try 
        {
            Banque banque = (Banque) Naming.lookup("Banque");
           
            banque.insererAgence("Montpellier", new AgenceImpl(new Adresse("Rond-point de la Lyre 202\nAvenue des Moulins", "Montpellier", "0 892 892 000"), banque));
            banque.insererAgence("Aubenas", new AgenceImpl(new Adresse("Le Moulin\nRoute Montelimar", "Aubenas", "0 810 04 08 83‎"), banque));
            banque.retirerAgence("Montpellier");
            
            System.out.println(banque.description());
        } 
        catch (Exception e) { e.printStackTrace(); }
    }
}
