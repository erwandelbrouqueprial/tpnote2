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
	
	private Personne p;
	private JPanel info;
	private JPanel btnAn;
	private JButton b;
	
	public PanelInfoPersonne(Personne p ){
		this.p = p;
		setLayout(new BorderLayout());
		
		info.setLayout(new CardLayout());
		info.add(new JLabel("nom"));
		info.add(new JLabel("prenom"));
		
		add(info,BorderLayout.WEST);
		b = new JButton("annuler");
		btnAn.add(b);
		add(btnAn,BorderLayout.EAST);
		
	}

}
