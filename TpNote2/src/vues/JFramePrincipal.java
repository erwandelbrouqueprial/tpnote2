/**
 * 
 */
package vues;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

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
	
	public JFramePrincipal(JFrame c){
		lastFrame = c;
		setTitle("Evaluation");
		setSize(new Dimension(400,400));
		setResizable(false);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		button = new JButton();
		button.setText("retour");
		button.addActionListener(this);
		
		add(button,BorderLayout.SOUTH);
		setVisibility(true);
	}
	
	public void setVisibility(boolean b){
		setVisible(b);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button){
			Connexion.callFrame(this, lastFrame);
		}
	}
}
