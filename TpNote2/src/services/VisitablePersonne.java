package services;

/**
 * @author six Interface pour le pattern Visitor
 */
public interface VisitablePersonne {
	/**
	 * Permet d'accepter un visiteur sur un objet personne
	 * 
	 * @param v
	 *            le visiteur de la personne
	 */
	void accepter(VisiteurPersonne v);
}
