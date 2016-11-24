/**
 * 
 */
package persistance;

import domaine.Personne;
import services.VisiteurPersonne;

/**
 * @author erwan
 *	
 */
public class Committer extends VisiteurPersonne {

	@Override
	public void visiter(Personne p) {
		DataMapperGenerique<Personne> dmgP = new DataMapperGenerique<Personne>("coo_personne", Personne.getFields(), Personne.class);
		try {
			System.out.println("on met à jour "+p.getNom());
			dmgP.update(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
