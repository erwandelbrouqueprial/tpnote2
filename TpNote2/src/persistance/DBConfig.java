package persistance;

import java.sql.DriverManager;

import java.sql.Connection;

public class DBConfig {

	private static Connection c; 
	private static String username;
	private static String password;
	
	/**
	 * 
	 * @param username String identifiant de connexion
	 */
    public static void setUsername(final String username) {
		DBConfig.username = username;
	}

    /**
     * 
     * @param password String mot de passe de connexion
     */
	public static void setPassword(final String password) {
		DBConfig.password = password;
	}

	/**
	 * getConnection() est une m�thode utilisant un design pattern Singleton 
	 * �vitant donc de multi connexion � la base
	 * @return c Connection retourne une connection de type mysql 
	 */
	public static Connection getConnection(){ 
        if (c == null) {
        	try{
            	c = (DriverManager.getConnection("jdbc:mysql://webtp.fil.univ-lille1.fr/delbrouquepri",username,password));
            }catch(Exception e){
                System.out.println(e);  
            };
		} 
        return c;
    }; 

}
