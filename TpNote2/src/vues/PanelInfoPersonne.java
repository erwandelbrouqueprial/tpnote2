/**
 * 
 */
package vues;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domaine.Personne;

/**
 * @author erwan
 *
 */

public class PanelInfoPersonne extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3995084449410919521L;
	
	private Personne p;
	private JPanel info;
	private JPanel btnAn;
	private JButton b;
	
	public PanelInfoPersonne(Personne p ){
		this.setP(p);
		setLayout(new BorderLayout());
		info = new JPanel(new BorderLayout());
		
		JLabel nom = new JLabel("nom: "+p.getNom());
		info.add(nom);
		JLabel pre = new JLabel("prenom: "+p.getPrenom());
		info.add(pre);
		btnAn = new JPanel();
		add(info,BorderLayout.WEST);
		b = new JButton("annuler");
		btnAn.add(b);
		add(btnAn,BorderLayout.EAST);
		
	}

	/**
	 * @return the p
	 */
	public Personne getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(Personne p) {
		this.p = p;
	}

}
