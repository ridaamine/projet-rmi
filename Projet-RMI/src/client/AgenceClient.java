package client;

import java.rmi.Naming;

import service.Agence;

public class AgenceClient 
{
	public static void main(String args[]) 
	{        
        try 
        {
            Agence agence = (Agence) Naming.lookup("Agence");
        } 
        catch (Exception e) { e.printStackTrace(); }
    }
}
