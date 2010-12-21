package client;

import java.rmi.Naming;

import service.IBanque;

public class BanqueClient 
{
	public static void main(String args[]) 
	{        
        try 
        {
            IBanque iBanque = (IBanque) Naming.lookup("Banque");
        } 
        catch (Exception e) { e.printStackTrace(); }
    }
}
