/**
 * 
 */
package domaine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author six
 * 
 */
public class Personne {

	private int id;
	private String nom;
	private String prenom;
	private String evalution;
	private Personne lePere;
	private List<Personne> lesFils;
	private static int increment = 0;
	/**
	 * Constructeur de l'objet Personne
	 * 
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param evaluation
	 */
	public Personne(final int id, final String nom, final String prenom,
			final String evaluation, final Personne lePere) {
		this.id = id;
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setEvalution(evaluation);
		this.setLePere(lePere);
		this.setLesFils(new ArrayList<Personne>());
	}

	public Personne(final String nom, final String prenom,
			final String evaluation, final Personne lePere) {
		this.id = increment++;
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setEvalution(evaluation);
		this.setLePere(lePere);
		this.setLesFils(new ArrayList<Personne>());
	}
	/**
	 * @return l'identifiant de la personne
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return le nom de la personne
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return le prenom de la personne
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return l'évaluation de la personne
	 */
	public String getEvalution() {
		return evalution;
	}

	/**
	 * @param evalution
	 */
	public void setEvalution(String evalution) {
		this.evalution = evalution;
	}

	/**
	 * @return l'objet Personne qui correspond au père de la personne (le père
	 *         étant celui qui évalue la personne dans son activité
	 *         professionnelle)
	 */
	public Personne getLePere() {
		return lePere;
	}

	/**
	 * @param lePere
	 */
	public void setLePere(Personne lePere) {
		this.lePere = lePere;
	}

	/**
	 * @return l'ensemble des objets Personne qui correspondent au fils de la
	 *         personne (les fils d'une personne sont les personnes qu'elle
	 *         évalue). Il est possible que la personne n'évalue personne et
	 *         qu'elle n'a donc pas de fils.
	 */
	public List<Personne> getLesFils() {
		return lesFils;
	}

	/**
	 * @param lesFils
	 */
	public void setLesFils(List<Personne> lesFils) {
		this.lesFils = lesFils;
	}

	/**
	 * 
	 * @return l'ensemble des champs pr�sent dans la table personne
	 */
	public static Map<String, Class<?>> getFields(){
		Map<String, Class<?>> fields = new HashMap<String, Class<?>>();
		fields.put("id", String.class);
		fields.put("nom", String.class);
		fields.put("prenom", String.class);
		return fields;
	}
}
