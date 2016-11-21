/**
 * 
 */
package services;

import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JFrame;

import domaine.Personne;
import persistance.DataMapperGenerique;
import vues.JFrameConnexion;
import vues.JFramePrincipal;

/**
 * @author delbrouquepri
 *
 */
public class Connexion {
	
	/**
	 * permet une connexion à la base de données
	 * @param c la fenetre de connexion
	 * @param id saisie par la personne.
	 */
	public static void login(JFrameConnexion c,int id){
		DataMapperGenerique<Personne> p = new DataMapperGenerique<Personne>("personne", Personne.getFields(), Personne.class);
		try {
			p.findById(id);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		c.setVisible(false);
		new JFramePrincipal(c);
	}

	/**
	 * Appel une frame et detruit l'ancienne.
	 * @param current la fenetre actuelle
	 * @param lastFrame la fenetre suivante 
	 */
	public static void callFrame(JFramePrincipal current,JFrame lastFrame) {
		// TODO Auto-generated method stub
		current.dispose();
		lastFrame.setVisible(true);
	}
}
