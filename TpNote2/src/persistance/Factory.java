/**
 * 
 */
package persistance;

/**
 * @author admin interface pour le pattern Factory.
 */
public interface Factory<T> {
	/**
	 * Méthode de création d'objets.
	 * 
	 * @return le(s) objet(s) créés
	 */
	T create();
}
