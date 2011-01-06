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
import service.ICompte;
import classe.Agence;
import classe.Banque;
import classe.Client;
import classe.Compte;

/**
 * Cette classe permet de represent� l'interface graphique du gestionnaire de compte
 *
 */
public class PanelGestionCompte extends JPanel
{
	
	private JScrollPane scrollBanque = new JScrollPane();
	private JScrollPane scrollAgence = new JScrollPane();
	private JScrollPane scrollClient = new JScrollPane();
	private JScrollPane scrollCompte = new JScrollPane();
	private JScrollPane scrollLivret = new JScrollPane();

	private JList listeBanque = new JList();
	private JList listeAgence = new JList();
	private JList listeClient = new JList();
	private JList listeCompte = new JList();
	private JList listeLivret = new JList();


	
	private JButton creationCompteB = new JButton("Créer compte");
	private JButton suppressionCompteB = new JButton("Supprimer compte");
	private JPanel panClientC = new JPanel(new BorderLayout());
	private JPanel pTempC = new JPanel();
	
	private JButton creationLivretB = new JButton("Créer Livret");
	private JButton suppressionLivretB = new JButton("Supprimer Livret");
	private JPanel panClientL = new JPanel(new BorderLayout());
	private JPanel pTempL = new JPanel();
	
	private JButton creerCompteB = new JButton("creer Compte");
	private JButton supprimerCompteB = new JButton("supprimer Compte");
	
	private JButton creerLivretB = new JButton("creer Livret");
	private JButton supprimerLivretB = new JButton("supprimer Livret");
	private JTextField tauxText = new JTextField();
	private JLabel tauxLab = new JLabel("Taux du livret : ");
	
	private JTextField montantText = new JTextField();
	private JLabel montantLab = new JLabel("Montant : ");
	
	private JButton debiterB = new JButton("Debiter compte");
	private JButton crediterB = new JButton("Crediter compte");
	private JButton debitB = new JButton("Debit");
	private JButton creditB = new JButton("Credit");
	private JPanel panCredit = new JPanel();
	private JPanel panDebit = new JPanel();
	private JTextField debitText = new JTextField();
	private JTextField creditText = new JTextField();
	private JPanel panClientT = new JPanel(new BorderLayout());
	JPanel pTempT = new JPanel(new BorderLayout());

	
	public PanelGestionCompte()
	{
		super();
		init();
	}

	public void init() 
	{
		this.setLayout(new BorderLayout());
		JPanel pt = new JPanel();
		pt.add(creationCompteB);
		pt.add(suppressionCompteB);
		panClientC.add(pt,BorderLayout.NORTH);
		
		
		creationCompteB.addActionListener(creerCompteListener());
		suppressionCompteB.addActionListener(supprimerCompteListener());
		
		this.add(panClientC,BorderLayout.NORTH);
		
		
		
		pt = new JPanel();
		pt.add(creationLivretB);
		pt.add(suppressionLivretB);
		panClientL.add(pt,BorderLayout.NORTH);
		
		
		creationLivretB.addActionListener(creerLivretListener());
		suppressionLivretB.addActionListener(supprimerLivretListener());
		
		tauxText.setColumns(7);
		montantText.setColumns(7);
		
		
		pt = new JPanel();
		pt.add(debiterB);
		pt.add(crediterB);
		panClientT.add(pt,BorderLayout.NORTH);
		
		debitText.setColumns(7);
		creditText.setColumns(7);
		
		debiterB.addActionListener(debiterListener());
		crediterB.addActionListener(crediterListener());
		
		pt = new JPanel(new BorderLayout());
		pt.add(panClientL,BorderLayout.NORTH);
		pt.add(panClientT,BorderLayout.CENTER);
		
		panCredit.add(creditB);
		panCredit.add(creditText);
		panDebit.add(debitB);
		panDebit.add(debitText);		
		
		this.add(pt,BorderLayout.CENTER);
	}
	
	

	public ActionListener creerCompteListener()
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				panClientL.remove(pTempL);
				panClientC.remove(pTempC);
				panClientT.remove(pTempT);
				
