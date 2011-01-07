package ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.ThreadVirement;

import service.IBanque;
import service.IClient;
import service.ICompte;

import classe.Agence;
import classe.Banque;
import classe.Client;
import classe.Compte;

/**
 * Cette classe permet de represent� l'interface graphique d'un client
 *
 */
public class PanelClient extends JPanel
{
	private JList listeBanque = null;
	private JScrollPane scrollBanque = null;
	private JList listeAgence=null;
	private JScrollPane scrollAgence = null;
	private JTextField nomText = new JTextField();
	private JPanel pan = new JPanel();
	
	public PanelClient()
	{
		super();
		init();
	}
	
	public void init()
	{
		JLabel nomLab = new JLabel("Nom : ");
		nomText.setColumns(7);
		
		final JButton validerB = new JButton("Valider");

		
		try 
		{
			final IBanque serveurBanque = (IBanque) Naming.lookup("//169.254.134.165/Banque");			
			ArrayList<Banque> banques = serveurBanque.listeBanques();
			
			listeBanque = new JList(banques.toArray());
			listeBanque.setVisibleRowCount(1);
			scrollBanque = new JScrollPane(listeBanque);
			
			listeBanque.addListSelectionListener(new ListSelectionListener()
			{
				@Override
				public void valueChanged(ListSelectionEvent e)
				{
					if(e.getValueIsAdjusting())
					{
						if(scrollAgence != null)
							pan.remove(scrollAgence);
			        	try 
			        	{
							HashMap<String, Agence> agences = serveurBanque.listeAgences((Banque) listeBanque.getSelectedValue());
							if(agences != null)		
							{
								listeAgence = new JList(agences.values().toArray()); 
								listeAgence.setVisibleRowCount(1);
								scrollAgence = new JScrollPane(listeAgence);
								pan.add(scrollAgence);
								revalidate();
							};
			        	} 
			        	catch (RemoteException e1)	{e1.printStackTrace();}
					}
				}
				
			});
						
			final JLabel trouveLab = new JLabel();
			validerB.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					pan.remove(trouveLab);
					try 
					{
						IClient serveurClient = (IClient)Naming.lookup(((Agence)listeAgence.getSelectedValue()).getAdresseServeurClient());
						Client client = serveurClient.rechercheClient(nomText.getText(), (Agence)listeAgence.getSelectedValue());
						if(client != null)
						{
							remove(validerB);
							revalidate();
							gestionCompte(client);
						}
						else
						{
							trouveLab.setText("Client non trouver");
							pan.add(trouveLab);
							pan.validate();
						}
					} 
					catch (MalformedURLException e) {e.printStackTrace();}
					catch (RemoteException e) {	e.printStackTrace();} 
					catch (NotBoundException e) {e.printStackTrace();}
				}
			});
			
			
		} 
		catch (RemoteException e1) {e1.printStackTrace();}
		catch (MalformedURLException e) {e.printStackTrace();} 
		catch (NotBoundException e) {e.printStackTrace();}
		
		pan.add(nomLab);
		pan.add(nomText);
		pan.add(scrollBanque);
		
		this.add(pan);
		add(validerB);
	}
	
	public void gestionCompte(final Client client)
	{
		this.remove(pan);
		pan = new JPanel(new BorderLayout());
		JPanel pt1 = new JPanel(new BorderLayout());
		JPanel pt2 = new JPanel(new BorderLayout());
		JPanel pt3 = new JPanel();
		JPanel pt4 = new JPanel();
		JPanel pt5 = new JPanel(new BorderLayout());
		JPanel pt6 = new JPanel(new BorderLayout());
		JPanel pt7 = new JPanel(new BorderLayout());
		JPanel pt8 = new JPanel(new BorderLayout());

		
		JLabel bonjourLab = new JLabel("Bonjour M. "+client.getNom());
		JLabel etatCompteLab = new JLabel("Etat de vos comptes : ");
		
		pt1.add(bonjourLab,BorderLayout.NORTH);
		pt1.add(etatCompteLab,BorderLayout.CENTER);
		pan.add(pt1,BorderLayout.NORTH);
		try 
		{
			
			ICompte serveurCompte = (ICompte) Naming.lookup(client.getAdresseServeurCompte());
			ArrayList<Compte> comptesClient = serveurCompte.rechercheToutCompte(client);
			pt2 = new JPanel(new GridLayout(comptesClient.size(),2));
			for(int i=0; i < comptesClient.size(); i ++)
			{
				JLabel labCompte = new JLabel("Compte numéro : "+comptesClient.get(i).getNumero()+" Montant : "+comptesClient.get(i).getMontant());
				pt2.add(labCompte);
			}
		} 
		catch (MalformedURLException e) {e.printStackTrace();} 
		catch (RemoteException e) {	e.printStackTrace();} 
		catch (NotBoundException e) {e.printStackTrace();}
		



		try 
		{
			final ICompte serveurCompte = (ICompte) Naming.lookup(client.getAdresseServeurCompte());
			ArrayList<Compte> comptesClient = serveurCompte.rechercheToutCompte(client);
			final JList listeComptes1 = new JList(comptesClient.toArray());
			listeComptes1.setVisibleRowCount(1);
			final JList listeComptes2 = new JList(comptesClient.toArray());
			listeComptes2.setVisibleRowCount(1);

			JScrollPane scrollCompte1 = new JScrollPane(listeComptes1);
			JScrollPane scrollCompte2 = new JScrollPane(listeComptes2);
			pt3.add(new JLabel("Virement de : "));
			final JTextField montantText = new JTextField();
			montantText.setColumns(7);
			pt3.add(montantText);
			pt4.add(new JLabel(" du compte : "));
			pt4.add(scrollCompte1);
			pt4.add(new JLabel(" au compte "));
			pt4.add(scrollCompte2);
			JButton validerB = new JButton("Valider");
			pt6.add(validerB);
			pt5.add(pt4,BorderLayout.NORTH);
			pt5.add(pt6,BorderLayout.CENTER);
			pt7.add(pt3,BorderLayout.NORTH);
			pt7.add(pt5,BorderLayout.CENTER);
			pt8.add(pt2,BorderLayout.NORTH);
			pt8.add(pt7,BorderLayout.CENTER);
			
			
			JPanel pt9 = new JPanel();
			JButton genereVirementB = new JButton("Generer des virements al�atoires");
			final JTextField nbGenereVirementText = new JTextField();
			nbGenereVirementText.setColumns(7);
			
			genereVirementB.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{ 
					if(nbGenereVirementText.getText() != null &&  !nbGenereVirementText.getText().equals(""))
					for (int i=0; i < Integer.valueOf(nbGenereVirementText.getText()); i++)
					{
						try 
						{
							serveurCompte.genereVirementAleatoire(client);
							gestionCompte(client);
						} 
						catch (RemoteException e1) {e1.printStackTrace();}
					}
				}
			});
			
			pt9.add(genereVirementB);
			pt9.add(nbGenereVirementText);
			pan.add(pt9,BorderLayout.SOUTH);
			
			
			
			
			validerB.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						if(montantText.getText() != null && !montantText.getText().equals(""))
						new ThreadVirement(client,(Compte)listeComptes1.getSelectedValue(), (Compte)listeComptes2.getSelectedValue(), Integer.valueOf(montantText.getText()));
						gestionCompte(client);
					} 
					catch (NumberFormatException e1) {e1.printStackTrace();}
				}
			});
			
		}
		catch (MalformedURLException e1) {e1.printStackTrace();} 
		catch (RemoteException e1) {e1.printStackTrace();}
		catch (NotBoundException e1) {e1.printStackTrace();}
		

		
		pan.add(pt1,BorderLayout.NORTH);
		pan.add(pt8,BorderLayout.CENTER);
		this.add(pan);
		this.revalidate();
		this.repaint();
		
		
	}

}
