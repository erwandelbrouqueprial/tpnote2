/**
 * 
 */
package vues;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domaine.IPersonne;

/**
 * @author admin
 *
 */
public class PanelFilsPersonne extends JPanel implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel<IPersonne> listModel;
	private JLabel label;
	private JScrollPane pane;
	private JList<IPersonne> listFils;

	public PanelFilsPersonne(IPersonne p){
		setLayout(new GridBagLayout());
		listModel = new DefaultListModel<IPersonne>();
		for(IPersonne per : p.getLesFils()){
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
		add(pane,gbc);

		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		label = new JLabel("Evaluation de ? ");
		add(label, gbc);
	}

	public void valueChanged(ListSelectionEvent e) {
		setLabel(listModel.get(listFils.getSelectedIndex()));
	}

	public void setLabel(IPersonne p){
		label.setText("Évaluation de "+p.getNom());

	}

}
