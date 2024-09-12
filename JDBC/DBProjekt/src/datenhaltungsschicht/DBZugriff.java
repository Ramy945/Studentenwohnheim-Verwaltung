package datenhaltungsschicht;

import java.sql.*;

/**
 * Die Klasse DBZugriff bietet Methoden zum Herstellen und Schließen von Verbindungen zu einer Datenbank.
 * Sie verwendet JDBC zum Verbinden mit einer Oracle-Datenbank.
 */

public class DBZugriff {

    private static Connection con;
    private static String url = "jdbc:oracle:thin:@172.22.160.22:1521:xe";
    protected static Statement befehl;
   
    /**
     * Stellt eine Verbindung zur Oracle-Datenbank her.
     *
     * @throws SQLException Wenn ein Fehler beim Verbinden mit der Datenbank auftritt.
     */
    
    public static void connect() throws SQLException {
        try {
            con = DriverManager.getConnection(url, "C##FBPOOL11", "oracle");
            befehl = con.createStatement();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Herstellen der Verbindung zur Datenbank aufgetreten.";
            throw new SQLException(errorMessage, ex);
        }
    }
    
    /**
     * Schließt die Datenbankverbindung und den SQL-Befehl.
     *
     * @throws SQLException Wenn ein Fehler beim Schließen der Verbindung oder des Befehls auftritt.
     */
    public static void close() throws SQLException {
        try {
        	
        	if (befehl !=null)
        		befehl.close();
        	
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Schließen der Verbindung zur Datenbank aufgetreten.";
            throw new SQLException(errorMessage, ex);
        }
    }
    
}