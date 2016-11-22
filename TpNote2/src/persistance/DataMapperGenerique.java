package persistance;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domaine.Personne;

/**
 * Classe DataMapperGenerique : elle permet le mapping et donc la persistance
 * entre les objets java et la base de données
 * 
 * @author six
 * 
 * @param <T>
 */
public class DataMapperGenerique<T> {

	private Map<String, Class<?>> fields;
	private String table;
	private Class<?> maClasse;

	/**
	 * Constructeur du Mapper Générique
	 * 
	 * @param table
	 *            dans laquelle on veut insérer des données
	 * @param fields
	 *            champs concernés dans l'insertion
	 * @param maClasse
	 *            classe de l'objet java dont on veut insérer ses données en
	 *            base
	 */
	public DataMapperGenerique(final String table,final Map<String, Class<?>> fields, final Class<?> maClasse) {
		this.maClasse = maClasse;
		this.table = table;
		this.fields = fields;
	}

	/**
	 * Cette méthode générique permet l'insertion d'objets en base.
	 * 
	 * @param p
	 *            Objet que l'on voudrait insérer en base
	 * @throws Exception
	 */
	public void insert(final T p) throws Exception {

		boolean first = true;
		// On construit une requête préparée pour une insertion en base
		String req = " VALUES(";
		String nomChamps = "";

		for (Map.Entry<String, Class<?>> e : fields.entrySet()) {
			if (!first) {
				// Si le champ n'est pas le premier de la liste, on ajoute des
				// virgules dans la requête préparée (entre les "?") et entre
				// les champs du " INSERT INTO (...)"
				req += ",";
				nomChamps += ",";
			}
			req += "?";
			nomChamps += e.getKey();
			first = false;
		}
		req = "INSERT INTO " + table + " (" + nomChamps + ") " + "(" + req
				+ ")";

		// La construction de la requête préparée est terminée. On peut la
		// passer dans le prepareStatement.
		PreparedStatement ps = DBConfig.getConnection().prepareStatement(req);

		int i = 0;
		// On récupére le nom des champs de la table dans lesquels on fait une
		// insertion
		for (Map.Entry<String, Class<?>> e : fields.entrySet()) {
			i++;
			String key = e.getKey(); // nom du champ
			// On construit le nom du getter correspondant au nom du champ
			String name = "get" + key.substring(0, 1).toUpperCase()
					+ key.substring(1);
			Method m = maClasse.getMethod(name);
			// On fait appel à la méthode construite sur l'objet passé en
			// paramètre. On construit un objet qui correspond au resultat de
			// l'execution du getter.
			Object r = m.invoke(p);

			if (e.getValue() != r.getClass()) {
				// TODO: gérer exception lorsque la méthode n'existe pas dans
				// l'objet P
			}
			// On remplie les "?" de la requête préparée en fonction du type des
			// champs
			if (e.getValue() == String.class) {
				ps.setString(i, (String) r);
			} else if (e.getValue() == Integer.class) {
				ps.setInt(i, (Integer) r);
			} else if (e.getValue() == Date.class) {
				ps.setDate(i, (Date) r);
			} else if (e.getValue() == Boolean.class){
				ps.setBoolean(i, (Boolean) r);
			}
		}

		ps.executeUpdate();
	}

