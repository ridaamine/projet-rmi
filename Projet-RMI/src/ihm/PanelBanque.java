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

import service.IAgence;
import service.IBanque;
import service.IClient;

import main.Adresse;
import classe.Agence;
import classe.Banque;
import classe.Client;

public class PanelBanque extends JPanel
{
	private JTextField rueText = new JTextField();
	private JTextField villeText = new JTextField();
	private JTextField telephoneText = new JTextField();
	
	private JScrollPane scrollBanque = new JScrollPane();
	private JList listeBanque = new JList();
	
	private JButton creationAgenceB = new JButton("Créer agence");
	private JButton suppressionAgenceB = new JButton("Supprimer agence");
	private JButton rechercheAgenceB = new JButton("Rechercher agence");
	private JPanel panAgence = new JPanel(new BorderLayout());
	
	private JPanel pTemp = new JPanel();
	
	public PanelBanque()
	{
		super();
		init();
	}
	
	public void init()
	{
		rueText.setColumns(7);
		villeText.setColumns(7);
		telephoneText.setColumns(7);
;
		
		JPanel pt = new JPanel();
		pt.add(creationAgenceB);
		pt.add(suppressionAgenceB);
		pt.add(rechercheAgenceB);
		panAgence.add(pt,BorderLayout.NORTH);
		
		creationAgenceB.addActionListener(creerAgenceListener());
		suppressionAgenceB.addActionListener(supprimerAgenceListener());
		rechercheAgenceB.addActionListener(rechercherAgenceListener());
		
		this.add(panAgence,BorderLayout.NORTH);
	}
	
	public ActionListener creerAgenceListener()
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				panAgence.remove(pTemp);
				
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
					
					listeBanque = new JList(banques.toArray());
					listeBanque.setVisibleRowCount(1);
					scrollBanque = new JScrollPane(listeBanque);
					
				} 
				catch (RemoteException e1) {e1.printStackTrace();}
				catch (MalformedURLException e3) {e3.printStackTrace();}
				catch (NotBoundException e2) {e2.printStackTrace();}
				
				validerB.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						Banque banque = (Banque) listeBanque.getSelectedValue();
						try 
						{
							IAgence serveurAgence = (IAgence) Naming.lookup(banque.getAdresseServeurAgence());
							serveurAgence.insererAgence(villeText.getText(), new Agence(new Adresse(rueText.getText(), villeText.getText(), telephoneText.getText()), (Banque)listeBanque.getSelectedValue()));
						} 
						catch (MalformedURLException e) {e.printStackTrace();} 
						catch (RemoteException e) {	e.printStackTrace();}
						catch (NotBoundException e) {e.printStackTrace();}
					}
				});
				
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
				panAgence.add(pTemp,BorderLayout.SOUTH);
				panAgence.revalidate();
			}
		};
		return a;
	}
	
	public ActionListener rechercherAgenceListener() 
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				panAgence.remove(pTemp);
				pTemp = new JPanel(new GridLayout(3,2,10,10));
				JPanel pp1 = new JPanel(new FlowLayout());


				
				JButton validerB = new JButton("Valider");

				try 
				{
					final IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();
					
					listeBanque = new JList(banques.toArray());
					listeBanque.setVisibleRowCount(1);
					scrollBanque = new JScrollPane(listeBanque);
					
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
						try 
						{
							IAgence serveurAgence = (IAgence) Naming.lookup(((Banque)listeBanque.getSelectedValue()).getAdresseServeurAgence());
							Agence agence = serveurAgence.rechercherAgence(villeText.getText());
							
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
				
				pp1.add(new JLabel("Ville : "));
				pp1.add(villeText);
				
				pTemp.add(pp1);
				
				pTemp.add(scrollBanque);
				pTemp.add(validerB);
				pTemp.add(trouverLab);
				panAgence.add(pTemp,BorderLayout.SOUTH);
				panAgence.revalidate();
			}
		};
		return a;
	}
	
	public ActionListener supprimerAgenceListener() 
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				panAgence.remove(pTemp);
				pTemp = new JPanel(new GridLayout(3,2,10,10));
				JPanel pp1 = new JPanel(new FlowLayout());


				
				JButton validerB = new JButton("Valider");

				try 
				{
					final IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
					ArrayList<Banque> banques;
					banques = serveurBanque.listeBanques();
					
					listeBanque = new JList(banques.toArray());
					listeBanque.setVisibleRowCount(1);
					scrollBanque = new JScrollPane(listeBanque);
					
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
						try 
						{
							IAgence serveurAgence = (IAgence) Naming.lookup(((Banque)listeBanque.getSelectedValue()).getAdresseServeurAgence());
							Agence agence = serveurAgence.rechercherAgence(villeText.getText());
							
							if(agence != null)
							{
								serveurAgence.retirerAgence(agence.getAdresse().getVille());
								trouverLab.setText("Agence supprimer");
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
				
				
				pp1.add(new JLabel("Ville : "));
				pp1.add(villeText);
				
				pTemp.add(pp1);;
				
				pTemp.add(scrollBanque);
				pTemp.add(validerB);
				pTemp.add(trouverLab);
				panAgence.add(pTemp,BorderLayout.SOUTH);
				panAgence.revalidate();
			}
		};
		return a;
	}
	
	
}
