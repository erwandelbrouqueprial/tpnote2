/**
 * 
 */
package services;

import domaine.IPersonne;
import domaine.Personne;

/**
 * @author erwan
 * 
 *         Classe implémentant le pattern Visitor. Cela permet d'externaliser et
 *         de centraliser des actions à effectuer sur des objets.
 * 
 */
public abstract class VisiteurPersonne {

	/**
	 * Permet à l'interface IPersonne d'accepter le visiteur qu'on applique
	 * 
	 * @param o
	 *            interface IPersonne
	 */
	public void visiter(IPersonne o) {
		o.accepter(this);
	}

	/**
	 * Méthode qui permet la visite d'un objet personne.
	 * 
	 * @param p
	 *            la personne a à visiter.
	 */
	abstract public void visiter(Personne p);
}
