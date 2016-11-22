/**
 * 
 */
package persistance;

import java.sql.SQLException;

import domaine.IPersonne;
import domaine.Personne;

/**
 * @author admin
 *
 */
public class PersonneFactory implements Factory<IPersonne> {

	private int id;
	public PersonneFactory(final int id) {
		this.id = id;
	}

	public IPersonne create() {
		DataMapperGenerique<Personne> dmgP = new DataMapperGenerique<Personne>("coo_personne", Personne.getFields(), Personne.class);
		try {
			return dmgP.findById(id);
		} catch (SQLException e) {
			return null;
		}
	}

}
