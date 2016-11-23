/**
 * 
 */
package domaine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import services.ObservateurPersonne;
import services.VisiteurPersonne;

/**
 * @author six
 * 
 */
public class Personne implements IPersonne {

	private int id;
	private List<ObservateurPersonne> obs;
	private String nom;
	private String prenom;
	private String evaluation;
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
		this.nom = nom;
		this.prenom = prenom;
		this.evaluation = evaluation;
		this.a_pour_pere = lePere;
		this.lesFils = new ArrayList<IPersonne>();
		this.obs = new ArrayList<ObservateurPersonne>();
	}

	public Personne(final String nom, final String prenom,
			final String evaluation, final Personne lePere) {
		this.id = increment++;
		this.nom = nom;
		this.prenom = prenom;
		this.evaluation = evaluation;
		this.a_pour_pere = lePere;
		this.lesFils = new ArrayList<IPersonne>();
		this.obs = new ArrayList<ObservateurPersonne>();
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
		notifier();
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
		notifier();
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#getEvalution()
	 */
	public String getEvaluation() {
		return evaluation;
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#setEvalution(java.lang.String)
	 */
	public void setEvaluation(String evalution) {
		this.evaluation = evalution;
		notifier();
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#getLePere()
	 */
	public IPersonne getA_pour_pere() {
		return a_pour_pere;
		
	}

	/* (non-Javadoc)
	 * @see domaine.IIPersonne#setLePere(domaine.IPersonne)
	 */
	public void setA_pour_pere(IPersonne lePere) {
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
		return fields;
	}

	public void add(ObservateurPersonne op) {
		obs.add(op);
	}

	public void notifier() {
		for (ObservateurPersonne o : obs){
			o.action(this);
		}
	}

	public void accepter(VisiteurPersonne v) {
		v.visiter(this);
	}
}
