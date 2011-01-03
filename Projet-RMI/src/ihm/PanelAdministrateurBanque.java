package ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import service.IBanque;

/**
 * Cette classe permet de representé l'interface graphique d'un administrateur de banque
 *
 */
public class PanelAdministrateurBanque extends JPanel
{
	private JTextField nomText = new JTextField();

	
	private JButton creationBanqueB = new JButton("Cr√©ation banque");
	private JButton suppressionBanqueB = new JButton("Suppression banque");
	private JButton rechercheBanqueB = new JButton("Recherche banque");
	private JPanel panBanque = new JPanel(new BorderLayout());
	private JPanel pTemp = new JPanel();
	
	public PanelAdministrateurBanque() 
	{
		super();
		init();
	}

	private void init()
	{
	
		creationBanqueB.addActionListener(creerBanqueListener());
		suppressionBanqueB.addActionListener(supprimerBanqueListener());
		rechercheBanqueB.addActionListener(rechercherBanqueListener());
		
		nomText.setColumns(7);
		
		JPanel pt = new JPanel();
		pt.add(creationBanqueB);
		pt.add(suppressionBanqueB);
		pt.add(rechercheBanqueB);
		panBanque.add(pt,BorderLayout.NORTH);
		this.add(panBanque,BorderLayout.NORTH);

	}

	private ActionListener creerBanqueListener() 
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				panBanque.remove(pTemp);
				pTemp = new JPanel();
				JButton validerB = new JButton("Valider");
				validerB.addActionListener(new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						try 
						{
							IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
							serveurBanque.creerBanque(nomText.getText());
						} 
						catch (MalformedURLException e) {e.printStackTrace();} 
						catch (RemoteException e) {e.printStackTrace();} 
						catch (NotBoundException e) {e.printStackTrace();}
					}
				});
				
				pTemp.add(new JLabel("Nom : "));
				pTemp.add(nomText);	
				pTemp.add(validerB);
				panBanque.add(pTemp,BorderLayout.SOUTH);
				panBanque.revalidate();
			}
		};
		return a;
	}
	

	private ActionListener rechercherBanqueListener() 
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				panBanque.remove(pTemp);
				pTemp = new JPanel();
				JButton validerB = new JButton("Valider");
				validerB.addActionListener(new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						try 
						{
							IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
							serveurBanque.rechercherBanque(nomText.getText());
						} 
						catch (MalformedURLException e) {e.printStackTrace();} 
						catch (RemoteException e) {e.printStackTrace();} 
						catch (NotBoundException e) {e.printStackTrace();}
					}
				});
				
				pTemp.add(new JLabel("Nom : "));
				pTemp.add(nomText);	
				pTemp.add(validerB);
				panBanque.add(pTemp,BorderLayout.SOUTH);
				panBanque.revalidate();
			}
		};
		return a;
	}
	

	private ActionListener supprimerBanqueListener() 
	{
		ActionListener a = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				panBanque.remove(pTemp);
				pTemp = new JPanel();
				JButton validerB = new JButton("Valider");
				validerB.addActionListener(new ActionListener() 
				{
					@Override
					public void actionPerformed(ActionEvent arg0) 
					{
						try 
						{
							IBanque serveurBanque = (IBanque) Naming.lookup("//127.0.0.1/Banque");
							serveurBanque.supprimerBanque(nomText.getText());
						} 
						catch (MalformedURLException e) {e.printStackTrace();} 
						catch (RemoteException e) {e.printStackTrace();} 
						catch (NotBoundException e) {e.printStackTrace();}
					}
				});
				
				pTemp.add(new JLabel("Nom : "));
				pTemp.add(nomText);	
				pTemp.add(validerB);
				panBanque.add(pTemp,BorderLayout.SOUTH);
				panBanque.revalidate();
			}
		};
		return a;
	}
		
}
