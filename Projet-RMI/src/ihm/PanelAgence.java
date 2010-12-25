package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.Adresse;
import service.IBanque;
import service.IClient;
import classe.Agence;
import classe.Banque;
import classe.Client;

public class PanelAgence extends JPanel
{
	private JTextField rueText = new JTextField();
	private JTextField villeText = new JTextField();
	private JTextField telephoneText = new JTextField();
	
	private JTextField nomText = new JTextField();
	private JTextField sexeText = new JTextField();

	
	private JScrollPane scrollBanque = new JScrollPane();
	private JScrollPane scrollAgence = new JScrollPane();
	private JList listeBanque = new JList();
	private JList listeAgence = new JList();
	
	private JButton creationClientB = new JButton("Créer client");
	private JButton suppressionClientB = new JButton("Supprimer client");
	private JButton rechercheClientB = new JButton("rechercher client");
	private JPanel panClient = new JPanel(new BorderLayout());
	private JPanel pTemp = new JPanel();
	
	public PanelAgence()
	{
		super();
		init();
	}

	public void init() 
	{
		rueText.setColumns(7);
		villeText.setColumns(7);
		telephoneText.setColumns(7);
		nomText.setColumns(7);
		sexeText.setColumns(7);
		
		JPanel pt = new JPanel();
		pt.add(creationClientB);
		pt.add(suppressionClientB);
		pt.add(rechercheClientB);
		panClient.add(pt,BorderLayout.NORTH);
		
		
		creationClientB.addActionListener(creerClientListener());
		suppressionClientB.addActionListener(supprimerClientListener());
		rechercheClientB.addActionListener(rechercherClientListener());
		
		this.add(panClient);
		
	}
	
	public ActionListener creerClientListener()
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				panClient.remove(pTemp);
				
				pTemp = new JPanel(new GridLayout(4,2,10,10));
				JPanel pp1 = new JPanel(new FlowLayout());
				JPanel pp2 = new JPanel(new FlowLayout());
				JPanel pp3 = new JPanel(new FlowLayout());
				JPanel pp4 = new JPanel(new FlowLayout());

				
				JButton validerB = new JButton("Valider");

				try 
				{
					final IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();

					listeAgence.setVisibleRowCount(1);
					scrollAgence = new JScrollPane(listeAgence);
					
					listeBanque = new JList(banques.toArray());
					listeBanque.setVisibleRowCount(1);
					scrollBanque = new JScrollPane(listeBanque);
					listeBanque.addListSelectionListener(new ListSelectionListener() 
					{
						@Override
						public void valueChanged(ListSelectionEvent e) 
						{
					        if (e.getValueIsAdjusting())
					        {
					        	pTemp.remove(scrollAgence);
					        	try 
					        	{
									HashMap<String, Agence> agences = serveurBanque.listeAgences((Banque) listeBanque.getSelectedValue());
									if(agences != null)										
										listeAgence = new JList(agences.values().toArray()); 
										listeAgence.setVisibleRowCount(1);
										scrollAgence = new JScrollPane(listeAgence);
										pTemp.add(scrollAgence);
										pTemp.revalidate();
					        	} 
					        	catch (RemoteException e1)	{e1.printStackTrace();}
					        }
					    }
					});
					
				} 
				catch (RemoteException e1) {e1.printStackTrace();}
				catch (MalformedURLException e3) {e3.printStackTrace();}
				catch (NotBoundException e2) {e2.printStackTrace();}
				
