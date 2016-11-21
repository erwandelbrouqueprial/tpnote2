/**
 * 
 */
package vues;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domaine.Personne;

/**
 * @author erwan
 *
 */
public class JFramePrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8216508345052272034L;
	
	public JFramePrincipal(JFrame last,Personne p){
		setTitle("Evaluation");
		setSize(new Dimension(400,400));
		setResizable(false);
		setLayout(new CardLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		PanelInfoPersonne pan = new PanelInfoPersonne(p, last,this);
		
		add(pan,BorderLayout.NORTH);
		setVisible(true);
	}


}
