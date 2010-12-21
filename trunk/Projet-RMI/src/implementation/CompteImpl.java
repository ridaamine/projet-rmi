package implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.IAgence;
import service.IClient;
import service.ICompte;

public class CompteImpl extends UnicastRemoteObject implements ICompte
{
//======================================================================//
//============================ Variables ===============================//
//======================================================================//
	
	//TODO A compléter...
	

//======================================================================//
//========================== Constructeurs =============================//
//======================================================================//
	
	public CompteImpl() throws RemoteException
	{
		super();
		//TODO A compléter...
	}


//======================================================================//
//====================== Méthodes Implémentées= ========================//
//======================================================================//
	
	@Override
	public void credit(int montant) throws RemoteException 
	{
		//TODO A compléter...
	}


	@Override
	public void debit(int montant) throws RemoteException 
	{
		//TODO A compléter...
	}
}
