package vues;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domaine.IPersonne;
import services.UnitOfWork;

/**
 * @author admin Panel de l'application représentant la liste des fils d'une
 *         personne
 */
public class PanelFilsPersonne extends JPanel implements ListSelectionListener,
		ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private DefaultListModel<IPersonne> listModel;
	private JLabel label;
	private JScrollPane pane;
	private JList<IPersonne> listFils;
	private JTextField saisie;
	private JButton valider;

	/**
	 * Construction de la fenêtre
	 * 
	 * @param p
	 */
	public PanelFilsPersonne(IPersonne p) {
		setLayout(new GridBagLayout());
		listModel = new DefaultListModel<IPersonne>();
		for (IPersonne per : p.getLesFils()) {
			System.out.println(per);
			listModel.addElement(per);
		}
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 4;

		listFils = new JList<IPersonne>(listModel);
		listFils.addListSelectionListener(this);
		pane = new JScrollPane(listFils);
		add(pane, gbc);

		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		label = new JLabel("Evaluation de ? :");
		add(label, gbc);

		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		saisie = new JTextField();
		saisie.setPreferredSize(new Dimension(100, 50));
		saisie.addKeyListener(this);
		add(saisie, gbc);

		gbc.gridx = 4;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		valider = new JButton("Valider !");
		valider.addActionListener(this);
		add(valider, gbc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event
	 * .ListSelectionEvent)
	 */
	public void valueChanged(ListSelectionEvent e) {
		setLabel(listModel.get(listFils.getSelectedIndex()));
	}

	/**
	 * Changement du label lorsqu'on selectionne une autre personne
	 * 
	 * @param p
	 */
	public void setLabel(IPersonne p) {
		label.setText("Évaluation de " + p.getNom() + ":");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == valider) {
			System.out.println("on commit");
			UnitOfWork.getInstance().commit();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == saisie) {
			System.out.println("modification de l'évaluation de "
					+ listModel.get(listFils.getSelectedIndex()).getNom()
					+ " avec " + saisie.getText());
			listModel.get(listFils.getSelectedIndex()).setEvaluation(
					saisie.getText());
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// Méthode non implémentée de l'interface KeyListener
	}

	public void keyTyped(KeyEvent arg0) {
		// Méthode non implémentée de l'interface KeyListener
	}
}