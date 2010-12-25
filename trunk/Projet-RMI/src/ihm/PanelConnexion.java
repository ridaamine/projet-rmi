package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.Adresse;

import classe.Agence;
import classe.Banque;
import classe.Client;

import service.IBanque;
import service.IClient;

public class PanelConnexion extends JPanel
{
	private JButton creationClientB = new JButton("Créer client");
	private JButton suppressionClientB = new JButton("Supprimer client");
	private JButton rechercheClientB = new JButton("rechercher client");
	private JPanel panClient = new JPanel();

	private JButton creationAgenceB = new JButton("Créer agence");
	private JButton suppressionAgenceB = new JButton("Supprimer agence");
	private JButton rechercheAgenceB = new JButton("Rechercher agence");
	private JPanel panAgence = new JPanel(new BorderLayout());


	private JButton creationCompteB = new JButton("Créer compte");
	private JButton suppressionCompteB = new JButton("Supprimer compte");
	private JButton rechercheCompteB = new JButton("Rechercher compte");
	private JPanel panCompte = new JPanel();


	private JButton creationBanqueB = new JButton("Création banque");
	private JButton suppressionBanqueB = new JButton("Suppression banque");
	private JButton rechercheBanqueB = new JButton("Recherche banque");
	private JPanel panBanque = new JPanel();
	
	private JTextField rueText = new JTextField();
	private JTextField villeText = new JTextField();
	private JTextField telephoneText = new JTextField();
	
	private JTextField nomText = new JTextField();
	private JTextField sexeText = new JTextField();

	
	private JScrollPane scrollBanque = new JScrollPane();
	private JScrollPane scrollAgence = new JScrollPane();
	private JList listeBanque = new JList();
	private JList listeAgence = new JList();
	private JPanel pTemp = new JPanel();
	private JPanel panelCourant = null;

	
	IBanque serveurBanque = null;

	public PanelConnexion()
	{
		this.setLayout(new GridLayout(4,1));
		
		rueText.setColumns(7);
		villeText.setColumns(7);
		telephoneText.setColumns(7);
		nomText.setColumns(7);
		sexeText.setColumns(7);
		
		try 
		{
			serveurBanque = (IBanque) Naming.lookup("Banque");
		} 
		catch (MalformedURLException e) {e.printStackTrace();}
		catch (RemoteException e) {e.printStackTrace();}
		catch (NotBoundException e) {e.printStackTrace();}
		
				
		creationAgenceB.addActionListener(creerAgenceListener());
		suppressionAgenceB.addActionListener(supprimerAgenceListener());
		rechercheAgenceB.addActionListener(rechercherAgenceListener());
		
		creationClientB.addActionListener(creerClientListener());
		suppressionClientB.addActionListener(creerClientListener());
		rechercheClientB.addActionListener(rechercherClientListener());
		
		JPanel pTempA = new JPanel(new FlowLayout());
		pTempA.add(creationClientB);
		pTempA.add(suppressionClientB);
		pTempA.add(rechercheClientB);
		panClient.add(pTempA,BorderLayout.NORTH);


		pTempA = new JPanel(new FlowLayout());
		pTempA.add(creationAgenceB);
		pTempA.add(suppressionAgenceB);
		pTempA.add(rechercheAgenceB);
		panAgence.add(pTempA,BorderLayout.NORTH);

		panCompte.add(creationCompteB);
		panCompte.add(suppressionCompteB);
		panCompte.add(rechercheCompteB);

		panBanque.add(creationBanqueB);
		panBanque.add(suppressionBanqueB);
		panBanque.add(rechercheBanqueB);
		
		this.add(panClient);
		this.add(panAgence);
		this.add(panCompte);
		this.add(panBanque);

	}
	