				pTempC = new JPanel(new GridLayout(4,2,10,10));

				try 
				{
		        	final JPanel pt = new JPanel();
					
					final IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();

					scrollAgence = new JScrollPane(listeAgence);
					
					listeBanque = new JList(banques.toArray());
					scrollBanque = new JScrollPane(listeBanque);
					listeBanque.addListSelectionListener(new ListSelectionListener() 
					{
						@Override
						public void valueChanged(ListSelectionEvent e) 
						{
					        if (e.getValueIsAdjusting())
					        {
					        	pTempC.remove(scrollAgence);
					        	pTempC.remove(scrollClient);
								pTempC.remove(creerCompteB);
								pTempC.remove(pt);
					        	try 
					        	{
									HashMap<String, Agence> agences = serveurBanque.listeAgences((Banque) listeBanque.getSelectedValue());
									if(agences != null)										
										listeAgence = new JList(agences.values().toArray()); 
										
										listeAgence.addListSelectionListener(new ListSelectionListener() 
										{
											@Override
											public void valueChanged(ListSelectionEvent e) 
											{
										        if (e.getValueIsAdjusting())
										        {
										        	pTempC.remove(scrollClient);
													pTempC.remove(creerCompteB);
													pTempC.remove(pt);
										        	try 
										        	{
														IClient serveurClient = (IClient) Naming.lookup(((Agence)listeAgence.getSelectedValue()).getAdresseServeurClient());
														ArrayList<Client> clients = serveurClient.listeClients((Agence) listeAgence.getSelectedValue());
														if(clients != null)										
															listeClient = new JList(clients.toArray()); 
															listeClient.addListSelectionListener(new ListSelectionListener() 
															{
																@Override
																public void valueChanged(ListSelectionEvent e) 
																{
															        if (e.getValueIsAdjusting())
															        {
															        	pTempC.remove(creerCompteB);
															        	creerCompteB = new JButton("creer Compte");
															        	creerCompteB.addActionListener(new ActionListener() 
															        	{
																			@Override
																			public void actionPerformed(ActionEvent e) 
																			{
																				try 
																				{
																					ICompte serveurCompte = (ICompte) Naming.lookup(((Client)listeClient.getSelectedValue()).getAdresseServeurCompte());
																					serveurCompte.creerCompte(Integer.valueOf(montantText.getText()), (Client)listeClient.getSelectedValue());
																				}
																				catch (MalformedURLException e1) {e1.printStackTrace();}
																				catch (RemoteException e1) {e1.printStackTrace();} 
																				catch (NotBoundException e1) {e1.printStackTrace();}
																			}
																		});
															        	pt.add(montantLab);
															        	pt.add(montantText);
															        	pTempC.add(pt);
															        	pTempC.add(creerCompteB);
															        	
															        	pTempC.revalidate();
															        }
															    }
															});
															
															scrollClient = new JScrollPane(listeClient);
															pTempC.add(scrollClient);
															pTempC.revalidate();
										        	} 
										        	catch (RemoteException e1)	{e1.printStackTrace();} 
										        	catch (MalformedURLException e1) {e1.printStackTrace();} 
										        	catch (NotBoundException e1) {e1.printStackTrace();}
										        }
										    }
										});
										
										scrollAgence = new JScrollPane(listeAgence);
										pTempC.add(scrollAgence);
										pTempC.revalidate();
					        	} 
					        	catch (RemoteException e1)	{e1.printStackTrace();}
					        }
					    }
					});
					
				} 
				catch (RemoteException e1) {e1.printStackTrace();}
				catch (MalformedURLException e3) {e3.printStackTrace();}
				catch (NotBoundException e2) {e2.printStackTrace();}

