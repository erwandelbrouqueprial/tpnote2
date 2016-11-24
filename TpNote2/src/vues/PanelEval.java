package vues;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import domaine.IPersonne;
import services.UnitOfWork;

/**
 * @author erwan Panel représentant la partie évaluation de l'application
 */
public class PanelEval extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextArea saisie;
	private JButton valider;
	private IPersonne p;

	/**
	 * Construction du panel d'évaluation
	 * 
	 * @param p
	 */
	public PanelEval(IPersonne p) {
		this.p = p;
		setLayout(new BorderLayout());
		saisie = new JTextArea();
		add(saisie);

		valider = new JButton("Valider !");
		valider.addActionListener(this);
		add(valider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == valider) {
			p.setEvaluation(saisie.getText());
			UnitOfWork.getInstance().commit();
		}
	}

}