	public ActionListener creerAgenceListener()
	{
		final PanelConnexion p = this;
		p.revalidate();
		
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				if (panelCourant != null)
				{
					panelCourant.remove(pTemp);
					panelCourant.revalidate();
					panelCourant.repaint();
				}
				
				panelCourant = panAgence;
				
				pTemp = new JPanel(new GridLayout(3,2));
				JPanel pp1 = new JPanel(new FlowLayout());
				JPanel pp2 = new JPanel(new FlowLayout());
				JPanel pp3 = new JPanel(new FlowLayout());
				
				JButton validerB = new JButton("Valider");

				try 
				{
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();
					
					listeBanque = new JList(banques.toArray());
					listeBanque.setVisibleRowCount(1);
					scrollBanque = new JScrollPane(listeBanque);		
				} 
				catch (RemoteException e1) {e1.printStackTrace();}
				
				validerB.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						try 
						{
							serveurBanque.insererAgence(villeText.getText(), new Agence(new Adresse(rueText.getText(),villeText.getText(),telephoneText.getText()),(Banque) listeBanque.getSelectedValue() ));
						} 
						catch (RemoteException e) {	e.printStackTrace();}
					}
				});
				
			
				pp1.add(new JLabel("Rue : "));
				pp1.add(rueText);
				pp2.add(new JLabel("Ville : "));
				pp2.add(villeText);
				pp3.add(new JLabel("Telephone : "));
				pp3.add(telephoneText);
				
				pTemp.add(pp1);
				pTemp.add(pp2);
				pTemp.add(pp3);

				
				pTemp.add(scrollBanque);
				pTemp.add(validerB);
				panAgence.add(pTemp);
				panAgence.revalidate();
				p.validate();
			}
		};
		return a;
	}
	
	public ActionListener supprimerAgenceListener() 
	{
		final PanelConnexion p = this;
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (panelCourant != null)
				{
					panelCourant.remove(pTemp);
					panelCourant.revalidate();
					panelCourant.repaint();
				}
				
				panelCourant = panAgence;

				pTemp = new JPanel(new GridLayout(3,2));
				JPanel pp1 = new JPanel(new FlowLayout());
				
				JLabel villeLab = new JLabel("Ville : ");
				final JTextField villeText = new JTextField();
				villeText.setColumns(7);
				
				JButton validerB = new JButton("Valider");
				validerB.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						try 
						{
							serveurBanque.retirerAgence(villeText.getText(), (Banque)listeBanque.getSelectedValue());
						} 
						catch (RemoteException e1) {e1.printStackTrace();}
					}
				});

				try 
				{
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();
					
					listeBanque = new JList(banques.toArray());
					listeBanque.setVisibleRowCount(1);
					scrollBanque = new JScrollPane(listeBanque);		
				} 
				catch (RemoteException e1) {e1.printStackTrace();}
				
				pp1.add(villeLab);
				pp1.add(villeText);
				
				pTemp.add(pp1);
				pTemp.add(scrollBanque);
				pTemp.add(validerB);
				
				panAgence.add(pTemp);
				panAgence.revalidate();
				p.validate();
			}
			
		};
		return a;
	}
	
	public ActionListener rechercherAgenceListener() 
	{
		final PanelConnexion p = this;
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (panelCourant != null)
				{
					panelCourant.remove(pTemp);
					panelCourant.revalidate();
					panelCourant.repaint();
				}
				
				panelCourant = panAgence;

				pTemp = new JPanel(new GridLayout(3,3));
				JPanel pp1 = new JPanel(new FlowLayout());
				
				JLabel villeLab = new JLabel("Ville : ");
				final JTextField villeText = new JTextField();
				villeText.setColumns(7);
				
				JButton validerB = new JButton("Valider");
				final JLabel trouverLab = new JLabel();
				
				validerB.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						try 
						{
							Agence agence = serveurBanque.rechercherAgence(villeText.getText(), (Banque)listeBanque.getSelectedValue());
							if(agence != null)
							{
								trouverLab.setText("Agence trouver");
							}
							else
								trouverLab.setText("Agence non trouver");
							
							pTemp.validate();
						} 
						catch (RemoteException e1) {e1.printStackTrace();}
					}
				});

				try 
				{
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();
					
					listeBanque = new JList(banques.toArray());
					listeBanque.setVisibleRowCount(1);
					scrollBanque = new JScrollPane(listeBanque);		
				} 
				catch (RemoteException e1) {e1.printStackTrace();}
				
				pp1.add(villeLab);
				pp1.add(villeText);
				
				pTemp.add(pp1);
				pTemp.add(scrollBanque);
				pTemp.add(validerB);
				pTemp.add(trouverLab);
				
				panAgence.add(pTemp);
				panAgence.revalidate();
				p.validate();
			}
		};
		return a;
	}
	
	
	public ActionListener creerClientListener()
	{
		final PanelConnexion p = this;
		
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		

				if (panelCourant != null)
				{
					panelCourant.remove(pTemp);
					panelCourant.revalidate();
					panelCourant.repaint();
				}
				
				panelCourant = panClient;
				
				pTemp = new JPanel(new GridLayout(4,2,10,10));
				JPanel pp1 = new JPanel(new FlowLayout());
				JPanel pp2 = new JPanel(new FlowLayout());
				JPanel pp3 = new JPanel(new FlowLayout());
				JPanel pp4 = new JPanel(new FlowLayout());

				
				JButton validerB = new JButton("Valider");

				try 
				{
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
				p.validate();
			}
		};
		return a;
	}
	
	public ActionListener rechercherClientListener() 
	{
		final PanelConnexion p = this;

		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		

				if (panelCourant != null)
				{
					panelCourant.remove(pTemp);
					panelCourant.revalidate();
					panelCourant.repaint();
				}
				
				panelCourant = panClient;
				
				pTemp = new JPanel(new GridLayout(3,2,10,10));
				JPanel pp1 = new JPanel(new FlowLayout());


				
				JButton validerB = new JButton("Valider");

				try 
				{
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
							serveurClient.rechercheClient(nomText.getText(), agence);
							
							if(agence != null)
							{
								trouverLab.setText("Agence trouver");
							}
							else
								trouverLab.setText("Agence non trouver");
							
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
				p.validate();
			}
		};
		return a;
	}
	
}
