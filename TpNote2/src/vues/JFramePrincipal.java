/**
 * 
 */
package vues;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import domaine.Personne;
import services.Connexion;

/**
 * @author erwan
 *
 */
public class JFramePrincipal extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8216508345052272034L;
	private JButton button;
	private JFrame lastFrame;
	
	public JFramePrincipal(JFrame c,Personne p){
		lastFrame = c;
		setTitle("Evaluation");
		setSize(new Dimension(400,400));
		setResizable(false);
		setLayout(new CardLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		add(new PanelInfoPersonne(p),BorderLayout.NORTH);
		
		button = new JButton();
		button.setText("retour");
		button.addActionListener(this);
		
		add(button,BorderLayout.SOUTH);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button){
			Connexion.callFrame(this, lastFrame);
		}
	}
}
