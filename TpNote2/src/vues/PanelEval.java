/**
 * 
 */
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
 * @author erwan
 *
 */
public class PanelEval extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea saisie;
	private JButton valider;
	private IPersonne p;
	
	public PanelEval(IPersonne p){
		this.p = p;
		setLayout(new BorderLayout());
		saisie = new JTextArea();
		add(saisie);
		
		
		valider = new JButton("Valider !");
		valider.addActionListener(this);
		add(valider);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == valider){
			p.setEvaluation(saisie.getText());
			UnitOfWork.getInstance().commit();
		}
	}

	
}
