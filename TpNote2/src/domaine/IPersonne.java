package domaine;

import java.util.List;

public interface IPersonne {

	/**
	 * @return l'identifiant de la personne
	 */
	int getId();

	/**
	 * @param id
	 */
	void setId(int id);

	/**
	 * @return le nom de la personne
	 */
	String getNom();

	/**
	 * @param nom
	 */
	void setNom(String nom);

	/**
	 * @return le prenom de la personne
	 */
	String getPrenom();

	/**
	 * @param prenom
	 */
	void setPrenom(String prenom);

	/**
	 * @return l'évaluation de la personne
	 */
	String getEvalution();

	/**
	 * @param evalution
	 */
	void setEvalution(String evalution);

	/**
	 * @return l'objet Personne qui correspond au père de la personne (le père
	 *         étant celui qui évalue la personne dans son activité
	 *         professionnelle)
	 */
	IPersonne getLePere();

	/**
	 * @param lePere
	 */
	void setLePere(IPersonne lePere);

	/**
	 * @return l'ensemble des objets Personne qui correspondent au fils de la
	 *         personne (les fils d'une personne sont les personnes qu'elle
	 *         évalue). Il est possible que la personne n'évalue personne et
	 *         qu'elle n'a donc pas de fils.
	 */
	List<IPersonne> getLesFils();

	/**
	 * @param lesFils
	 */
	void setLesFils(List<IPersonne> lesFils);

	String toString();

}