/**
 * 
 */
package persistance;

import java.sql.SQLException;
import java.util.List;

import domaine.IPersonne;
import domaine.Personne;

/**
 * @author admin
 *
 */
public class ListFilsPersonne implements Factory< List<IPersonne> >{

	private int id;
	
	public ListFilsPersonne(final int id){
		this.id = id;
	}

	public List<IPersonne> create() {
		DataMapperGenerique<IPersonne> dmgP = new DataMapperGenerique<IPersonne>("coo_personne", Personne.getFields(), Personne.class);
		try {
			return dmgP.findFilsOf(id);
		} catch (SQLException e) {
			return null;
		}
	}

}
