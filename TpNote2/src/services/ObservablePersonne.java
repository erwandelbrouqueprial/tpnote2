package services;

/**
 * @author six Interface pour le patternet Observator.
 */
public interface ObservablePersonne {
	/**
	 * Methode permettant d'ajouter l'observateur
	 * 
	 * @param op
	 *            l'observateur que l'on veut ajouter
	 */
	void add(ObservateurPersonne op);

	/**
	 * Méthode qui notifie les actions lorsqu'elles se passent
	 */
	void notifier();
}
