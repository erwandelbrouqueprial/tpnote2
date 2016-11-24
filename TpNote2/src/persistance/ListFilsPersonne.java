package persistance;

import java.sql.SQLException;
import java.util.List;

import domaine.IPersonne;
import domaine.Personne;

/**
 * @author admin Classe générant la liste des fils d'une personne. Cette classe
 *         implémente Factory (utilisation du pattern Factory)
 */
public class ListFilsPersonne implements Factory<List<IPersonne>> {

	private int id;

	/**
	 * Constructeur de la classe
	 * 
	 * @param id
	 *            identifiant de la personne dont on voudra récupérer et créer
	 *            la liste de fils correspondante.
	 */
	public ListFilsPersonne(final int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see persistance.Factory#create()
	 */
	public List<IPersonne> create() {
		DataMapperGenerique<IPersonne> dmgP = new DataMapperGenerique<IPersonne>(
				"coo_personne", Personne.getFields(), Personne.class);
		try {
			return dmgP.findFilsOf(id);
		} catch (SQLException e) {
			return null;
		}
	}

}
