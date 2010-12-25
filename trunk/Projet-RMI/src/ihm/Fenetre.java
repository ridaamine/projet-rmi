package ihm;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame
{
	public Fenetre(JPanel pan,String nom)
	{
		this.add(pan);
		this.setTitle(nom);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setMinimumSize(new Dimension(700,700));
	}
}
