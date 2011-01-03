package ihm;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * Cette classe permet de definir les proprété de base d'une fenetre (taille, action lors de la fermeture ...)
 *
 */
public class Fenetre extends JFrame
{
	/**
	 * 
	 * @param pan le panel que l'on va inserer dans la fenetre
	 * @param nom le nom de la fenetre
	 */
	public Fenetre(JPanel pan,String nom)
	{
		this.add(pan);
		this.setTitle(nom);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setMinimumSize(new Dimension(700,700));
	}
}
