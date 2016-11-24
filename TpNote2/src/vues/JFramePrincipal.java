/**
 * 
 */
package vues;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import domaine.IPersonne;

/**
 * @author erwan Classe représentant la fenêtre principale de l'application
 *         (celle qui s'affiche après la connexion d'un utilisateur)
 */
public class JFramePrincipal extends JFrame {

	private static final long serialVersionUID = -8216508345052272034L;

	private Container c;

	/**
	 * Construction de la fenêtre principale
	 * 
	 * @param last
	 * @param p
	 */
	public JFramePrincipal(JFrame last, IPersonne p) {
		c = getContentPane();
		c.setLayout(new BorderLayout());
		PanelInfoPersonne pan = new PanelInfoPersonne(p, last, this);
		PanelFilsPersonne pan2 = new PanelFilsPersonne(p);

		c.add(pan, BorderLayout.NORTH);
		c.add(pan2, BorderLayout.CENTER);

		setTitle("Evaluation");
		setSize(new Dimension(400, 400));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
