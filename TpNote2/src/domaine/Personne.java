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
public class Personne implements IPersonne {

	private int id;
	private String nom;
	private String prenom;
	private String evalution;
	private IPersonne a_pour_pere;
	private List<IPersonne> lesFils;
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
		this.setLesFils(new ArrayList<IPersonne>());
	}

	public Personne(final String nom, final String prenom,
			final String evaluation, final Personne lePere) {
		this.id = increment++;
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setEvalution(evaluation);
		this.setLePere(lePere);
		this.setLesFils(new ArrayList<IPersonne>());
	}
	/* (non-Javadoc)
	 * @see domaine.IIPersonne#getId()
	 */
	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#setId(int)
	 */
	public void setId(int id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#getNom()
	 */
	public String getNom() {
		return nom;
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#setNom(java.lang.String)
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#getPrenom()
	 */
	public String getPrenom() {
		return prenom;
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#setPrenom(java.lang.String)
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#getEvalution()
	 */
	public String getEvalution() {
		return evalution;
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#setEvalution(java.lang.String)
	 */
	public void setEvalution(String evalution) {
		this.evalution = evalution;
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#getLePere()
	 */
	public IPersonne getLePere() {
		return a_pour_pere;
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#setLePere(domaine.IPersonne)
	 */
	public void setLePere(IPersonne lePere) {
		this.a_pour_pere = lePere;
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#getLesFils()
	 */
	public List<IPersonne> getLesFils() {
		return lesFils;
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#setLesFils(java.util.List)
	 */
	public void setLesFils(List<IPersonne> lesFils) {
		this.lesFils = lesFils;
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#toString()
	 */
	public String toString(){
		return this.nom;
	}
	/**
	 * 
	 * @return l'ensemble des champs pr�sent dans la table personne
	 */
	public static Map<String, Class<?>> getFields(){
		Map<String, Class<?>> fields = new HashMap<String, Class<?>>();
		fields.put("id", Integer.class);
		fields.put("nom", String.class);
		fields.put("prenom", String.class);
		fields.put("evaluation",String.class);
		fields.put("a_pou_pere", Integer.class);
		return fields;
	}
}
