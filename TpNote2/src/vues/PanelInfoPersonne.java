/**
 * 
 */
package vues;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domaine.IPersonne;

/**
 * @author erwan
 *
 */

public class PanelInfoPersonne extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3995084449410919521L;
	
	private JPanel btnAn;
	private JButton b;
	private JFrame last;
	private JFrame current;
	
	public PanelInfoPersonne(IPersonne p,JFrame last, JFrame current){
		this.last = last;
		this.current = current;
		setLayout(new BorderLayout());
		Box b1 = Box.createVerticalBox();
		JLabel nom = new JLabel("votre nom: "+p.getNom());
		b1.add(nom);
		JLabel pre = new JLabel("votre prenom: "+p.getPrenom());
		b1.add(pre);
		JLabel eval = null;
		if(!p.getEvaluation().isEmpty()){
			eval = new JLabel("votre évaluation: "+p.getEvaluation());
		} else {
			eval = new JLabel("votre évaluation: Aucune évaluation");
		}
		b1.add(eval);
		JLabel LabelPere = null;
		IPersonne pere = p.getA_pour_pere();
		if(pere != null){
			LabelPere = new JLabel("Votre père: "+p.getA_pour_pere().getNom());
		}else{
			LabelPere = new JLabel("Votre père: Vous êtes orphelin");
		}
		b1.add(LabelPere);
		btnAn = new JPanel();
		
		add(b1,BorderLayout.WEST);
		b = new JButton("annuler");
		b.addActionListener(this);
		btnAn.add(b);
		add(btnAn,BorderLayout.EAST);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b){
			current.dispose();
			last.setVisible(true);
		}
	}
}
