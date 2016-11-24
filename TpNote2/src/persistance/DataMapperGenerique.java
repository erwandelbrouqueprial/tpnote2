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

import domaine.IPersonne;
import domaine.Personne;
import services.UnitOfWork;

/**
 * Classe DataMapperGenerique : elle permet le mapping et donc la persistance
 * entre les objets java et la base de données
 * 
 * @author six
 * 
 * @param <T>
 */
public class DataMapperGenerique<T extends domaine.IPersonne> {

	private HashMap<Integer, WeakReference<T>> reference;
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
	public DataMapperGenerique(final String table,
			final Map<String, Class<?>> fields, final Class<?> maClasse) {
		this.maClasse = maClasse;
		this.table = table;
		this.fields = fields;
		reference = new HashMap<Integer, WeakReference<T>>();
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
			} else if (e.getValue() == Boolean.class) {
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
			req += e.getKey() + " = ?";
			first = false;
		}
		req = "DELETE FROM " + table + " WHERE " + req;

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
			} else if (e.getValue() == Boolean.class) {
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

		boolean first = true;
		// On construit une requête préparée pour une suppression en base
		String req = " SET ";

		for (Map.Entry<String, Class<?>> e : fields.entrySet()) {
			if (!first) {
				// Si le champ n'est pas le premier de la liste, on ajoute des
				// virgules dans la requête préparée (entre les "?") et entre
				// les champs du " INSERT INTO (...)"
				req += ", ";
			}

			req += e.getKey() + " = ?";
			first = false;
		}

		// UPDATE personne SET nom=?, prenom=? WHERE id=?
		// 1 <== p.getNom() // avec la reflexion
		// 2 <== p.getPrenom() // avec la reflexion
		// 3 <== p.getId(); // pas besoin de la reflexion car T implements
		// IDomainObject
		req = "UPDATE " + table + req + " WHERE id = ?";

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
			System.out.println("m " + key);
			String name = "get" + key.substring(0, 1).toUpperCase()
					+ key.substring(1);
			Method m = maClasse.getMethod(name);
			// On fait appel à la méthode construite sur l'objet passé en
			// paramètre. On construit un objet qui correspond au resultat de
			// l'execution du getter.
			Object r = m.invoke(p);

			if (e.getValue() != r.getClass()) {
				// TODO: gérer exception lorsque la méthode n'existe pas dans

			}
			// On remplie les "?" de la requête préparée en fonction du type des
			// champs

			if (e.getValue() == String.class) {
				// System.out.println("string " +r);
				ps.setString(i, (String) r);
			} else if (e.getValue() == Integer.class) {
				// System.out.println("int "+r);
				ps.setInt(i, (Integer) r);
			} else if (e.getValue() == Date.class) {
				// System.out.println("date "+r);
				ps.setDate(i, (Date) r);
			} else if (e.getValue() == Boolean.class) {
				// System.out.println("bool "+r);
				ps.setBoolean(i, (Boolean) r);
			} else if (e.getValue() == Personne.class) {

			}
		}
		ps.setInt(++i, p.getId());
		// System.out.println(ps);
		ps.executeUpdate();
	}

	/**
	 * Permet de retrouver un objet en base grace a son identifiant.
	 * 
	 * @param id
	 *            l'identifiant de l'objet que l'on cherche
	 * @return T Un objet Correspondant au type de T generique
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public T findById(final int id) throws SQLException {

		if (reference.containsKey(id)) {
			System.out.println("si mon objet de type " + maClasse
					+ " existe je le retourne");
			return reference.get(id).get();
		}
		// On part du principe que pour tout les types d'objet, l'identifiant
		// est contenu dans un champ nomme "id"
		System.out.println("on recherche par l'id");
		String req = "SELECT * FROM " + table + " WHERE id = ?";
		PreparedStatement ps = DBConfig.getConnection().prepareStatement(req);

		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			if (this.maClasse == Personne.class) {
				System.out.println("je ne contient pas " + rs.getString("id")
						+ " on le crée");

				Personne p = new Personne(rs.getInt("id"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("evaluation"),
						null);
				p.setA_pour_pere(new VirtualProxyGeneriqueBuilder<IPersonne>(
						IPersonne.class, new PersonneFactory(rs
								.getInt("a_pour_pere"))).getProxy());
				p.setLesFils(new VirtualProxyGeneriqueBuilder<List<IPersonne>>(
						List.class, new ListFilsPersonne(p.getId())).getProxy());
				p.add(UnitOfWork.getInstance());
				return (T) p;
			}
		}
		return null;
	}

	/**
	 * Permet de retrouver l'ensemble des fils d'une personne dont l'identifiant
	 * est passé en paramètre. On retourne une liste de personnes qui sont fils.
	 * 
	 * @param id
	 *            de la personne dont on chercher les fils
	 * @return l'ensemble des fils d'une personne
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<T> findFilsOf(final int id) throws SQLException {
		// On part du principe que pour tout les types d'objet l'identifiant est
		// contenu dans un champ nomm� "id"
		System.out.println("on recherche les fils");
		String req = "SELECT * FROM " + table + " WHERE a_pour_pere = ?";
		PreparedStatement ps = DBConfig.getConnection().prepareStatement(req);
		List<T> t = new ArrayList<T>();
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			if (reference.containsKey(rs.getInt("id"))) {
				System.out.println("si le fils existe, on l'ajoute ");
				t.add(reference.get(rs.getInt("id")).get());
			} else {
				System.out
						.println("le fils n'existe pas, on le crée et l'ajoute");
				if (this.maClasse == Personne.class) {
					System.out.println("on ne contient pas "
							+ rs.getString("id") + " on le crée");

					Personne p = new Personne(rs.getInt("id"),
							rs.getString("nom"), rs.getString("prenom"),
							rs.getString("evaluation"), null);
					p.setA_pour_pere(new VirtualProxyGeneriqueBuilder<IPersonne>(
							IPersonne.class, new PersonneFactory(id))
							.getProxy());
					p.setLesFils(new VirtualProxyGeneriqueBuilder<List<IPersonne>>(
							List.class, new ListFilsPersonne(p.getId()))
							.getProxy());
					p.add(UnitOfWork.getInstance());
					t.add((T) p);
				}
			}
		}

		return t;
	}
}