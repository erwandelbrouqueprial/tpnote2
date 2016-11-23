/**
 * 
 */
package services;

import domaine.IPersonne;
import domaine.Personne;

/**
 * @author erwan
 *
 */
public abstract class VisiteurPersonne {
	
	public void visiter(IPersonne o) {
		o.accepter(this);
	}
	
	abstract public void visiter(Personne p);
}
