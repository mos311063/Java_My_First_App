
package db;

/**
 * SQL connection object 
 * store user,pass,driver,url
 * @author Moss
 */
public class SQLConnection {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/soccersystem?autoReconnect = true";

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getDRIVER() {
        return DRIVER;
    }

    public static String getURL() {
        return URL;
    }
    
    
}
