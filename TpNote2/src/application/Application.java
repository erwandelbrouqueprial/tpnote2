package application;

import persistance.DBConfig;
import vues.JFrameConnexion;

/**
 * @author six Classe permettant le lancement de l'application.
 */
public class Application {

	public static void connect() {
		DBConfig.setUsername("A_REMPLIR");
		DBConfig.setPassword("A_REMPLIR");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		connect();
		new JFrameConnexion();
	}

}
