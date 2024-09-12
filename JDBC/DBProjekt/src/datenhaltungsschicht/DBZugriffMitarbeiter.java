package datenhaltungsschicht;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import logikschicht.Mitarbeiter;

/**
 * Die Klasse DBZugriffMitarbeiter erweitert die DBZugriff-Klasse und bietet Methoden für den Zugriff auf Datenbankoperationen
 * bezüglich der Mitarbeiter.
 */

public class DBZugriffMitarbeiter extends DBZugriff {

	private static ResultSet datenmenge;
	
	/**
     * Fügt einen neuen Mitarbeiter in die Datenbank ein.
     *
     * @param mitarbeiter Der Mitarbeiter, der hinzugefügt werden soll.
     * @return true, wenn die Operation erfolgreich war, sonst wird eine Exception ausgelöst.
     * @throws Exception Wenn ein Fehler beim Hinzufügen des Mitarbeiters auftritt.
     */

    public static boolean insert(Mitarbeiter mitarbeiter) throws Exception {
        connect();
        String insertCommand = "INSERT INTO Mitarbeiter (Mitarbeiter_ID, Email, Vorname, Nachname, Rolle, Wohnheimnummer) VALUES ("
                + mitarbeiter.getMitarbeiterID() + ", '" + mitarbeiter.getEmail() + "', '" + mitarbeiter.getVorname() + "', '" + mitarbeiter.getNachname() + 
               "', '" + mitarbeiter.getRolle() + "', '" + mitarbeiter.getWohnheimnummer() + "')";

        try {
            befehl.executeUpdate(insertCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Hinzufügen der Mitarbeiter " + mitarbeiter.getMitarbeiterID() + " aufgetreten.";
            throw new Exception(errorMessage);
        }finally { 
        	close();
        }
        return true;
    }
    
    /**
     * Aktualisiert einen vorhandenen Mitarbeiter in der Datenbank.
     *
     * @param mitarbeiter Der Mitarbeiter, der aktualisiert werden soll.
     * @return true, wenn die Operation erfolgreich war, sonst wird eine Exception ausgelöst.
     * @throws Exception Wenn ein Fehler beim Aktualisieren des Mitarbeiters auftritt.
     */


    public static boolean update(Mitarbeiter mitarbeiter) throws Exception {
        connect();
        String updateCommand = "UPDATE Mitarbeiter SET Email = '" + mitarbeiter.getEmail() + "', Vorname = '" + mitarbeiter.getVorname() + "', Nachname = '" + mitarbeiter.getNachname() + "', Rolle = '" + mitarbeiter.getRolle() +
                "', Wohnheimnummer = '" + mitarbeiter.getWohnheimnummer() + "' WHERE Mitarbeiter_ID = " + mitarbeiter.getMitarbeiterID();

        try {
            befehl.executeUpdate(updateCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Aktualisieren der Mitarbeiter " + mitarbeiter.getMitarbeiterID() + " aufgetreten.";
            throw new Exception(errorMessage);
        }finally {
        	close();
        }
        return true;
    }
    
    /**
     * Löscht einen Mitarbeiter aus der Datenbank.
     *
     * @param mitarbeiter Der zu löschende Mitarbeiter.
     * @return true, wenn die Operation erfolgreich war, sonst wird eine Exception ausgelöst.
     * @throws Exception Wenn ein Fehler beim Löschen des Mitarbeiters auftritt.
     */

    public static boolean delete(Mitarbeiter mitarbeiter) throws Exception {
        connect();
        String deleteCommand = "DELETE FROM Mitarbeiter WHERE Mitarbeiter_ID = " + mitarbeiter.getMitarbeiterID();

        try {
        	befehl.executeUpdate(deleteCommand);
        } catch (SQLException ex) {
            String errorMessage = "Es ist ein Fehler beim Löschen der Mitarbeiter " + mitarbeiter.getMitarbeiterID() + " aufgetreten.";
            throw new Exception(errorMessage);
        }finally {
        	close();
        }
        return true;
    }
    
    /**
     * Ruft alle Mitarbeiter aus der Datenbank ab.
     *
     * @return Eine Liste aller Mitarbeiter.
     * @throws Exception Wenn ein Fehler beim Lesen der Mitarbeiterdaten auftritt.
     */

    public static List<Mitarbeiter> getAllMitarbeiter() throws Exception {
    	
        ArrayList<Mitarbeiter> mitarbeitern = new ArrayList<>();
        connect();
        try {
    		datenmenge = befehl.executeQuery("SELECT * FROM Mitarbeiter");
	        while (getNext()) {
	            String mitarbeiterID = datenmenge.getString("Mitarbeiter_ID");
	            String email = datenmenge.getString("Email");
	            String vorname = datenmenge.getString("Vorname");
	            String nachname = datenmenge.getString("Nachname");
	            String rolle = datenmenge.getString("Rolle"); 
	            String wohnheimnummer = datenmenge.getString("wohnheimnummer");
	          
	            Mitarbeiter mitarbeiter = new Mitarbeiter(mitarbeiterID, email, vorname, nachname, rolle, wohnheimnummer);
	            mitarbeitern.add(mitarbeiter);
	        }
       
    	}catch(Exception e) {
    		throw new Exception("Es ist ein Fehler beim Lesen der Mitarbeiterdaten aufgetreten. ");
    	}finally{
    		close();
    	}
        return mitarbeitern;
    }

    /**
     * Ruft einen Mitarbeiter anhand seiner Mitarbeiter-ID aus der Datenbank ab.
     *
     * @param mitarbeiterID Die ID des abzurufenden Mitarbeiters.
     * @return Der abgerufene Mitarbeiter oder null, wenn kein Mitarbeiter mit der ID gefunden wurde.
     * @throws Exception Wenn ein Fehler beim Lesen der Mitarbeiterdaten auftritt.
     */
    
    public static Mitarbeiter getMitarbeiterByMitarbeiterID(String mitarbeiterID) throws Exception {
        connect();
        Mitarbeiter mitarbeiter = null;
        String query = "SELECT * FROM Mitarbeiter WHERE Mitarbeiter_ID = " + mitarbeiterID;
        
        try {
	        datenmenge = befehl.executeQuery(query);
	
	        if (datenmenge.next()) {
	        	String email = getEmail();
	            String vorname = getVorname();
	            String nachname = getNachname();
	            String rolle = getRolle();
	            String wohnheimnummer = Integer.toString(getWohnheimnummer());
	
	            mitarbeiter = new Mitarbeiter(mitarbeiterID, email, vorname, nachname, rolle, wohnheimnummer);
	        }
        }catch(Exception e) {
    		throw new Exception("Es ist ein Fehler beim Lesen der Mitarbeiterdaten aufgetreten. ");
        }finally {
        	close();
        }
        return mitarbeiter;
    }
    
    /**
     * Prüft, ob weitere Datensätze in der Ergebnismenge vorhanden sind.
     *
     * @return true, wenn weitere Datensätze vorhanden sind, sonst false.
     * @throws Exception Wenn ein Fehler beim Lesen der Ergebnismenge auftritt.
     */

    public static boolean getNext() throws Exception {
        if (datenmenge.next()) {
            return true;
        } else {
            close();
            return false;
        }
    }
    
    /**
     * Gibt die Mitarbeiter-ID des aktuellen Mitarbeiters in der Ergebnismenge zurück.
     *
     * @return Die Mitarbeiter-ID des aktuellen Mitarbeiters.
     * @throws SQLException Wenn ein Fehler beim Lesen der Mitarbeiter-ID auftritt.
     */

    public static int getMitarbeiterID() throws SQLException  {
        return datenmenge.getInt("Mitarbeiter_ID");
    }

    /**
     * Gibt die E-Mail-Adresse des aktuellen Mitarbeiters in der Ergebnismenge zurück.
     *
     * @return Die E-Mail-Adresse des aktuellen Mitarbeiters.
     * @throws Exception Wenn ein Fehler beim Lesen der E-Mail-Adresse auftritt.
     */
    
    public static String getEmail() throws Exception {
        return datenmenge.getString("Email");
    }
    
    /**
     * Gibt den Vornamen des aktuellen Mitarbeiters in der Ergebnismenge zurück.
     *
     * @return Der Vorname des aktuellen Mitarbeiters.
     * @throws Exception Wenn ein Fehler beim Lesen des Vornamens auftritt.
     */

    public static String getVorname() throws Exception {
        return datenmenge.getString("Vorname");
    }

    /**
     * Gibt den Nachnamen des aktuellen Mitarbeiters in der Ergebnismenge zurück.
     *
     * @return Der Nachname des aktuellen Mitarbeiters.
     * @throws Exception Wenn ein Fehler beim Lesen des Nachnamens auftritt.
     */
    
    public static String getNachname() throws Exception {
        return datenmenge.getString("Nachname");
    }


    /**
     * Gibt die Rolle des aktuellen Mitarbeiters in der Ergebnismenge zurück.
     *
     * @return Die Rolle des aktuellen Mitarbeiters.
     * @throws Exception Wenn ein Fehler beim Lesen der Rolle auftritt.
     */
    
    public static String getRolle() throws Exception {
        return datenmenge.getString("Rolle");
    }

    /**
     * Gibt die Wohnheimnummer des aktuellen Mitarbeiters in der Ergebnismenge zurück.
     *
     * @return Die Wohnheimnummer des aktuellen Mitarbeiters.
     * @throws Exception Wenn ein Fehler beim Lesen der Wohnheimnummer auftritt.
     */

    public static int getWohnheimnummer() throws Exception {
        return datenmenge.getInt("Wohnheimnummer");
    }

}
