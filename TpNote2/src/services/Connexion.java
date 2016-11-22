/**
 * 
 */
package services;

import java.sql.SQLException;

import domaine.IPersonne;
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
	 * permet une connexion � la base de donn�es
	 * @param c la fenetre de connexion
	 * @param id saisie par la personne.
	 */
	public static void login(JFrameConnexion c,int id){
		DataMapperGenerique<Personne> p = new DataMapperGenerique<Personne>("coo_personne", Personne.getFields(), Personne.class);
		IPersonne per = null;
		try {
			per = p.findById(id);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		c.setVisible(false);
		new JFramePrincipal(c,per);
	}

}
