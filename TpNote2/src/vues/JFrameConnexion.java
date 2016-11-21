package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import services.Connexion;

public class JFrameConnexion extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1511263417777898704L;
	private JPanel panel_area = new JPanel();
	private JPanel panel_button = new JPanel();
	private JLabel label = new JLabel("identifiant de connexion:");
	private JTextField zoneTexte = new JTextField();
	private JButton button = new JButton("connexion");
	
	public JFrameConnexion(){
		
		setTitle("Evaluation");
		setSize(new Dimension(400,400));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		panel_area.setLayout(new FlowLayout());
		panel_area.add(label);
		zoneTexte.setColumns(10);
		panel_area.add(zoneTexte);
		button.addActionListener(this);
		panel_button.add(button);
		panel_button.setBackground(Color.gray);
		
		getContentPane().add(panel_area,BorderLayout.CENTER);
		getContentPane().add(panel_button,BorderLayout.SOUTH);

		setVisible(true);
	}

	/**
	 * Permet d'afficher une erreur sur la fenetre
	 * @param string le message d'erreur
	 */
	public void showError(String string) {
		panel_area.add(new JLabel(string));
		validate();
	}

	/**
	 * Vérifie que la valeur entrée dans le JTextField est bien un entier (correspondant à un identifiant)
	 * @return vrai si c'est un entier, sinon faux.
	 */
	public boolean isIntegerValue(){
		try{
			Integer.parseInt(zoneTexte.getText());
			return true;
		}catch(Exception e){
			return false;
		}	
	}

	/**
	 *  Association d'une class service à une execution de bouton.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button){
			if(isIntegerValue()){
				Connexion.login(this,Integer.parseInt(zoneTexte.getText()));
			}else{
				showError("Erreur, veuillez saisir un entier.");
			}
		}
	}

}

