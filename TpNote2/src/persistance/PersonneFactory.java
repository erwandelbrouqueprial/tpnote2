package persistance;

import java.sql.SQLException;

import domaine.IPersonne;
import domaine.Personne;

/**
 * @author admin Classe récupérant et crééant une personne grâce à son
 *         identifiant. Cette classe implémente Factory (utilisation du pattern
 *         factory)
 */
public class PersonneFactory implements Factory<IPersonne> {

	private int id;

	/**
	 * Constructeur de la classe
	 * 
	 * @param id
	 *            identifiant de la personne que l'on veut récupérer en base et
	 *            créer en objet
	 */
	public PersonneFactory(final int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.Factory#create()
	 */
	public IPersonne create() {
		DataMapperGenerique<Personne> dmgP = new DataMapperGenerique<Personne>(
				"coo_personne", Personne.getFields(), Personne.class);
		try {
			return dmgP.findById(id);
		} catch (SQLException e) {
			return null;
		}
	}

}
