/**
 * 
 */
package persistance;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import domaine.Personne;

/**
 * @author admin
 *
 */
public class PersonneFactory implements Factory< Map<Integer,Personne> >{

	Map<Integer, Personne > instance = null;
	
	public Map<Integer, Personne> create() {
		if(instance == null){
			instance = new HashMap<Integer, Personne >();
			try {
				DataMapperGenerique<Personne> dmgP = new DataMapperGenerique<Personne>("coo_personne", Personne.getFields(), Personne.class);
				instance = dmgP.findAll();
			} catch (SQLException e) {
				System.out.println("erreur");
			}	
		}
		return instance;
	}

}