				pTempC.add(scrollBanque);
				panClientC.add(pTempC,BorderLayout.SOUTH);
				panClientC.revalidate();
			}
		};
		return a;
	}
	
	
	public ActionListener supprimerCompteListener() 
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				panClientL.remove(pTempL);
				panClientC.remove(pTempC);
				panClientT.remove(pTempT);
				
				pTempC = new JPanel(new GridLayout(4,2,10,10));

				try 
				{
					final IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();

//					listeAgence.setVisibleRowCount(1);
					scrollAgence = new JScrollPane(listeAgence);
					
					listeBanque = new JList(banques.toArray());
//					listeBanque.setVisibleRowCount(1);
					scrollBanque = new JScrollPane(listeBanque);
					listeBanque.addListSelectionListener(new ListSelectionListener() 
					{
						@Override
						public void valueChanged(ListSelectionEvent e) 
						{
					        if (e.getValueIsAdjusting())
					        {
					        	pTempC.remove(scrollAgence);
					        	pTempC.remove(scrollClient);
					        	pTempC.remove(scrollCompte);
					        	pTempC.remove(supprimerCompteB);
					        	try 
					        	{
									HashMap<String, Agence> agences = serveurBanque.listeAgences((Banque) listeBanque.getSelectedValue());
									if(agences != null)										
										listeAgence = new JList(agences.values().toArray()); 
//										listeAgence.setVisibleRowCount(1);
										
										listeAgence.addListSelectionListener(new ListSelectionListener() 
										{
											@Override
											public void valueChanged(ListSelectionEvent e) 
											{
										        if (e.getValueIsAdjusting())
										        {
										        	pTempC.remove(scrollClient);
										        	pTempC.remove(scrollCompte);
										        	pTempC.remove(supprimerCompteB);
										        	try 
										        	{
														IClient serveurClient = (IClient) Naming.lookup(((Agence)listeAgence.getSelectedValue()).getAdresseServeurClient());
														ArrayList<Client> clients = serveurClient.listeClients((Agence) listeAgence.getSelectedValue());
														if(clients != null)										
															listeClient = new JList(clients.toArray()); 
//															listeClient.setVisibleRowCount(1);
															
															listeClient.addListSelectionListener(new ListSelectionListener() 
															{
																@Override
																public void valueChanged(ListSelectionEvent e) 
																{
															        if (e.getValueIsAdjusting())
															        {
															        	pTempC.remove(scrollCompte);
															        	pTempC.remove(supprimerCompteB);
															        	try 
															        	{
																			final ICompte serveurCompte = (ICompte) Naming.lookup(((Client)listeClient.getSelectedValue()).getAdresseServeurCompte());
																			ArrayList<Compte> comptes = serveurCompte.rechercheCompte((Client) listeClient.getSelectedValue());
																			if(comptes != null)										
																				listeCompte = new JList(comptes.toArray()); 
																				
																        	pTempC.remove(supprimerCompteB);
																			supprimerCompteB = new JButton("supprimer Compte");

																				listeCompte.addListSelectionListener(new ListSelectionListener() 
																				{
																					@Override
																					public void valueChanged(ListSelectionEvent e)
																					{
																						if(e.getValueIsAdjusting())
																						{
																							pTempC.add(supprimerCompteB);
																							pTempC.revalidate();
																						}
																					}
																				});

																				
																				scrollCompte = new JScrollPane(listeCompte);
																				supprimerCompteB.addActionListener(new ActionListener() 
																				{
																					@Override
																					public void actionPerformed(ActionEvent e) 
																					{
																						try 
																						{
																							serveurCompte.detruireCompte(((Compte)listeCompte.getSelectedValue()).getNumero());
																						}
																						catch (RemoteException e1) {e1.printStackTrace();}
																					}
																				});
																				
																				pTempC.add(scrollCompte);
																				pTempC.revalidate();
															        	} 
															        	catch (RemoteException e1)	{e1.printStackTrace();} 
															        	catch (MalformedURLException e1) {e1.printStackTrace();} 
															        	catch (NotBoundException e1) {e1.printStackTrace();}
															        }
															    }
															});
															
															scrollClient = new JScrollPane(listeClient);
															pTempC.add(scrollClient);
															pTempC.revalidate();
										        	} 
										        	catch (RemoteException e1)	{e1.printStackTrace();} 
										        	catch (MalformedURLException e1) {e1.printStackTrace();} 
										        	catch (NotBoundException e1) {e1.printStackTrace();}
										        }
										    }
										});
										
										scrollAgence = new JScrollPane(listeAgence);
										pTempC.add(scrollAgence);
										pTempC.revalidate();
					        	} 
					        	catch (RemoteException e1)	{e1.printStackTrace();}
					        }
					    }
					});
					
				} 
				catch (RemoteException e1) {e1.printStackTrace();}
				catch (MalformedURLException e3) {e3.printStackTrace();}
				catch (NotBoundException e2) {e2.printStackTrace();}

				pTempC.add(scrollBanque);
				panClientC.add(pTempC,BorderLayout.SOUTH);
				panClientC.revalidate();
			}
		};
		return a;
	}
	
	
	public ActionListener creerLivretListener()
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				panClientL.remove(pTempL);
				panClientC.remove(pTempC);
				panClientT.remove(pTempT);
				
				pTempL = new JPanel(new GridLayout(4,2,10,10));

				try 
				{
					final IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();

					scrollAgence = new JScrollPane(listeAgence);
					
					listeBanque = new JList(banques.toArray());
					scrollBanque = new JScrollPane(listeBanque);
					
					final JPanel pt = new JPanel(new BorderLayout());
					JPanel pt1 = new JPanel();
					pt1.add(tauxLab);
					pt1.add(tauxText);
					pt.add(pt1,BorderLayout.NORTH);
		        	JPanel pt2 = new JPanel();
		        	pt2.add(montantLab);
		        	pt2.add(montantText);
		        	pt.add(pt2,BorderLayout.CENTER);
					listeBanque.addListSelectionListener(new ListSelectionListener() 
					{
						@Override
						public void valueChanged(ListSelectionEvent e) 
						{
					        if (e.getValueIsAdjusting())
					        {
					        	pTempL.remove(scrollAgence);
					        	pTempL.remove(scrollClient);
								pTempL.remove(creerLivretB);
								pTempL.remove(pt);
					        	try 
					        	{
									HashMap<String, Agence> agences = serveurBanque.listeAgences((Banque) listeBanque.getSelectedValue());
									if(agences != null)										
										listeAgence = new JList(agences.values().toArray()); 
//										listeAgence.setVisibleRowCount(1);
										listeAgence.addListSelectionListener(new ListSelectionListener() 
										{
					
											@Override
											public void valueChanged(ListSelectionEvent e) 
											{
										        if (e.getValueIsAdjusting())
										        {
										        	pTempL.remove(scrollClient);
													pTempL.remove(creerLivretB);
													pTempL.remove(pt);
										        	try 
										        	{
														IClient serveurClient = (IClient) Naming.lookup(((Agence)listeAgence.getSelectedValue()).getAdresseServeurClient());
														ArrayList<Client> clients = serveurClient.listeClients((Agence) listeAgence.getSelectedValue());
														if(clients != null)										
															listeClient = new JList(clients.toArray()); 
														
															listeClient.addListSelectionListener(new ListSelectionListener() 
															{
																@Override
																public void valueChanged(ListSelectionEvent e) 
																{
															        if (e.getValueIsAdjusting())
															        {
																		pTempL.remove(creerLivretB);
															        	creerLivretB = new JButton("creer Livret");
															        	creerLivretB.addActionListener(new ActionListener() 
															        	{
																			@Override
																			public void actionPerformed(ActionEvent e) 
																			{
																				try 
																				{
																					ICompte serveurCompte = (ICompte) Naming.lookup(((Client)listeClient.getSelectedValue()).getAdresseServeurCompte());
																					serveurCompte.creerLivret(Integer.valueOf(montantText.getText()), Double.valueOf(tauxText.getText()),(Client)listeClient.getSelectedValue());
																				}
																				catch (MalformedURLException e1) {e1.printStackTrace();}
																				catch (RemoteException e1) {e1.printStackTrace();} 
																				catch (NotBoundException e1) {e1.printStackTrace();}
																			}
																		});
															        	pTempL.add(pt);
															        	pTempL.add(creerLivretB);
															        	pTempL.revalidate();
															        	pTempL.repaint();
															        }
															    }
															});
															
															scrollClient = new JScrollPane(listeClient);
															pTempL.add(scrollClient);
															pTempL.revalidate();
										        	} 
										        	catch (RemoteException e1)	{e1.printStackTrace();} 
										        	catch (MalformedURLException e1) {e1.printStackTrace();} 
										        	catch (NotBoundException e1) {e1.printStackTrace();}
										        }
										    }
										});
										
										scrollAgence = new JScrollPane(listeAgence);
										pTempL.add(scrollAgence);
										pTempL.revalidate();
					        	} 
					        	catch (RemoteException e1)	{e1.printStackTrace();}
					        }
					    }
					});
					
				} 
				catch (RemoteException e1) {e1.printStackTrace();}
				catch (MalformedURLException e3) {e3.printStackTrace();}
				catch (NotBoundException e2) {e2.printStackTrace();}

				pTempL.add(scrollBanque);
				panClientL.add(pTempL,BorderLayout.CENTER);
				panClientL.revalidate();
			}
		};
		return a;
	}
	
	
	
	public ActionListener supprimerLivretListener() 
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				panClientL.remove(pTempL);
				panClientC.remove(pTempC);
				panClientT.remove(pTempT);
				
				pTempL = new JPanel(new GridLayout(4,2,10,10));

				try 
				{
					final IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();

//					listeAgence.setVisibleRowCount(1);
					scrollAgence = new JScrollPane(listeAgence);
					
					listeBanque = new JList(banques.toArray());
//					listeBanque.setVisibleRowCount(1);
					scrollBanque = new JScrollPane(listeBanque);
					listeBanque.addListSelectionListener(new ListSelectionListener() 
					{
						@Override
						public void valueChanged(ListSelectionEvent e) 
						{
					        if (e.getValueIsAdjusting())
					        {
					        	pTempL.remove(scrollAgence);
					        	pTempL.remove(scrollClient);
					        	pTempL.remove(scrollLivret);
					        	pTempL.remove(supprimerLivretB);
					        	try 
					        	{
									HashMap<String, Agence> agences = serveurBanque.listeAgences((Banque) listeBanque.getSelectedValue());
									if(agences != null)										
										listeAgence = new JList(agences.values().toArray()); 
//										listeAgence.setVisibleRowCount(1);
										
										listeAgence.addListSelectionListener(new ListSelectionListener() 
										{
											@Override
											public void valueChanged(ListSelectionEvent e) 
											{
										        if (e.getValueIsAdjusting())
										        {
										        	pTempL.remove(scrollClient);
										        	pTempL.remove(scrollLivret);
										        	pTempL.remove(supprimerLivretB);
										        	try 
										        	{
														IClient serveurClient = (IClient) Naming.lookup(((Agence)listeAgence.getSelectedValue()).getAdresseServeurClient());
														ArrayList<Client> clients = serveurClient.listeClients((Agence) listeAgence.getSelectedValue());
														if(clients != null)										
															listeClient = new JList(clients.toArray()); 
															
															listeClient.addListSelectionListener(new ListSelectionListener() 
															{
																@Override
																public void valueChanged(ListSelectionEvent e) 
																{
															        if (e.getValueIsAdjusting())
															        {
															        	pTempL.remove(scrollLivret);
															        	pTempL.remove(supprimerLivretB);
															        	try 
															        	{
																			final ICompte serveurCompte = (ICompte) Naming.lookup(((Client)listeClient.getSelectedValue()).getAdresseServeurCompte());
																			ArrayList<Compte> comptes = serveurCompte.rechercheLivret((Client) listeClient.getSelectedValue());
																			if(comptes != null)										
																				listeLivret = new JList(comptes.toArray()); 
//																				listeLivret.setVisibleRowCount(1);
																				
																				listeLivret.addListSelectionListener(new ListSelectionListener() 
																				{
																					@Override
																					public void valueChanged(ListSelectionEvent e)
																					{
																						if(e.getValueIsAdjusting())
																						{
																							pTempL.add(supprimerLivretB);
																							pTempL.revalidate();
																						}
																					}
																				});

																	        	supprimerLivretB = new JButton("supprimer Livret");
																				scrollLivret = new JScrollPane(listeLivret);
																				supprimerLivretB.addActionListener(new ActionListener() 
																				{
																					@Override
																					public void actionPerformed(ActionEvent e) 
																					{
																						try 
																						{
																							serveurCompte.detruireCompte(((Compte)listeLivret.getSelectedValue()).getNumero());
																						}
																						catch (RemoteException e1) {e1.printStackTrace();}
																					}
																				});
																				
																				pTempL.add(scrollLivret);
																				pTempL.revalidate();
															        	} 
															        	catch (RemoteException e1)	{e1.printStackTrace();} 
															        	catch (MalformedURLException e1) {e1.printStackTrace();} 
															        	catch (NotBoundException e1) {e1.printStackTrace();}
															        }
															    }
															});
															
															scrollClient = new JScrollPane(listeClient);
															pTempL.add(scrollClient);
															pTempL.revalidate();
										        	} 
										        	catch (RemoteException e1)	{e1.printStackTrace();} 
										        	catch (MalformedURLException e1) {e1.printStackTrace();} 
										        	catch (NotBoundException e1) {e1.printStackTrace();}
										        }
										    }
										});
										
										scrollAgence = new JScrollPane(listeAgence);
										pTempL.add(scrollAgence);
										pTempL.revalidate();
					        	} 
					        	catch (RemoteException e1)	{e1.printStackTrace();}
					        }
					    }
					});
					
				} 
				catch (RemoteException e1) {e1.printStackTrace();}
				catch (MalformedURLException e3) {e3.printStackTrace();}
				catch (NotBoundException e2) {e2.printStackTrace();}

				pTempL.add(scrollBanque);
				panClientL.add(pTempL,BorderLayout.CENTER);
				panClientL.revalidate();
			}
		};
		return a;
	}
	
	public ActionListener crediterListener() 
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				panClientL.remove(pTempL);
				panClientC.remove(pTempC);
				panClientT.remove(pTempT);
				
				pTempT = new JPanel(new GridLayout(4,2,10,10));

				try 
				{
					final IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();

//					listeAgence.setVisibleRowCount(1);
					scrollAgence = new JScrollPane(listeAgence);
					
					listeBanque = new JList(banques.toArray());
//					listeBanque.setVisibleRowCount(1);
					scrollBanque = new JScrollPane(listeBanque);
					listeBanque.addListSelectionListener(new ListSelectionListener() 
					{
						@Override
						public void valueChanged(ListSelectionEvent e) 
						{
					        if (e.getValueIsAdjusting())
					        {
					        	pTempT.remove(scrollAgence);
					        	pTempT.remove(scrollClient);
					        	pTempT.remove(scrollCompte);
					        	pTempT.remove(panCredit);
					        	panCredit = new JPanel();
								panCredit.add(creditB);
								panCredit.add(creditText);
					        	try 
					        	{
									HashMap<String, Agence> agences = serveurBanque.listeAgences((Banque) listeBanque.getSelectedValue());
									if(agences != null)										
										listeAgence = new JList(agences.values().toArray()); 
//										listeAgence.setVisibleRowCount(1);
										
										listeAgence.addListSelectionListener(new ListSelectionListener() 
										{
											@Override
											public void valueChanged(ListSelectionEvent e) 
											{
										        if (e.getValueIsAdjusting())
										        {
										        	pTempT.remove(scrollClient);
										        	pTempT.remove(scrollCompte);
										        	pTempT.remove(panCredit);
										        	try 
										        	{
														IClient serveurClient = (IClient) Naming.lookup(((Agence)listeAgence.getSelectedValue()).getAdresseServeurClient());
														ArrayList<Client> clients = serveurClient.listeClients((Agence) listeAgence.getSelectedValue());
														if(clients != null)										
															listeClient = new JList(clients.toArray()); 
//															listeClient.setVisibleRowCount(1);
															
															listeClient.addListSelectionListener(new ListSelectionListener() 
															{
																@Override
																public void valueChanged(ListSelectionEvent e) 
																{
															        if (e.getValueIsAdjusting())
															        {
															        	pTempT.remove(scrollCompte);
															        	pTempT.remove(panCredit);
															        	
															        	panCredit = new JPanel();
															        	creditB = new JButton("Credit");
															    		panCredit.add(creditB);
															    		panCredit.add(creditText);
															        	try 
															        	{
																			final ICompte serveurCompte = (ICompte) Naming.lookup(((Client)listeClient.getSelectedValue()).getAdresseServeurCompte());
																			ArrayList<Compte> comptes = serveurCompte.rechercheToutCompte((Client) listeClient.getSelectedValue());
																			if(comptes != null)										
																				listeCompte = new JList(comptes.toArray()); 
//																				listeCompte.setVisibleRowCount(1);
																				
																				listeCompte.addListSelectionListener(new ListSelectionListener() 
																				{
																					@Override
																					public void valueChanged(ListSelectionEvent e)
																					{
																						if(e.getValueIsAdjusting())
																						{
																							pTempT.add(panCredit);
																							pTempT.revalidate();
																						}
																					}
																				});
																				
																				scrollCompte = new JScrollPane(listeCompte);
																				creditB.addActionListener(new ActionListener() 
																				{
																					@Override
																					public void actionPerformed(ActionEvent e) 
																					{
																						try 
																						{
																							serveurCompte.credit(Integer.valueOf(creditText.getText()),((Compte)listeCompte.getSelectedValue()).getNumero());
																						}
																						catch (RemoteException e1) {e1.printStackTrace();}
																					}
																				});
																				
																				pTempT.add(scrollCompte);
																				pTempT.revalidate();
															        	} 
															        	catch (RemoteException e1)	{e1.printStackTrace();} 
															        	catch (MalformedURLException e1) {e1.printStackTrace();} 
															        	catch (NotBoundException e1) {e1.printStackTrace();}
															        }
															    }
															});
															
															scrollClient = new JScrollPane(listeClient);
															pTempT.add(scrollClient);
															pTempT.revalidate();
										        	} 
										        	catch (RemoteException e1)	{e1.printStackTrace();} 
										        	catch (MalformedURLException e1) {e1.printStackTrace();} 
										        	catch (NotBoundException e1) {e1.printStackTrace();}
										        }
										    }
										});
										
										scrollAgence = new JScrollPane(listeAgence);
										pTempT.add(scrollAgence);
										pTempT.revalidate();
					        	} 
					        	catch (RemoteException e1)	{e1.printStackTrace();}
					        }
					    }
					});
					
				} 
				catch (RemoteException e1) {e1.printStackTrace();}
				catch (MalformedURLException e3) {e3.printStackTrace();}
				catch (NotBoundException e2) {e2.printStackTrace();}

				pTempT.add(scrollBanque);
				panClientT.add(pTempT,BorderLayout.CENTER);
				panClientT.revalidate();
			}
		};
		return a;
	}
	public ActionListener debiterListener() 
	{
		ActionListener a = new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				panClientL.remove(pTempL);
				panClientC.remove(pTempC);
				panClientT.remove(pTempT);
				
				pTempT = new JPanel(new GridLayout(4,2,10,10));

				try 
				{
					final IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();

					scrollAgence = new JScrollPane(listeAgence);
					
					listeBanque = new JList(banques.toArray());
					scrollBanque = new JScrollPane(listeBanque);
					listeBanque.addListSelectionListener(new ListSelectionListener() 
					{
						@Override
						public void valueChanged(ListSelectionEvent e) 
						{
					        if (e.getValueIsAdjusting())
					        {
					        	pTempT.remove(scrollAgence);
					        	pTempT.remove(scrollClient);
					        	pTempT.remove(scrollCompte);
					        	pTempT.remove(panDebit);
					        	

					        	try 
					        	{
									HashMap<String, Agence> agences = serveurBanque.listeAgences((Banque) listeBanque.getSelectedValue());
									if(agences != null)										
										listeAgence = new JList(agences.values().toArray()); 										
										listeAgence.addListSelectionListener(new ListSelectionListener() 
										{
											@Override
											public void valueChanged(ListSelectionEvent e) 
											{
										        if (e.getValueIsAdjusting())
										        {
										        	pTempT.remove(scrollClient);
										        	pTempT.remove(scrollCompte);
										        	pTempT.remove(panDebit);
										        	try 
										        	{
														IClient serveurClient = (IClient) Naming.lookup(((Agence)listeAgence.getSelectedValue()).getAdresseServeurClient());
														ArrayList<Client> clients = serveurClient.listeClients((Agence) listeAgence.getSelectedValue());
														if(clients != null)										
															listeClient = new JList(clients.toArray()); 
															
															listeClient.addListSelectionListener(new ListSelectionListener() 
															{
																@Override
																public void valueChanged(ListSelectionEvent e) 
																{
															        if (e.getValueIsAdjusting())
															        {
															        	pTempT.remove(scrollCompte);
															        	pTempT.remove(panDebit);
															        	
															        	panDebit = new JPanel();
															        	debitB = new JButton("Debit");
															    		panDebit.add(debitB);
															    		panDebit.add(debitText);
															        	try 
															        	{
																			final ICompte serveurCompte = (ICompte) Naming.lookup(((Client)listeClient.getSelectedValue()).getAdresseServeurCompte());
																			ArrayList<Compte> comptes = serveurCompte.rechercheToutCompte((Client) listeClient.getSelectedValue());
																			if(comptes != null)										
																				listeCompte = new JList(comptes.toArray()); 
																				
																				listeCompte.addListSelectionListener(new ListSelectionListener() 
																				{
																					@Override
																					public void valueChanged(ListSelectionEvent e)
																					{
																						if(e.getValueIsAdjusting())
																						{
																							pTempT.add(panDebit);
																							pTempT.revalidate();
																						}
																					}
																				});

																				debitB.addActionListener(new ActionListener() 
																				{
																					@Override
																					public void actionPerformed(ActionEvent e) 
																					{
																						try 
																						{
																							serveurCompte.debit(Integer.valueOf(debitText.getText()),((Compte)listeCompte.getSelectedValue()).getNumero());
																						}
																						catch (RemoteException e1) {e1.printStackTrace();}
																					}
																				});
																				
																				scrollCompte = new JScrollPane(listeCompte);
																				pTempT.add(scrollCompte);
																				pTempT.revalidate();
															        	} 
															        	catch (RemoteException e1)	{e1.printStackTrace();} 
															        	catch (MalformedURLException e1) {e1.printStackTrace();} 
															        	catch (NotBoundException e1) {e1.printStackTrace();}
															        }
															    }
															});
															
															scrollClient = new JScrollPane(listeClient);
															pTempT.add(scrollClient);
															pTempT.revalidate();
										        	} 
										        	catch (RemoteException e1)	{e1.printStackTrace();} 
										        	catch (MalformedURLException e1) {e1.printStackTrace();} 
										        	catch (NotBoundException e1) {e1.printStackTrace();}
										        }
										    }
										});
										
										scrollAgence = new JScrollPane(listeAgence);
										pTempT.add(scrollAgence);
										pTempT.revalidate();
					        	} 
					        	catch (RemoteException e1)	{e1.printStackTrace();}
					        }
					    }
					});
					
				} 
				catch (RemoteException e1) {e1.printStackTrace();}
				catch (MalformedURLException e3) {e3.printStackTrace();}
				catch (NotBoundException e2) {e2.printStackTrace();}

				pTempT.add(scrollBanque);
				panClientT.add(pTempT,BorderLayout.CENTER);
				panClientT.revalidate();
			}
		};
		return a;
	}
}