	/**
	 * Cette méthode générique permet la suppression d'objets en base.
	 * 
	 * @param p
	 *            Objet que l'on veut insérer en base
	 * @throws Exception
	 */
	public void delete(final T p) throws Exception {

		boolean first = true;
		// On construit une requête préparée pour une insertion en base
		String req = "";

		for (Map.Entry<String, Class<?>> e : fields.entrySet()) {
			if (!first) {
				// Si le champ n'est pas le premier de la liste, on ajoute des
				// virgules dans la requête préparée (entre les "?") et entre
				// les champs du " INSERT INTO (...)"
				req += " AND ";
			}
			req+= e.getKey()+" = ?";
			first = false;
		}
		req = "DELETE FROM " + table + " WHERE "+ req;
		
		System.out.println(req);
		// La construction de la requête préparée est terminée. On peut la
		// passer dans le prepareStatement.
		PreparedStatement ps = DBConfig.getConnection().prepareStatement(req);

		int i = 0;
		// On récupére le nom des champs de la table dans lesquels on fait une
		// insertion
		for (Map.Entry<String, Class<?>> e : fields.entrySet()) {
			i++;
			String key = e.getKey(); // nom du champ
			// On construit le nom du getter correspondant au nom du champ
			String name = "get" + key.substring(0, 1).toUpperCase()
					+ key.substring(1);
			Method m = maClasse.getMethod(name);
			// On fait appel à la méthode construite sur l'objet passé en
			// paramètre. On construit un objet qui correspond au resultat de
			// l'execution du getter.
			Object r = m.invoke(p);

			if (e.getValue() != r.getClass()) {
				// TODO: gérer exception lorsque la méthode n'existe pas dans
				// l'objet P
			}
			// On remplie les "?" de la requête préparée en fonction du type des
			// champs
			if (e.getValue() == String.class) {
				ps.setString(i, (String) r);
			} else if (e.getValue() == Integer.class) {
				ps.setInt(i, (Integer) r);
			} else if (e.getValue() == Date.class) {
				ps.setDate(i, (Date) r);
			} else if (e.getValue() == Boolean.class){
				ps.setBoolean(i, (Boolean) r);
			}
		}
		
		ps.executeUpdate();
		
	}

	/**
	 * Cette méthode générique permet la mise à jour d'objets en base.
	 * 
	 * @param p
	 *            Objet que l'on veut insérer en base
	 * @throws Exception
	 */
	public void update(final T p) throws Exception {
		// TODO : finir la màj générique
	}
	
	/**
	 *  Permet de retrouver un objet en base gr�ce � son identifiant.
	 *  
	 * @param id
	 * @return T Un objet Correspondant au type de T g�n�rique
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public T findById(final int id) throws SQLException{
		//On part du principe que pour tout les types d'objet l'identifiant est contenu dans un champ nomm� "id"
		System.out.println("on recherche par l'id");
		String req = "SELECT * FROM "+table+" WHERE id = ?";
		PreparedStatement ps = DBConfig.getConnection().prepareStatement(req);

		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()){
			if(this.maClasse == Personne.class){
				PersonneFactory fp = new PersonneFactory();
				Map<Integer, Personne> lp = new VirtualProxyGeneriqueBuilder<Map<Integer,Personne>>(Map.class, fp).getProxy();
			
				if(!lp.containsKey(rs.getString("id"))){
					System.out.println("je en contient pas "+rs.getString("id")+" je le créer");
					
					Personne p = new Personne(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"), rs.getString("evaluation"), (Personne) findById(rs.getInt("id")));
					lp.put(p.getId(), p);
				}else{
					System.out.println("Je le contient déjà donc je le retourne");
					return (T) lp.get(rs.getInt("id"));
					
				}
			}
		}
		return null;		
	}

	public Map<Integer, T> findAll() throws SQLException {
		System.out.println("on cherche tout le monde");
		Map<Integer, T> t = null ;
		String req = "SELECT * FROM "+table;
		PreparedStatement ps = DBConfig.getConnection().prepareStatement(req);
		
		ResultSet rs = ps.executeQuery();
		if(this.maClasse == Personne.class){
			PersonneFactory fp = new PersonneFactory();
			Map<Integer, Personne> lp = new VirtualProxyGeneriqueBuilder<Map<Integer,Personne>>(Map.class, fp).getProxy();
		
			while(rs.next()){
				if(lp.containsKey(rs.getString("id"))== false){
					System.out.println("je ne contient pas "+rs.getString("id"));
					Personne p = new Personne(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"), rs.getString("evaluation"),(Personne) findById(rs.getInt("id")));
					t.put(p.getId(), (T) p);
				}
				
			}
		}
		return t;	
	}

	// TODO : créer autres méthodes findWithCondition, etc..
}