				validerB.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						Agence agence = (Agence) listeAgence.getSelectedValue();
						try 
						{
							IClient serveurClient = (IClient) Naming.lookup(agence.getAdresseServeurClient());
							serveurClient.creerClient(new Client(nomText.getText(),sexeText.getText(),new Adresse(rueText.getText(),villeText.getText(),telephoneText.getText()),(Agence) listeAgence.getSelectedValue()));
						} 
						catch (MalformedURLException e) {e.printStackTrace();} 
						catch (RemoteException e) {	e.printStackTrace();}
						catch (NotBoundException e) {e.printStackTrace();}
					}
				});
				
				pp1.add(new JLabel("Nom : "));
				pp1.add(nomText);
				pp1.add(new JLabel("Sexe : "));
				pp1.add(sexeText);
				pp2.add(new JLabel("Rue : "));
				pp2.add(rueText);
				pp3.add(new JLabel("Ville : "));
				pp3.add(villeText);
				pp4.add(new JLabel("Telephone : "));
				pp4.add(telephoneText);
				
				pTemp.add(pp1);
				pTemp.add(pp2);
				pTemp.add(pp3);
				pTemp.add(pp4);

				
				pTemp.add(scrollBanque);
				pTemp.add(validerB);
				panClient.add(pTemp,BorderLayout.SOUTH);
				panClient.revalidate();
			}
		};
		return a;
	}
	
	public ActionListener rechercherClientListener() 
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				panClient.remove(pTemp);
				pTemp = new JPanel(new GridLayout(3,2,10,10));
				JPanel pp1 = new JPanel(new FlowLayout());


				
				JButton validerB = new JButton("Valider");

				try 
				{
					final IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();

					listeAgence.setVisibleRowCount(1);
					scrollAgence = new JScrollPane(listeAgence);
					
					listeBanque = new JList(banques.toArray());
					listeBanque.setVisibleRowCount(1);
					scrollBanque = new JScrollPane(listeBanque);
					listeBanque.addListSelectionListener(new ListSelectionListener() 
					{
						@Override
						public void valueChanged(ListSelectionEvent e) 
						{
					        if (e.getValueIsAdjusting())
					        {
					        	pTemp.remove(scrollAgence);
					        	try 
					        	{
									HashMap<String, Agence> agences = serveurBanque.listeAgences((Banque) listeBanque.getSelectedValue());
									if(agences != null)										
										listeAgence = new JList(agences.values().toArray()); 
										listeAgence.setVisibleRowCount(1);
										scrollAgence = new JScrollPane(listeAgence);
										pTemp.add(scrollAgence);
										pTemp.revalidate();
					        	} 
					        	catch (RemoteException e1)	{e1.printStackTrace();}
					        }
					    }
					});
					
				} 
				catch (MalformedURLException e2) {e2.printStackTrace();} 
				catch (RemoteException e2) {	e2.printStackTrace();}
				catch (NotBoundException e2) {e2.printStackTrace();}
				
				final JLabel trouverLab = new JLabel();
				
				validerB.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						Agence agence = (Agence) listeAgence.getSelectedValue();
						try 
						{
							IClient serveurClient = (IClient) Naming.lookup(agence.getAdresseServeurClient());
							Client client = serveurClient.rechercheClient(nomText.getText(), agence);
							
							if(client != null)
							{
								trouverLab.setText("Client trouver");
							}
							else
								trouverLab.setText("Client non trouver");
							
							pTemp.validate();
						} 
						catch (MalformedURLException e) {e.printStackTrace();} 
						catch (RemoteException e) {	e.printStackTrace();}
						catch (NotBoundException e) {e.printStackTrace();}
					}
				});
				
				pp1.add(new JLabel("Nom : "));
				pp1.add(nomText);
				
				pTemp.add(pp1);
				
				pTemp.add(scrollBanque);
				pTemp.add(validerB);
				pTemp.add(trouverLab);
				panClient.add(pTemp,BorderLayout.SOUTH);
				panClient.revalidate();
			}
		};
		return a;
	}
	
	public ActionListener supprimerClientListener() 
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				panClient.remove(pTemp);
				pTemp = new JPanel(new GridLayout(3,2,10,10));
				JPanel pp1 = new JPanel(new FlowLayout());


				
				JButton validerB = new JButton("Valider");

				try 
				{
					final IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();

					listeAgence.setVisibleRowCount(1);
					scrollAgence = new JScrollPane(listeAgence);
					
					listeBanque = new JList(banques.toArray());
					listeBanque.setVisibleRowCount(1);
					scrollBanque = new JScrollPane(listeBanque);
					listeBanque.addListSelectionListener(new ListSelectionListener() 
					{
						@Override
						public void valueChanged(ListSelectionEvent e) 
						{
					        if (e.getValueIsAdjusting())
					        {
					        	pTemp.remove(scrollAgence);
					        	try 
					        	{
									HashMap<String, Agence> agences = serveurBanque.listeAgences((Banque) listeBanque.getSelectedValue());
									if(agences != null)										
										listeAgence = new JList(agences.values().toArray()); 
										listeAgence.setVisibleRowCount(1);
										scrollAgence = new JScrollPane(listeAgence);
										pTemp.add(scrollAgence);
										pTemp.revalidate();
					        	} 
					        	catch (RemoteException e1)	{e1.printStackTrace();}
					        }
					    }
					});
					
				} 
				catch (MalformedURLException e2) {e2.printStackTrace();} 
				catch (RemoteException e2) {	e2.printStackTrace();}
				catch (NotBoundException e2) {e2.printStackTrace();}
				
				final JLabel trouverLab = new JLabel();
				
				validerB.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						Agence agence = (Agence) listeAgence.getSelectedValue();
						try 
						{
							IClient serveurClient = (IClient) Naming.lookup(agence.getAdresseServeurClient());
							Client client = serveurClient.rechercheClient(nomText.getText(), agence);
							
							if(client != null)
							{
								serveurClient.detruireClient(client.getNumero());
								trouverLab.setText("Client supprimer");
							}
							else
								trouverLab.setText("Client non trouver");
							
							pTemp.validate();
						} 
						catch (MalformedURLException e) {e.printStackTrace();} 
						catch (RemoteException e) {	e.printStackTrace();}
						catch (NotBoundException e) {e.printStackTrace();}
					}
				});
				
				pp1.add(new JLabel("Nom : "));
				pp1.add(nomText);
				
				pTemp.add(pp1);
				
				pTemp.add(scrollBanque);
				pTemp.add(validerB);
				pTemp.add(trouverLab);
				panClient.add(pTemp,BorderLayout.SOUTH);
				panClient.revalidate();
			}
		};
		return a;
	}
	
}
