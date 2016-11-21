package application;

import persistance.DBConfig;
import vues.JFrameConnexion;

/**
 * @author six
 *
 */
public class Application {

	public static void connect(){
		DBConfig.setUsername("delbrouquepri");
		DBConfig.setPassword("txxmvt4w");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		connect();
		new JFrameConnexion();
	}

}
